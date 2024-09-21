package com.example.eventnest.api

import com.example.eventnest.model.Event
import com.example.eventnest.model.LoginRequest
import com.example.eventnest.model.LoginResponse
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {
    @GET("/{userId}/{eventId}")
    suspend fun generateQRCode(@Path("userId") userId: Long,@Path("eventId") eventId: Long):Response<ByteArray>

    @GET("/users/{id}/events")
    suspend fun getUserRegisteredEvents(@Path("id") userId: Long): Set<Event>

    @POST("/events/{eventId}/register")
    suspend fun registerUserForEvent(
        @Path("eventId") eventId: Long,
        @Query("userId") userId: Long
    ): Response<ResponseBody>

    @GET("events")
    suspend fun getAllEvents(): List<Event>

    @POST("/users/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}
