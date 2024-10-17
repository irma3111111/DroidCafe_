package com.example.droidcafe

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {
    private lateinit var citySpinner: Spinner
    private lateinit var noteEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi Spinner dan EditText
        citySpinner = findViewById(R.id.city_spinner)
        noteEditText = findViewById(R.id.note_text)

        // Membuat daftar kota untuk Spinner
        val cities = arrayOf("Kota Bandung", "Kota Cimahi", "Kabupaten Bandung")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        citySpinner.adapter = adapter

        // Handler untuk event Spinner
        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCity = parent.getItemAtPosition(position).toString()
                displayToast("Pengiriman ke: $selectedCity")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    fun onRadioButtonClicked(view: View) {
        // Is the button now checked?
        val checked = (view as RadioButton).isChecked
        when (view.id) {
            R.id.sameday -> if (checked) displayToast(getString(R.string.same_day_messenger_service))
            R.id.nextday -> if (checked) displayToast(getString(R.string.next_day_ground_delivery))
            R.id.pickup -> if (checked) displayToast(getString(R.string.pick_up))
        }
    }

    private fun displayToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
