// MARK: DataBase

package com.ageone.naladoni.SCAG

import com.ageone.naladonipartner.Application.utils

enum class DataBase {

    Category, City, Company, Document, Image, Location, Stock, Subcategory, User;

	companion object DataObjects {
		var url: String = "http://45.132.19.77"
		val headers//todo: val & \n
			get() = mutableMapOf("x-access-token" to utils.variable.token)
	}
}