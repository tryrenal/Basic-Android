package com.renaldysabdo.basicandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageLogo = findViewById<ImageView>(R.id.imageLogo)

        val btnZoomIn = findViewById<Button>(R.id.btnZoomIn)
        btnZoomIn.setOnClickListener {
            imageLogo.startAnimation(AnimationUtils.loadAnimation(
                    this, R.anim.zoom_in
            ))
        }

        val btnZoomOut = findViewById<Button>(R.id.btnZoomOut)
        btnZoomOut.setOnClickListener {
            imageLogo.startAnimation(AnimationUtils.loadAnimation(
                    this, R.anim.zoom_out
            ))
        }

        val btnFadeIn = findViewById<Button>(R.id.btnFadeIn)
        btnFadeIn.setOnClickListener {
            imageLogo.startAnimation(AnimationUtils.loadAnimation(
                    this, R.anim.fade_in
            ))
        }
    }
}