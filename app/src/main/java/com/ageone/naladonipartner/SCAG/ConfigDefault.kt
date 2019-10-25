package com.ageone.naladoni.SCAG
import io.realm.Realm
import net.alexandroid.shpref.ShPref


object ConfigDefault {

	var name: String
		get() = ShPref.getString("userDataName", "")
		set(value) = ShPref.put("configDataName", value)


}