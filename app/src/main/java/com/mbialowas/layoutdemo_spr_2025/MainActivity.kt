package com.mbialowas.layoutdemo_spr_2025

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest
import android.provider.AlarmClock
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.core.os.registerForAllProfilingResults

class MainActivity : AppCompatActivity() {

    // constants
    val CALL_REQUEST_CODE = 1
    private val calledNumbers = arrayListOf<String>() // list is used to track phone numbers

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
        val btnLinear: Button = findViewById<Button>(R.id.btn_linear)
        val btnVertical: Button = findViewById<Button>(R.id.btn_vertical)
        val btnRelative: Button = findViewById<Button>(R.id.btn_relative)
        val btnConstraint: Button = findViewById<Button>(R.id.btn_constraint)


        // passing control to different apps
        val btnApp: Button = findViewById<Button>(R.id.btn_app)

        btnLinear.setOnClickListener {
            val intent = Intent(this, LinearActivity::class.java)
            startActivity(intent)
        }
        btnVertical.setOnClickListener {
            Toast.makeText(
                this,
                "Main Activity is already usingthe Vertical Layout",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnRelative.setOnClickListener {
            val intent = Intent(this, RelativeActivity::class.java)
            startActivity(intent)
        }
        btnConstraint.setOnClickListener {
            val intent = Intent(this, ConstraintActivity::class.java)
            startActivity(intent)
        }

        btnApp.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://cnn.com")
            }
            startActivity(intent)
        }
        initialize()
    }
    private fun initialize(){

        val callButton = findViewById<Button>(R.id.btn_phone)//
        val callLogButton = findViewById<Button>(R.id.btn_call_log)
        val contactsButton = findViewById<Button>(R.id.btn_contacts)
        val galleryButton = findViewById<Button>(R.id.btn_photo)
        val cameraButton = findViewById<Button>(R.id.btn_camera)
        val alarmButton = findViewById<Button>(R.id.btn_alarm)


        callButton.setOnClickListener { callButton() }
        galleryButton.setOnClickListener { galleryButton() }
        callLogButton.setOnClickListener { callLogButton() }
        cameraButton.setOnClickListener { cameraButton() }
        alarmButton.setOnClickListener { alarmButton() }
        contactsButton.setOnClickListener { contactsButton() }

    }
    private fun contactsButton(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = ContactsContract.Contacts.CONTENT_TYPE
        startActivity(intent)

    }
    private fun cameraButton(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }
    private fun alarmButton(){
        val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
        startActivity(intent)
    }

    private fun galleryButton(){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("content://media/external/images/media/")
        startActivity(intent)
    }
    private fun callButton(){
        calledNumbers.add("+14311234567")
        calledNumbers.add("+14312222222")
        calledNumbers.add("+14313333333")
        calledNumbers.add("+14314444444")
        calledNumbers.add("+14315555555")

        val phoneNumber:String = "+36363563454"
        makePhoneCall(phoneNumber)
    }
    private fun makePhoneCall(phoneNumber: String) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is granted, proceed with the phone call
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(callIntent)
        } else {
            // Request the CALL_PHONE permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                CALL_REQUEST_CODE
            )
        }
    }

    // open call log
    private fun callLogButton(){
        val intent = Intent(this, CallLogActivity::class.java)
        intent.putStringArrayListExtra("phoneNumbers", calledNumbers)
        startActivity(intent)
    }

}