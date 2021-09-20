package com.shareinfo.shareyourinfo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imangazaliev.circlemenu.CircleMenu
import com.shareinfo.shareyourinfo.R
import com.shareinfo.shareyourinfo.adapters.NewInfoAdapter
import com.shareinfo.shareyourinfo.models.NewInfoModel
import io.realm.Realm
import io.realm.RealmConfiguration
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var pressedTime:Long=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //wyłączenie czarnego motywu aplikacji
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
     /*   supportActionBar?.hide()*/
        //inicjalizacja bazy danych Realm
        Realm.init(this)


        mainLogic()

    }

    private fun mainLogic(){

        try {
            val fabCircleMenu = findViewById<CircleMenu>(R.id.fab)
            fabCircleMenu.setOnItemClickListener { menuButton ->
                when (menuButton) {

                    0 -> Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                    1 -> {
                        val intentAddInfo = Intent(this, AddNewInfoActivity::class.java)
                        startActivity(intentAddInfo)
                    }

                    2 -> Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
                }
            }

            val config = RealmConfiguration.Builder().name("yourInfo.realm").build()
            val realm = Realm.getInstance(config)

            val readAllRealm=realm.where(NewInfoModel::class.java).findAll()

            val recyclerView=findViewById<RecyclerView>(R.id.recyclerViewInfo)

            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager=LinearLayoutManager(context)
                adapter=NewInfoAdapter(readAllRealm)
            }


        }catch (e:Exception){

            println("Someting was wrong"+e.localizedMessage)
        }




    }

    override fun onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(baseContext, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
        pressedTime = System.currentTimeMillis()
    }
}