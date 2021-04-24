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

        val btnFadeOut = findViewById<Button>(R.id.btnFadeOut)
        btnFadeOut.setOnClickListener {
            imageLogo.startAnimation(AnimationUtils.loadAnimation(
                    this, R.anim.fade_out
            ))
        }

        val btnSlideLeft = findViewById<Button>(R.id.btnSlideLeft)
        btnSlideLeft.setOnClickListener {
            imageLogo.startAnimation(AnimationUtils.loadAnimation(
                    this, R.anim.slide_left
            ))
        }

        val btnSlideRight = findViewById<Button>(R.id.btnSlideRight)
        btnSlideRight.setOnClickListener {
            imageLogo.startAnimation(AnimationUtils.loadAnimation(
                    this, R.anim.slide_right
            ))
        }

        val btnSlideDown = findViewById<Button>(R.id.btnSlideDown)
        btnSlideDown.setOnClickListener {
            imageLogo.startAnimation(AnimationUtils.loadAnimation(
                    this, R.anim.slide_down
            ))
        }

        val btnSlideUp = findViewById<Button>(R.id.btnSlideUp)
        btnSlideUp.setOnClickListener {
            imageLogo.startAnimation(AnimationUtils.loadAnimation(
                    this, R.anim.slide_up
            ))
        }
    }
}