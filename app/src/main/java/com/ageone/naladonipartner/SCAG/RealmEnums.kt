package com.ageone.naladoni.SCAG

class Enums {

	// MARK: Enum UserType

	enum class UserType {
		clint, admin
	}

	// MARK: Enum DocumentType

	enum class DocumentType {
		regular, faq
	}

	// MARK: Enum PaymentType

	enum class PaymentType {
		applepay, cash, card, cardtocourier
	}

	// MARK: Enum CategoryType

	enum class CategoryType {
		food, flowers
	}

	// MARK: Enum ProductType

	enum class ProductType {
		from7to25, from25to45, lessthen7
	}

	// MARK: Enum OrderType

	enum class OrderType {
		vipaccess12m, productset1m, vipaccess6m, product1m, product48h, vipaccess3m, product12m, productset12m
	}

	// MARK: Enum AnnounceType

	enum class AnnounceType {
		event, video
	}

}