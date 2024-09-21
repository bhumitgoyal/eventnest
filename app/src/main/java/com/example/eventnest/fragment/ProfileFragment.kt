package com.example.eventnest.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventnest.Adapters.EventAdapter
import com.example.eventnest.Adapters.EventPassAdapter
import com.example.eventnest.R
import com.example.eventnest.api.RetrofitInstance
import com.example.eventnest.model.Event
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var eventAdapter: EventPassAdapter
    private var userId: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        userId = sharedPreferences.getLong("USER_ID", 0)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_registered_events)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize the adapter with an empty list initially
        eventAdapter = EventPassAdapter(emptyList(), "bhumit","Sex",findNavController())
        recyclerView.adapter = eventAdapter

        // Load registered events for the user
        loadUserRegisteredEvents(userId)

        return view
    }

    private fun loadUserRegisteredEvents(userId: Long) {
        lifecycleScope.launch {
            try {
                // Fetch events from the API
                val events = RetrofitInstance.api.getUserRegisteredEvents(userId)

                // Update the adapter with the fetched events
                eventAdapter.updateEvents(events.toList())
            } catch (e: Exception) {
                // Handle the error (e.g., show a Toast or log it)
                e.printStackTrace() // Log the error for debugging
            }
        }
    }
}
