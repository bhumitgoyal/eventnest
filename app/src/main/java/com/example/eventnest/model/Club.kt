package com.example.eventnest.model

import com.squareup.moshi.Json
import java.io.Serializable

data class Club(
    @Json(name = "id") val id: Long = 0,
    @Json(name = "name") val name: String = "",
    @Json(name = "description") val description: String = "",
    @Json(name = "cluborchap") val cluborchap: String = "",

    @Json(name = "members") val members: List<User> = listOf(), // List of users who are members
    @Json(name = "events") val events: List<Event> = listOf(), // List of events associated with the club
    @Json(name = "admin") val admin: User // The admin of the club
) : Serializable
