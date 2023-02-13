package com.example.galleryapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.galleryapp.databinding.ActivityFullImageViewBinding

class FullImageView : AppCompatActivity() {
    private lateinit var binding: ActivityFullImageViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullImageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val path = intent.getStringExtra("path")

        supportActionBar!!.hide()

        Glide.with(this).load(path).into(binding.imageView2)
    }
}