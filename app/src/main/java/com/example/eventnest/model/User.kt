package com.example.eventnest.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import java.io.Serializable

data class User(
    @Json(name = "id") val id: Long = 0,
    @Json(name = "name") val name: String = "",
    @Json(name = "registrationNumber") val registrationNumber: String = "",
    @Json(name = "email") val email: String = "",
    @Json(name = "phoneNumber") val phoneNumber: String = "",
    @Json(name = "role") val role: UserRole,
    @Json(name = "password") val password: String = "", // Avoid exposing passwords
    @Json(name = "registeredEvents") val registeredEvents: List<Event> = listOf(),
    @Json(name = "clubs") val clubs: List<Club> = listOf()
) : Serializable