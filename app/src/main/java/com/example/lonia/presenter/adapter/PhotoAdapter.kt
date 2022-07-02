package com.example.lonia.presenter.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lonia.R
import com.example.lonia.domain.model.Photos

class PhotoAdapter(photoList: List<Photos>): RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    var listPhoto = photoList as MutableList<Photos>

    interface ShortOnClickListener {
        fun shortClick(photo: Photos)
    }
    interface LongOnClickListener {
        fun longClick(photo: Photos, position: Int)
    }

    var shortOnClickListener: ShortOnClickListener? = null
    var longOnClickListener: LongOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listPhoto[position].photoUri)
        holder.itemView.setOnClickListener {
            shortOnClickListener?.shortClick(listPhoto[position])
        }
        holder.itemView.setOnLongClickListener {
            longOnClickListener?.longClick(listPhoto[position], position)
            true
        }
    }

    override fun getItemCount(): Int {
        return listPhoto.size
    }

    fun deliteItem(position: Int){
        listPhoto.removeAt(position)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val photoItem = itemView.findViewById<ImageView>(R.id.iv_photo_item)

        fun onBind(bitmap: Bitmap){
            Glide.with(itemView.context).load(bitmap)
       //         .error(R.drawable.ic_launcher_background)
                .override(200, 200)
                .centerCrop()
                .into(photoItem)

        }



    }
}