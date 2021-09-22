package com.shareinfo.shareyourinfo .activities

import android.R.attr
import android.app.ActionBar.DISPLAY_SHOW_CUSTOM
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.shareinfo.shareyourinfo.R
import android.graphics.PorterDuff

import androidx.core.content.ContextCompat

import android.graphics.drawable.Drawable
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.google.android.material.textfield.TextInputEditText
import java.security.AccessController.getContext
import android.R.attr.button
import android.content.Intent

import androidx.core.graphics.drawable.DrawableCompat
import com.shareinfo.shareyourinfo.activities.MainActivity
import com.shareinfo.shareyourinfo.models.NewInfoModel
import io.realm.Realm
import io.realm.RealmConfiguration


class AddNewInfoActivity : AppCompatActivity() {

    private lateinit var colorPickedHex:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_info)

        //wyłączenie czarnego motywu aplikacji
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar?.title = "Add info"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)

        //inicjalizacja bazy danych Realm
        Realm.init(this)


        addNewInfoAboutYou()
    }


    private fun addNewInfoAboutYou() {

        try {

            val socialMediaName = findViewById<TextInputEditText>(R.id.social_media)
            val socialMediaValue = findViewById<TextInputEditText>(R.id.social_value)
            val colorPicker = findViewById<ImageView>(R.id.color_picker)
            val addInfoBtn = findViewById<Button>(R.id.send_info_btn)

            colorPicker.setOnClickListener {

                MaterialColorPickerDialog
                    .Builder(this)
                    .setTitle("Pick color")
                    .setColorShape(ColorShape.SQAURE)
                    .setColorSwatch(ColorSwatch._300)
                    .setColorListener { color, colorHex ->

                        //ustawienie koloru w przykładowym ImageView
                        var buttonDrawable: Drawable? = colorPicker.background
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable!!)
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorHex))
                        colorPicker.background = buttonDrawable

                        colorPickedHex=colorHex
                    }
                    .show()
            }

            val config = RealmConfiguration.Builder().name("yourInfo.realm").build()
            val realm = Realm.getInstance(config)
            val readAllRealm = realm.where(NewInfoModel::class.java).findAll()
            readAllRealm.forEach { book ->
                println("BAZA DANYCH: ${book.socialMediaName} :${book.socialMediaNameValue} :${book.colorPickedHex} :${book.visibleItem}")
            }

            addInfoBtn.setOnClickListener{


                realm.beginTransaction()

                //obostrzenie przeciwko duplikatom i nie możliwość dodawania ich do bazy przesłanych książek
                val realmObject= realm.where(NewInfoModel::class.java)
                    .equalTo("socialMediaName",socialMediaName.text.toString())
                    .findFirst()

                if(realmObject==null) {
                    val newInfo = realm.createObject(NewInfoModel::class.java)
                    newInfo.socialMediaName=socialMediaName.text.toString()
                    newInfo.socialMediaNameValue=socialMediaValue.text.toString()
                    newInfo.colorPickedHex=colorPickedHex
                    newInfo.visibleItem=true
                    realm.commitTransaction()

                    Toast.makeText(this, "Info added ", Toast.LENGTH_LONG).show()

                    //przekierowanie do głównej aktywności po udanym procesie dodania rekordu do bazy danych
                    val intentFinishActivity= Intent(this, MainActivity::class.java)
                    intentFinishActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intentFinishActivity)
                    finish()

                }else{

                    Toast.makeText(this,"There is already in database item with this name",Toast.LENGTH_LONG).show()
                    realm.commitTransaction()
                }

            }



        }catch (e:Exception){

        }
    }
}