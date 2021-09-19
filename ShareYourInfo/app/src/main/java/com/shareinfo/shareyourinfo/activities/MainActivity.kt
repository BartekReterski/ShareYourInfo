package com.shareinfo.shareyourinfo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.imangazaliev.circlemenu.CircleMenu
import com.shareinfo.shareyourinfo.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fabCircleMenu= findViewById<CircleMenu>(R.id.fabMenu)
        fabCircleMenu.setOnItemClickListener {
            menuButton->
            when(menuButton){

                0->Toast.makeText(this,"Save",Toast.LENGTH_SHORT).show()
                1->Toast.makeText(this,"Add",Toast.LENGTH_SHORT).show()
                2->Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show()
            }
        }
    }
}