package com.example.myapplication

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CountryAdapter()
        recyclerView.adapter = adapter

        fetchCountries()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }

    private fun fetchCountries() {
        val service = ApiClient.countryService

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val countries = withContext(Dispatchers.IO) {
                    service.getCountries()
                }
                adapter.setData(countries)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
