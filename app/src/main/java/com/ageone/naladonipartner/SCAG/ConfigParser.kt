package com.ageone.naladoni.SCAG

import com.ageone.naladonipartner.Application.utils
import org.json.JSONObject

fun Parser.config(json: JSONObject) {
	json.optJSONArray("Config")?.optJSONObject(0)?.let { userJson ->
		utils.config.name = userJson.optString("name", "")
	}
}