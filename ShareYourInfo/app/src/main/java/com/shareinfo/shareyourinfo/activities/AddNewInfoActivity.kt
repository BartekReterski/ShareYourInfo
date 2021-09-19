package com.shareinfo.shareyourinfo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.shareinfo.shareyourinfo.R
import android.graphics.PorterDuff

import androidx.core.content.ContextCompat

import android.graphics.drawable.Drawable

class AddNewInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_info)

        //wyłączenie czarnego motywu aplikacji
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar?.title = "Add info"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)

    }
}