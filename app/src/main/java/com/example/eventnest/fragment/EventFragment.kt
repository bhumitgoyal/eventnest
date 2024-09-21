package com.example.eventnest.fragment

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
import com.example.eventnest.R
import com.example.eventnest.api.RetrofitInstance
import com.example.eventnest.model.Event
import kotlinx.coroutines.launch


class EventFragment : Fragment() {
    private var userId: Long = 0

    private lateinit var eventAdapter: EventAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_event, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_events)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        eventAdapter = EventAdapter(getDummyEvents())
        recyclerView.adapter = eventAdapter

        fetchEvents()


        return view
    }
    private fun getDummyEvents(): List<Event> {
        return listOf(
            Event(id = 1, name = "Event 1", clubName = "Club A", organizerClub = "Organizer A", location = "Location A", startTime = "2024-09-21T10:00:00", endTime = "2024-09-21T12:00:00"),
            Event(id = 2, name = "Event 2", clubName = "Club B", organizerClub = "Organizer B", location = "Location B", startTime = "2024-09-22T10:00:00", endTime = "2024-09-22T12:00:00"),
            Event(id = 3, name = "Event 3", clubName = "Club C", organizerClub = "Organizer C", location = "Location C", startTime = "2024-09-23T10:00:00", endTime = "2024-09-23T12:00:00")
        )
    }
    private fun fetchEvents() {
        lifecycleScope.launch {
            try {
                // Fetch events from the API
                val events = RetrofitInstance.api.getAllEvents()

                // Update the adapter with the fetched events
                eventAdapter.updateEvents(events.toSet())
            } catch (e: Exception) {
                // Handle the error (e.g., show a Toast or log it)
                e.printStackTrace() // Log the error for debugging
            }
        }
    }



}