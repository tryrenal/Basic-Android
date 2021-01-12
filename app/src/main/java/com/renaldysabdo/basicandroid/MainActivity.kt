package com.renaldysabdo.basicandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.renaldysabdo.basicandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set theme to default setting device
        binding.btnDefault.setOnClickListener(this)

        //set theme to night
        binding.btnNight.setOnClickListener(this)

        //set theme to light
        binding.btnLight.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnDefault -> {
               val theme = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                implThemeChoosen(theme)
            }
            R.id.btnLight -> {
               val theme = AppCompatDelegate.MODE_NIGHT_NO
                implThemeChoosen(theme)
            }
            R.id.btnNight -> {
                val theme = AppCompatDelegate.MODE_NIGHT_YES
                implThemeChoosen(theme)
            }
        }
    }

    private fun implThemeChoosen(theme: Int) {
        //setting implementasi theme choosen
        AppCompatDelegate.setDefaultNightMode(theme)
    }
}