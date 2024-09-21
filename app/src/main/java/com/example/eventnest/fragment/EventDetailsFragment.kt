package com.example.eventnest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.eventnest.R

class EventDetailsFragment : Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_event_details, container, false)
        val event = EventDetailsFragmentArgs.fromBundle(requireArguments()).selectedEvent

        // Populate the event details in the view
        val eventNameTextView = view.findViewById<TextView>(R.id.tv_event_name)
        val eventDateTextView = view.findViewById<TextView>(R.id.tv_event_date)
        val eventDescriptionTextView = view.findViewById<TextView>(R.id.tv_event_description)

        eventNameTextView.text = event.name
        eventDateTextView.text = event.startTime // Update as needed
        eventDescriptionTextView.text = event.location // Update as needed

        return view
    }
}
