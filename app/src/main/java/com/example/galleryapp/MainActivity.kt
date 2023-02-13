package com.example.galleryapp

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var allPictures : ArrayList<Image>? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Storage Permission to access PHOTOS

        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                101
            )

        }
        allPictures = ArrayList()

        if (allPictures!!.isEmpty()) {
            allPictures = getImages()
            binding.rcmain.adapter = ImageAdapter(this, allPictures!!)

            binding.rcmain.layoutManager = GridLayoutManager(this,2)
        }
    }

    private fun getImages(): ArrayList<Image>? {
        var images  = ArrayList<Image>()
        var allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        var projection = arrayOf(MediaStore.Images.ImageColumns.DATA,MediaStore.Images.Media.DISPLAY_NAME)
        var cursor = this@MainActivity.contentResolver.query(allImageUri,projection,null,null,null)

        try {
            cursor!!.moveToFirst()
            do {
                val image = Image()
                image.ImagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                image.ImageName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                images.add(image)
            }while (cursor.moveToNext())
            cursor.close()
        }catch (e:java.lang.Exception) {
            e.printStackTrace()

        }
        return images


    }


}