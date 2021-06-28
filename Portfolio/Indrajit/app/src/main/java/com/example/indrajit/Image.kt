package com.example.indrajit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class Image : AppCompatActivity() {
    lateinit var imgdesc: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        imgdesc=findViewById(R.id.imgdesc)
        val url = intent.getStringExtra("url");
        Glide.with(this).load(url)
            .error(R.drawable.notfound)
            .into(imgdesc);

    }
}