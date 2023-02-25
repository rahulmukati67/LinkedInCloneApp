package com.example.linkedinclone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.linkedinclone.R

class Splash_activity : AppCompatActivity() {
    val time_out = 1000;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)


            finish()
        },time_out.toLong())
    }
}