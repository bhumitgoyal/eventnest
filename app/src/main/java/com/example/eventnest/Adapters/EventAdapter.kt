package com.example.eventnest.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.NavController
import com.example.eventnest.R
import com.example.eventnest.fragment.EventFragmentDirections
import com.example.eventnest.model.Event

class EventAdapter(
    private var events: List<Event>,
    private val navController: NavController
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    // Update events list
    fun updateEvents(newEvents: Set<Event>) {
        events = newEvents.toList()
        notifyDataSetChanged()
    }

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventNameTextView: TextView = itemView.findViewById(R.id.tv_event_name)

        init {
            itemView.setOnClickListener {
                // Ensure that the adapter position is valid before accessing the events list
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val action = EventFragmentDirections.actionEventsFragmentToEventDetailsFragment(events[position])
                    navController.navigate(action)
                }
            }
        }

        // Bind data to views
        fun bind(event: Event) {
            eventNameTextView.text = event.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size
}
