// MARK: Realm Utiles

package com.ageone.naladoni.SCAG

import io.realm.Realm
import io.realm.RealmResults
import timber.log.Timber


object RealmUtilities {
	val category = CategoryUtiles()
	val city = CityUtiles()
	val company = CompanyUtiles()
	val document = DocumentUtiles()
	val image = ImageUtiles()
	val location = LocationUtiles()
	val stock = StockUtiles()
	val subcategory = SubcategoryUtiles()
	val user = UserUtiles()
}

class CategoryUtiles {

	fun getObjectById(id: String): Category? =
	try {
		Realm.getDefaultInstance()
			.where(Category::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Category> =
	try {
		Realm.getDefaultInstance()
			.where(Category::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Category::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class CityUtiles {

	fun getObjectById(id: String): City? =
	try {
		Realm.getDefaultInstance()
			.where(City::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<City> =
	try {
		Realm.getDefaultInstance()
			.where(City::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(City::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class CompanyUtiles {

	fun getObjectById(id: String): Company? =
	try {
		Realm.getDefaultInstance()
			.where(Company::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Company> =
	try {
		Realm.getDefaultInstance()
			.where(Company::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Company::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class DocumentUtiles {

	fun getObjectById(id: String): Document? =
	try {
		Realm.getDefaultInstance()
			.where(Document::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Document> =
	try {
		Realm.getDefaultInstance()
			.where(Document::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Document::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class ImageUtiles {

	fun getObjectById(id: String): Image? =
	try {
		Realm.getDefaultInstance()
			.where(Image::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Image> =
	try {
		Realm.getDefaultInstance()
			.where(Image::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Image::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class LocationUtiles {

	fun getObjectById(id: String): Location? =
	try {
		Realm.getDefaultInstance()
			.where(Location::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Location> =
	try {
		Realm.getDefaultInstance()
			.where(Location::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Location::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class StockUtiles {

	fun getObjectById(id: String): Stock? =
	try {
		Realm.getDefaultInstance()
			.where(Stock::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Stock> =
	try {
		Realm.getDefaultInstance()
			.where(Stock::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Stock::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class SubcategoryUtiles {

	fun getObjectById(id: String): Subcategory? =
	try {
		Realm.getDefaultInstance()
			.where(Subcategory::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Subcategory> =
	try {
		Realm.getDefaultInstance()
			.where(Subcategory::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Subcategory::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class UserUtiles {

	fun getObjectById(id: String): User? =
	try {
		Realm.getDefaultInstance()
			.where(User::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<User> =
	try {
		Realm.getDefaultInstance()
			.where(User::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(User::class.java)
		.alwaysFalse()
		.findAll()
	}

}

