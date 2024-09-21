package com.example.eventnest.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.NavController
import com.example.eventnest.R
import com.example.eventnest.fragment.EventFragmentDirections
import com.example.eventnest.fragment.ProfileFragmentDirections
import com.example.eventnest.model.Event

class EventPassAdapter(
    private var events: List<Event>,
    private val userName: String,
    private val registrationNumber: String,
    private val navController: NavController
) : RecyclerView.Adapter<EventPassAdapter.EventPassViewHolder>() {

    inner class EventPassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventNameTextView: TextView = itemView.findViewById(R.id.tv_event_name)

        init {
            itemView.setOnClickListener {
                // Navigate to EventPassFragment on item click
                val action = ProfileFragmentDirections.actionProfileFragmentToEventPassFragment(events[adapterPosition],userName,registrationNumber)
                navController.navigate(action)
            }
        }


        fun bind(event: Event) {
            eventNameTextView.text = event.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventPassViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventPassViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventPassViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size

    fun updateEvents(newEvents: List<Event>) {
        events = newEvents
        notifyDataSetChanged()
    }
}
