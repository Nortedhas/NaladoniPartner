package com.ageone.naladonipartner.External.HTTP.API

import android.provider.Settings
import com.ageone.naladonipartner.Application.api
import com.ageone.naladonipartner.Application.currentActivity
import com.ageone.naladonipartner.Application.utils
import com.ageone.naladonipartner.External.Libraries.Alert.alertManager
import com.ageone.naladonipartner.External.Libraries.Alert.blockUI
import com.ageone.naladonipartner.External.Libraries.Alert.single
import com.ageone.naladonipartner.Models.User.user
//import com.ageone.naladonipartner.SCAG.*
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.swarmnyc.promisekt.Promise
import net.alexandroid.shpref.ShPref
import org.json.JSONObject
import timber.log.Timber
import java.util.*

class API {
    var cashTime: Int
        get() = ShPref.getInt("ApiCashTime", 0)
        set(value) = ShPref.put("ApiCashTime", value)

   // val parser = Parser()

    fun handshake(completion: () -> Unit){
        Fuel.post(Routes.Handshake.path)
            .jsonBody(createBody(mapOf(
                "deviceId" to Settings.Secure.getString(currentActivity?.contentResolver, Settings.Secure.ANDROID_ID)
            )).toString())
            .responseString { request, response, result ->
                Timber.i("API Handshake: $request $response")

                val jsonObject = JSONObject(result.get())
                utils.variable.token = jsonObject.optString("Token")
                Timber.i("API new token: ${utils.variable.token}")
//                cashTime = Date().time.toInt()
              //  parser.userData(jsonObject)
                completion.invoke()
            }
    }

    fun request(params: Map<String, Any>, isErrorShown: Boolean = false, completion: (JSONObject) -> (Unit)) {

        Fuel.post(Routes.Api.path)
            .jsonBody(createBody(params).toString())
           // .header(DataBase.headers)
            .responseString { request, response, result ->
                result.fold({ result ->
                    val jsonObject = JSONObject(result)
                    Timber.i("API request:\n $request \n $response")

                    val error = jsonObject.optString("error", "")
                    if (error.isNotEmpty()) {
                        Timber.e("$error")
                        if (isErrorShown) {
                            alertManager.single("Ошибка", "$error",
                                completion =  {_,_ ->
                                    alertManager.blockUI(false)
                                })
                        }
                    } else {
                        completion.invoke(jsonObject)
                    }

                },{ error ->
                    Timber.e("${error.response.responseMessage}")
                })

            }
    }

    fun createBody(params: Map<String, Any>): JSONObject {
        val body = JSONObject()
        params.forEach { (key, value) ->
            body.put(key, value)
        }

        return body
    }


//    fun handleUser()

    /*fun requestMainLoad(completion: () -> Unit){

        api.request(mapOf("router" to "mainLoad", "cashTime" to api.cashTime)) { jsonObject ->
          /*  for (type in DataBase.values()) {
                parser.parseAnyObject(jsonObject, type)
            }
            parser.config(jsonObject)*/
            api.cashTime = (System.currentTimeMillis() / 1000).toInt()
            completion.invoke()
        }

    }*/


    enum class Routes(val path: String) {
        Handshake("/handshake"),
        Database("/database"),
        Api("/api");
    }
}