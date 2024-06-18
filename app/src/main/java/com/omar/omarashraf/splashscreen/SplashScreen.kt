package com.omar.omarashraf.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.omar.omarashraf.MainActivity
import com.omar.omarashraf.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        showGif()
        intentMainActivity()
    }

    private fun intentMainActivity() {
        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    private fun showGif() {
        val imageView: ImageView = findViewById(R.id.imageView)
        Glide.with(this).load(R.drawable.photo).into(imageView)
    }

}
