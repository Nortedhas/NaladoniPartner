package com.ageone.naladonipartner.Network.HTTP

/*import com.ageone.naladonipartner.External.HTTP.API.API
import com.ageone.naladonipartner.Models.User.City
import com.ageone.naladonipartner.SCAG.DataBase
import org.json.JSONObject
import timber.log.Timber

fun API.getCityStocks(cityHashId: String) {
    request(
        mapOf(
            "router" to "getCityStocks",
            "cashTime" to api.cashTime,
            "cityHashId" to cityHashId
        )) { jsonObject ->

            parser.parseAnyObject(jsonObject, DataBase.Stock)
        }
}

fun API.getCity(latitude: Double, longitude: Double, completion: (City) -> Unit) {
    request(
        mapOf(
            "router" to "getCity",
            "latitude" to  latitude,
            "longitude" to longitude
            "clientCoordinates" to JSONObject().apply {
                put("latitude", latitude)
                put("longitude", longitude)
            }
        )) { jsonObject ->

            Timber.i("$jsonObject")
            jsonObject.optJSONObject("City")?.let { city ->
                val nearCity = City(city.optString("name"), city.optString("hashId"))
                completion.invoke(nearCity)
            }
        }
}
*/
