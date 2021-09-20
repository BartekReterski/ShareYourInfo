package com.shareinfo.shareyourinfo.adapters

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.shareinfo.shareyourinfo.R
import com.shareinfo.shareyourinfo.models.NewInfoModel
import io.realm.Realm
import io.realm.RealmConfiguration

class NewInfoAdapter(private val info: List<NewInfoModel>): RecyclerView.Adapter<NewInfoAdapter.NewInfoViewHolder>() {

    private var checkVisibility:Boolean=false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.your_info_items, parent, false)
        return NewInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return info.size
    }

    override fun onBindViewHolder(holder: NewInfoViewHolder, position: Int) {


        val config = RealmConfiguration.Builder().name("yourInfo.realm").build()
        val realm = Realm.getInstance(config)

        val context=holder.itemView.context

        //wartości listy

        val socialMediaName=info[position].socialMediaName
        val socialMediaNameValue=info[position].socialMediaNameValue
        val colorPickedHex=info[position].colorPickedHex
        val itemVisible= info[position].visibleItem

        holder.textViewSocialMediaName.text=socialMediaName
        holder.textViewSocialMediaNameValue.text=socialMediaNameValue

        //ustawienie koloru do linearLayoutu
        var buttonDrawable: Drawable? = holder.linearLayoutColorPicked.background
        buttonDrawable = DrawableCompat.wrap(buttonDrawable!!)
        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorPickedHex))
        holder.linearLayoutColorPicked.background=buttonDrawable

        if(itemVisible == true){
            holder.imageViewItemVisibility.setImageResource(R.drawable.ic_baseline_visibility_24)

        }else{
            holder.imageViewItemVisibility.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        }



        holder.imageViewItemVisibility.setOnClickListener{

            if(checkVisibility){
                holder.imageViewItemVisibility.setImageResource(R.drawable.ic_baseline_visibility_24)
                !checkVisibility

            }
            if(!checkVisibility){
                holder.imageViewItemVisibility.setImageResource(R.drawable.ic_baseline_visibility_off_24)
               // checkVisibility
            }

        }
        holder.emailMoreButton.setOnClickListener(){



    }
}

class NewInfoViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

    val textViewSocialMediaName: TextView = itemView.findViewById(R.id.textViewSocialMediaName)
    val textViewSocialMediaNameValue: TextView =
        itemView.findViewById(R.id.textViewSocialMediaNameValue)
    val linearLayoutColorPicked: LinearLayout = itemView.findViewById(R.id.linearLayoutChosenColor)
    val imageViewItemVisibility: ImageView = itemView.findViewById(R.id.imageViewVisibility)
    val emailMoreButton: ImageView = itemView.findViewById(R.id.more_button)

}
}