package com.ageone.naladonipartner.Models.User

import com.ageone.naladonipartner.Application.utils
import net.alexandroid.shpref.ShPref

class UserInformation {
    var city: City?
        get() {
            val cityHashId = ShPref.getString("userCity", "")
            if (!cityHashId.isNullOrBlank()) {
              /*  utils.realm.city.getObjectById(cityHashId)?.let { cityDB ->
                    return City(cityDB.name, cityDB.hashId)
                }*/
            }
            return null
        }
        set(city) {
            city?.let { city ->
                val cityHashId = city.hashId
                if (!cityHashId.isNullOrBlank()) {
                    ShPref.put("userCity", cityHashId)
                }
            }

        }
}

data class City (
    var name: String = "",
    var hashId: String = ""
)

