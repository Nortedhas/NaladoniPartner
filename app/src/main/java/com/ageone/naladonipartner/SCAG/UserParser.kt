package com.ageone.naladoni.SCAG

import com.ageone.naladonipartner.Models.User.user
import org.json.JSONObject

fun Parser.userData(json: JSONObject) {
	json.optJSONObject("User")?.let { userJson ->
		user.hashId = userJson.optString("hashId", "")
		/*user.data.giftsTakenNum = userJson.optInt("giftsTakenNum", 0)
		user.data.phone = userJson.optString("phone", "")
		user.data.role = userJson.optString("role", "")
		user.data.name = userJson.optString("name", "")*/
	}
}