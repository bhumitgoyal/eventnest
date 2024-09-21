package com.example.eventnest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.lifecycle.lifecycleScope
import com.example.eventnest.R
import com.example.eventnest.api.ApiService
import com.example.eventnest.model.Event
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventPassFragment : Fragment() {
    private lateinit var eventNameTextView: TextView
    private lateinit var userNameTextView: TextView
    private lateinit var registrationNumberTextView: TextView
    private lateinit var qrCodeImageView: ImageView
    private lateinit var apiService: ApiService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_event_pass, container, false)

        eventNameTextView = view.findViewById(R.id.event_name)
        userNameTextView = view.findViewById(R.id.user_name)
        registrationNumberTextView = view.findViewById(R.id.registration_number)
        qrCodeImageView = view.findViewById(R.id.qr_code_image)

        // Initialize Retrofit and ApiService
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        // Retrieve the event details from arguments
        val event = EventPassFragmentArgs.fromBundle(requireArguments()).selectedEvent
        val sharedPreferences = requireActivity().getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val userId = sharedPreferences.getLong("USER_ID", 0)

        // Fetch user details by ID
        fetchUserDetails(userId)

        event?.let {
            eventNameTextView.text = it.name

            // Load the image using Glide
            val qrCodeImageUrl = "https://i.postimg.cc/mDRLjt63/user-6-event-6.png"
            Glide.with(requireContext())
                .load(qrCodeImageUrl)
                .into(qrCodeImageView)
        }

        return view
    }

    private fun fetchUserDetails(userId: Long) {
        lifecycleScope.launch {
            val response = apiService.getUserById(userId)
            if (response.isSuccessful) {
                response.body()?.let { user ->
                    userNameTextView.text = user.name
                    registrationNumberTextView.text = user.registrationNumber
                }
            } else {
                // Handle error (e.g., show a message or default values)
                userNameTextView.text = "User"
                registrationNumberTextView.text = "N/A"
            }
        }
    }
}
