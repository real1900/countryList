package com.example.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    private var countries: List<Country> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val codeTextView: TextView = itemView.findViewById(R.id.codeTextView)
        private val capitalTextView: TextView = itemView.findViewById(R.id.capitalTextView)

        fun bind(country: Country) {
            val countryName = country.name ?: "N/A"
            val regionName = country.region ?: "N/A"
            nameTextView.text = buildString {
                append(countryName)
                append(", ")
                append(regionName)
            }
            codeTextView.text = country.code ?: "N/A"
            capitalTextView.text = country.capital ?: "N/A"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return countries.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(countries: List<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }
}
