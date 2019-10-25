// MARK: Parser

package com.ageone.naladoni.SCAG

import com.ageone.naladonipartner.External.Extensions.Realm.add
import org.json.JSONObject

class Parser {
fun parseAnyObject(json: JSONObject, type: DataBase) {
	json.optJSONArray(type.name)?.let{array ->
		for (i in 0 until array.length()) {
			val json = array[i] as JSONObject
			val obj = when (type) {
				DataBase.Category -> {
					json.parseCategory()
				}
				DataBase.City -> {
					json.parseCity()
				}
				DataBase.Company -> {
					json.parseCompany()
				}
				DataBase.Document -> {
					json.parseDocument()
				}
				DataBase.Image -> {
					json.parseImage()
				}
				DataBase.Location -> {
					json.parseLocation()
				}
				DataBase.Stock -> {
					json.parseStock()
				}
				DataBase.Subcategory -> {
					json.parseSubcategory()
				}
				DataBase.User -> {
					json.parseUser()
				}
				}
			add(obj)
		}
	}
}

}

// MARK: Parse JSON to Realm

fun JSONObject.parseCategory(): Category {
	val some = Category()
	some.isExist = optBoolean("isExist", false)
	some.hashId = optString("hashId", "")
	optJSONObject("subcategory")?.let { subcategorys ->
 		for (i in 0 until subcategorys.length()) {
 			some.subcategory.add(
				subcategorys.optJSONObject("$i")?.let { subcategory ->
					subcategory.parseSubcategory()
				}
			)
		}
	}
	some.name = optString("name", "")
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	some.serialNum = optInt("serialNum", 0)
	return some
}

fun JSONObject.parseCity(): City {
	val some = City()
	some.name = optString("name", "")
	some.updated = optInt("updated", 0)
	optJSONObject("location")?.let { location ->
		some.location = location.parseLocation()
	}
	some.hashId = optString("hashId", "")
	some.isExist = optBoolean("isExist", false)
	some.created = optInt("created", 0)
	return some
}

fun JSONObject.parseCompany(): Company {
	val some = Company()
	some.isExist = optBoolean("isExist", false)
	some.password = optString("password", "")
	some.hashId = optString("hashId", "")
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.name = optString("name", "")
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	return some
}

fun JSONObject.parseDocument(): Document {
	val some = Document()
	some.isExist = optBoolean("isExist", false)
	some.hashId = optString("hashId", "")
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.__type = optString("__type", "")
	some.name = optString("name", "")
	some.txttext = optString("txttext", "")
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	return some
}

fun JSONObject.parseImage(): Image {
	val some = Image()
	some.isExist = optBoolean("isExist", false)
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	some.original = optString("original", "")
	some.preview = optString("preview", "")
	return some
}

fun JSONObject.parseLocation(): Location {
	val some = Location()
	some.latitude = optDouble("latitude", 0.0)
	some.address = optString("address", "")
	some.created = optInt("created", 0)
	some.geoHash = optString("geoHash", "")
	some.longitude = optDouble("longitude", 0.0)
	some.updated = optInt("updated", 0)
	some.isExist = optBoolean("isExist", false)
	some.hashId = optString("hashId", "")
	return some
}

fun JSONObject.parseStock(): Stock {
	val some = Stock()
	some.longAbout = optString("longAbout", "")
	some.shortAbout = optString("shortAbout", "")
	some.updated = optInt("updated", 0)
	some.isExist = optBoolean("isExist", false)
	some.name = optString("name", "")
	some.usesNum = optInt("usesNum", 0)
	optJSONObject("subcategory")?.let { subcategorys ->
 		for (i in 0 until subcategorys.length()) {
 			some.subcategory.add(
				subcategorys.optJSONObject("$i")?.let { subcategory ->
					subcategory.parseSubcategory()
				}
			)
		}
	}
	some.code = optString("code", "")
	some.isPopular = optBoolean("isPopular", false)
	some.avaliableTo = optInt("avaliableTo", 0)
	some.created = optInt("created", 0)
	optJSONObject("location")?.let { location ->
		some.location = location.parseLocation()
	}
	optJSONObject("category")?.let { category ->
		some.category = category.parseCategory()
	}
	some.hashId = optString("hashId", "")
	some.workTime = optString("workTime", "")
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	optJSONObject("city")?.let { city ->
		some.city = city.parseCity()
	}
	return some
}

fun JSONObject.parseSubcategory(): Subcategory {
	val some = Subcategory()
	some.isExist = optBoolean("isExist", false)
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	some.serialNum = optInt("serialNum", 0)
	some.updated = optInt("updated", 0)
	some.name = optString("name", "")
	return some
}

fun JSONObject.parseUser(): User {
	val some = User()
	some.deviceId = optString("deviceId", "")
	some.created = optInt("created", 0)
	some.fcmToken = optString("fcmToken", "")
	some.phone = optString("phone", "")
	some.giftsTakenNum = optInt("giftsTakenNum", 0)
	some.isExist = optBoolean("isExist", false)
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	some.role = optString("role", "")
	some.name = optString("name", "")
	return some
}