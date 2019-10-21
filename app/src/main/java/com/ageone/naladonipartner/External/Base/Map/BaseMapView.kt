package com.ageone.naladonipartner.External.Base.Map

import com.ageone.naladonipartner.Application.utils
import com.ageone.naladonipartner.External.Base.ImageView.BaseImageView
import com.ageone.naladonipartner.External.Extensions.Activity.startLocation
import com.ageone.naladonipartner.Models.User.user
import com.github.kittinunf.fuel.Fuel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin


fun GoogleMap.setMyLocation(buttonLocation: BaseImageView) {
    if (user.permission.geo) {
        isMyLocationEnabled = true
        uiSettings.isMyLocationButtonEnabled = false
    }

    buttonLocation.setOnClickListener {
        moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 13f))
    }
}

fun geodecodeByCoordinates(latitude: Double, longitude: Double, completion: (Address) -> Unit) {

    val url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=$latitude,$longitude&key=${utils.googleApiKey}"

    Fuel.post(url)
        .responseString { request, response, result ->
            result.fold({ result ->
                val jsonObject = JSONObject(result)
                Timber.i("\nAPI Google Request:\n $request\n \n $response")

                val error = jsonObject.optString("error", "")
                if (error != "") {
                    Timber.e("$error")
                } else {
                    completion.invoke(parseGoogleMapResultsFromJson(jsonObject))
                }

            },{ error ->
                Timber.e("${error.response.responseMessage}")
            })
        }
}

fun parseGoogleMapResultsFromJson(json: JSONObject): Address {
    var address = Address()

    address.lat = json.optJSONArray("results")
        .optJSONObject(0).optJSONObject("geometry")
        .optJSONObject("location").optDouble("lat", .0)//]json["results"][0]["geometry"]["location"]["lat"].doubleValue

    address.lng = json.optJSONArray("results")
        .optJSONObject(0).optJSONObject("geometry")
        .optJSONObject("location").optDouble("lng", .0)//json["results"][0]["geometry"]["location"]["lng"].doubleValue

    (json["results"] as JSONArray).optJSONObject(0).optJSONArray("address_components")?.let{ adressComponents ->
        for (i in 0 until adressComponents.length()) {
            val addressObject = adressComponents[i] as JSONObject
            val type = addressObject.optJSONArray("types").optString(0)
            var value = addressObject.optString("short_name")
            if (value.isNullOrBlank()) {
                value = addressObject.optString("long_name")
            }

            when (getAddressComponentByType(type)) {
                ComponentType.home -> {
                    address.home = value
                }
                ComponentType.postalCode -> {
                    address.postalCode = value
                }
                ComponentType.street -> {
                    address.street = value
                }
                ComponentType.region -> {
                    address.region = value
                }
                ComponentType.city -> {
                    address.city = value
                }
                ComponentType.country -> {
                    address.country = value
                }
                ComponentType.none -> {
                    Timber.e("Cant parce $type from Google Map Address Component")
                }
            }

        }
    }

    return address
}

enum class ComponentType {
    home, postalCode, street, region, city, country, none
}

fun getAddressComponentByType(type: String): ComponentType = when(type) {

    "street_number" -> {
        ComponentType.home
    }
    "postal_code" -> {
        ComponentType.postalCode
    }
    in "street_address", "route" -> {
        ComponentType.street
    }
    in "administrative_area_level_1",
    "administrative_area_level_2",
    "administrative_area_level_3",
    "administrative_area_level_4",
    "administrative_area_level_5" -> {
        ComponentType.region
    }
    in "locality",
    "sublocality",
    "sublocality_level_1",
    "sublocality_level_2",
    "sublocality_level_3",
    "sublocality_level_4" -> {
        ComponentType.city
    }
    "country" -> {
        ComponentType.country
    }
    else -> {
        ComponentType.none
    }
}

data class Address(
    var home: String = "",
    var postalCode: String = "",
    var street: String = "",
    var region: String = "",
    var city: String = "",
    var country: String = "",
    var lat: Double = .0,
    var lng: Double = .0
)

fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double, unit: String): Double {
    if (lat1 == lat2 && lon1 == lon2) {
        return 0.0
    } else {
        val theta = lon1 - lon2
        var dist = sin(Math.toRadians(lat1)) * sin(Math.toRadians(lat2)) + cos(
            Math.toRadians(lat1)
        ) * cos(Math.toRadians(lat2)) * cos(Math.toRadians(theta))
        dist = acos(dist)
        dist = Math.toDegrees(dist)
        dist *= 60.0 * 1.1515
        if (unit === "K") {
            dist *= 1.609344
        } else if (unit === "N") {
            dist *= 0.8684
        }
        return dist
    }
}

