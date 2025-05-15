package com.mbialowas.layoutdemo_spr_2025

import android.os.Bundle
import android.widget.ArrayAdapter

import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CallLogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_call_log)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val phoneNumbers = intent.getStringArrayListExtra("phoneNumbers") ?: arrayListOf()

        val listView = findViewById<ListView>(R.id.vw_phone_list)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            phoneNumbers
        )
        listView.adapter = adapter
    }
}