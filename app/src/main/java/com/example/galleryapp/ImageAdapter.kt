package com.example.galleryapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ImageAdapter(private var context: Context, private var imageList: ArrayList<Image>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView? = null

        init {
            image = itemView.findViewById(R.id.imageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val infalater = LayoutInflater.from(parent.context)
        val view = infalater.inflate(R.layout.gallery_view, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageModel = imageList[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(context,FullImageView::class.java)
            intent.putExtra("name",imageModel.ImageName)
            intent.putExtra("path",imageModel.ImagePath)
            context.startActivity(intent)
        }


        Glide.with(context)
            .load(imageModel.ImagePath)
            .apply(RequestOptions().centerCrop())
            .into(holder.image!!)

    }

}
