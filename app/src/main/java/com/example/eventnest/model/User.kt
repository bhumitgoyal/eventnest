package com.example.eventnest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("registrationNumber") val registrationNumber: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("phoneNumber") val phoneNumber: String = "",
    @SerializedName("role") val role: UserRole,
    @SerializedName("password") val password: String = "", // Avoid exposing passwords
    @SerializedName("registeredEvents") val registeredEvents: List<Event> = listOf(),
    @SerializedName("clubs") val clubs: List<Club> = listOf()
): Serializable
