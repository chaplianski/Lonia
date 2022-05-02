package com.example.testtask.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtask.R
import com.example.testtask.domain.model.Photos

class PhotoAdapter(photoList: List<Photos>): RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    var listPhoto: List<Photos> = photoList

    interface ShortOnClickListener {
        fun shortClick(photo: Photos)
    }
    interface LongOnClickListener {
        fun longClick(photo: Photos)
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
            longOnClickListener?.longClick(listPhoto[position])
            true
        }
    }

    override fun getItemCount(): Int {
        return listPhoto.size
    }

    fun updateData(list: List<Photos>) {

        val diffCallback = ViewHolder.PhotoDiffCallback(listPhoto, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listPhoto = list as MutableList<Photos>
        diffResult.dispatchUpdatesTo(this)
    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val photoItem = itemView.findViewById<ImageView>(R.id.iv_photo_item)

        fun onBind(photoUri: String){
            Glide.with(itemView.context).load(photoUri)
       //         .error(R.drawable.ic_launcher_background)
                .override(200, 200)
                .centerCrop()
                .into(photoItem)

        }

        class PhotoDiffCallback (val oldList: List<Photos>, val newList: List<Photos>): DiffUtil.Callback(){
            override fun getOldListSize(): Int {
                return oldList.size
            }

            override fun getNewListSize(): Int {
                return newList.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].photoId == newList[newItemPosition].photoId
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val (photoId, imageId, photoUri) = oldList[oldItemPosition]
                val (photoId1, imageId1, photoUri1) = newList[newItemPosition]

                return photoId == photoId1 && imageId == imageId1 && photoUri == photoUri1
            }

            override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
                return super.getChangePayload(oldItemPosition, newItemPosition)
            }

        }

    }
}