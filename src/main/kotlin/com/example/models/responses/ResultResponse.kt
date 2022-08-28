package com.example.models.responses

import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    val id: Long?,
    val seller: String?,
    val title: String?,
    val description: String?,
    val food_category: String?,
    val image_path: String?,
    val price: Long?,
    val discount: Int?,
//    val rating: Double?,
//    val vote_count: Long?,
    val prepare_duration: Int?,
    val date_created: String?
)