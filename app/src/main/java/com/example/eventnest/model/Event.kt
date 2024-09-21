package com.example.eventnest.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Event(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("clubName") val clubName: String? = null,
    @SerializedName("organizerClub") val organizerClub: String = "",
    @SerializedName("location") val location: String = "",
    @SerializedName("startTime") val startTime: String = "",
    @SerializedName("endTime") val endTime: String = "",
    @SerializedName("registeredUsers") val registeredUsers: List<User> = emptyList()
) : Serializable