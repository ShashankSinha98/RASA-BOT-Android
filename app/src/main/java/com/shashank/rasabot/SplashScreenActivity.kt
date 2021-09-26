package com.shashank.rasabot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar)
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, ChatBotActivity::class.java))
        finish()
    }

}