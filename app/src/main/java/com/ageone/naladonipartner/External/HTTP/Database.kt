    package com.ageone.naladonipartner.External.HTTP

import com.ageone.naladonipartner.Application.api
import com.ageone.naladonipartner.External.HTTP.API.API
//import com.ageone.naladonipartner.SCAG.DataBase
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber

/*fun DataBase.request(params: Map<String, Any>, completion: (JSONObject) -> (Unit)) {

    Fuel.post(API.Routes.Database.path)
        .jsonBody(api.createBody(params).toString())
        .header(DataBase.headers)
        .responseString { request, response, result ->
            result.fold({ result ->
                val jsonObject = JSONObject(result)
                Timber.i("API Update:\n $request\n \n $response")

                val error = jsonObject.optString("error", "")
                if (error != "") {
                    Timber.e("$error")
                } else {
                    completion.invoke(jsonObject)
                }

            },{ error ->
                Timber.e("${error.response.responseMessage}")
            })

        }
}

//TODO: 3 func
fun DataBase.update(objectID: String, objectStruct: Map<String, Any>) {
    request(
        mapOf(
            "router" to "update",
            "collectionName" to name,
            "elementId" to objectID,
            "jsonValues" to api.createBody(objectStruct)

        )) { jsonObject ->
        Timber.i("Object: $jsonObject")
    }
}

fun DataBase.delete(objectID: String) {
    request(
        mapOf(
            "router" to "delete",
            "collectionName" to name,
            "elementId" to objectID
        )) { jsonObject ->
        Timber.i("Object: $jsonObject")
    }
}

fun DataBase.fetch(filter: String, cashTime: Int = 0, completion: (JSONObject) -> (Unit)) {
    request(
        mapOf(
            "router" to "fetch",
            "collectionName" to name,
            "cashTime" to cashTime,
            "withCollectionName" to true,
            "filter" to filter
        )) { json ->
        Timber.i("Object: $json")
        completion.invoke(json)
    }
}
*/