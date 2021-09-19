package com.shareinfo.shareyourinfo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.imangazaliev.circlemenu.CircleMenu
import com.shareinfo.shareyourinfo.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //wyłączenie czarnego motywu aplikacji
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val fabCircleMenu= findViewById<CircleMenu>(R.id.fabMenu)
        fabCircleMenu.setOnItemClickListener {
            menuButton->
            when(menuButton){

                0->Toast.makeText(this,"Save",Toast.LENGTH_SHORT).show()
                1->{
                    val intentAddInfo=Intent(this,AddNewInfoActivity::class.java)
                    startActivity(intentAddInfo)
                }

                2->Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show()
            }
        }
    }
}