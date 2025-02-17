package com.example.mvvmuserapplication.model.data.model

data class GetAllUsersResponse(
    val `data`: List<User>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)