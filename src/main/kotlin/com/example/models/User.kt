package com.example.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * ساختار کاربر فقط برای ثبت نام
 * برای ذخیره داده ها استفاده میشه و البته نمایش اونها
 */
@Serializable
data class User(
    val id: Int = 0,
    val email: String = "",
    val password: String = "",
    val user_type: UserType = UserType.TEST,
    val date_created: String = "",
)

@Serializable
data class Seller(
    val id: Int = 0,
    val user_id: Int = 0,
    val title: String = "",
    val description: String = "",
    val logo: String = "",
    val banner: String = "",
    val seller_category_id: SellerCategory? = null, // رستوران، کافه
    val result_category_id: ResultCategory? = null, // فست فود، ایرانی، بین المللی
    val food_category_id: List<FoodCategory> = emptyList(),
    val state_id: State? = null,
    val city_id: City? = null,
    val location: Location? = null,
    val is_open: Boolean = false,
    val rating: Double = 0.0,
    val vote_count: Int = 0,
    val delivery_fee: Int = 0,
    val delivery_duration: Int = 0
)

@Serializable
data class Customer(
    val id: Int = 0,
    val user_id: Int = 0,
    val first_name: String = "",
    val last_name: String = "",
    val avatar: String = "",
    val phone_number: Long = 0L,
    val email: String = "",
    val address: String = "",
    val sex: UserSex = UserSex.UNKNOWN,
    val birth_date: String = ""
)

/**
 * برای تعیین نوع کاربری که قراره ثبت نام کنه
 */
@Serializable
enum class UserType(val value: Int) {
    TEST(0),

    @SerialName("Admin")
    ADMIN(1),

    @SerialName("Seller")
    SELLER(2),

    @SerialName("Customer")
    CUSTOMER(3)
}

@Serializable
enum class UserSex(val value: Int) {
    UNKNOWN(0),

    @SerialName("Man")
    MAN(1),

    @SerialName("Woman")
    WOMAN(2)
}


