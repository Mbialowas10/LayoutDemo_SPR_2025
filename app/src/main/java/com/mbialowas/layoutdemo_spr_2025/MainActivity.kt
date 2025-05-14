package com.mbialowas.layoutdemo_spr_2025

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_linear)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        } // end of the callback

        // register our controls
        val btnLinear:Button = findViewById<Button>(R.id.btn_linear)
        val btnVertical:Button = findViewById<Button>(R.id.btn_vertical)
        val btnRelative:Button = findViewById<Button>(R.id.btn_relative)
        val btnConstraint:Button = findViewById<Button>(R.id.btn_constraint)

        btnLinear.setOnClickListener {
            val intent = Intent(this, LinearActivity::class.java)
            startActivity(intent)
        }
        btnVertical.setOnClickListener {
            Toast.makeText(this,"Main Activity is already usingthe Vertical Layout", Toast.LENGTH_SHORT).show()
        }

        btnRelative.setOnClickListener {
            val intent = Intent(this, RelativeActivity::class.java)
            startActivity(intent)
        }
        btnConstraint.setOnClickListener {
            val intent = Intent(this, ConstraintActivity::class.java)
            startActivity(intent)
        }


    }

}