package com.example.krisi.mywardrobe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button

class MainActivityKotlin : AppCompatActivity() {

    var welcomeMessage:TextView? = null
    val next:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        welcomeMessage = findViewById<TextView>(R.id.message);

    }
}
