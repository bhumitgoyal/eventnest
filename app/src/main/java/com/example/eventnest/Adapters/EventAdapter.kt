package com.example.eventnest.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventnest.R
import com.example.eventnest.fragment.EventDetailsFragment
import com.example.eventnest.model.Event

class EventAdapter(private var events: List<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    fun updateEvents(newEvents: Set<Event>) {
        events = newEvents.toList()
        notifyDataSetChanged() // Notify the adapter to refresh the data
    }

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventNameTextView: TextView = itemView.findViewById(R.id.tv_event_name)

        init {
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, EventDetailsFragment::class.java)
                intent.putExtra("EVENT", events[adapterPosition]) // Pass the event object
                context.startActivity(intent)
            }
        }

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
