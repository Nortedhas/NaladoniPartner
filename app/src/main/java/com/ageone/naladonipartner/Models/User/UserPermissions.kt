package com.ageone.naladonipartner.Models.User

import net.alexandroid.shpref.ShPref

class UserPermissions {
    var geo: Boolean
        get() = ShPref.getBoolean("userGeo", false)
        set(value) = ShPref.put("userGeo", value)
}