package com.ageone.naladoni.SCAG
import io.realm.Realm
import net.alexandroid.shpref.ShPref


object UserData {

	var role: String
		get() = ShPref.getString("userDataRole", "")
		set(value) = ShPref.put("userDataRole", value)

	var giftsTakenNum: Int
		get() = ShPref.getInt("userDataGiftsTakenNum", 0)
		set(value) = ShPref.put("userDataGiftsTakenNum", value)

	var name: String
		get() = ShPref.getString("userDataName", "")
		set(value) = ShPref.put("userDataName", value)

	var phone: String
		get() = ShPref.getString("userDataPhone", "")
		set(value) = ShPref.put("userDataPhone", value)


}