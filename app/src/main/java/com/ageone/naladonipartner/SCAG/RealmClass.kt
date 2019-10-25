// MARK: Realm Class

package com.ageone.naladoni.SCAG

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Category (
	open var serialNum: Int = 0,
	open var subcategory: RealmList<Subcategory> = RealmList(),
	open var created: Int = 0,
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var name: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class City (
	open var created: Int = 0,
	open var updated: Int = 0,
	open var name: String = "",
	open var isExist: Boolean = false,
	open var location: Location? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Company (
	open var created: Int = 0,
	open var image: Image? = null,
	open var isExist: Boolean = false,
	open var updated: Int = 0,
	open var name: String = "",
	open var password: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Document (
	open var created: Int = 0,
	open var image: Image? = null,
	open var isExist: Boolean = false,
	open var updated: Int = 0,
	open var __type: String = "",
	open var name: String = "",
	open var txttext: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Image (
	open var updated: Int = 0,
	open var created: Int = 0,
	open var isExist: Boolean = false,
	open var preview: String = "",
	open var original: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Location (
	open var address: String = "",
	open var longitude: Double = 0.0,
	open var created: Int = 0,
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var latitude: Double = 0.0,
	open var geoHash: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Stock (
	open var isPopular: Boolean = false,
	open var category: Category? = null,
	open var longAbout: String = "",
	open var subcategory: RealmList<Subcategory> = RealmList(),
	open var code: String = "",
	open var updated: Int = 0,
	open var city: City? = null,
	open var name: String = "",
	open var image: Image? = null,
	open var usesNum: Int = 0,
	open var created: Int = 0,
	open var shortAbout: String = "",
	open var workTime: String = "",
	open var isExist: Boolean = false,
	open var avaliableTo: Int = 0,
	open var location: Location? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Subcategory (
	open var created: Int = 0,
	open var name: String = "",
	open var isExist: Boolean = false,
	open var updated: Int = 0,
	open var serialNum: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class User (
	open var phone: String = "",
	open var fcmToken: String = "",
	open var role: String = "",
	open var created: Int = 0,
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var giftsTakenNum: Int = 0,
	open var deviceId: String = "",
	open var name: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()