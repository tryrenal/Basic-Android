package com.renaldysabdo.basicandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.renaldysabdo.basicandroid.databinding.ActivityMainBinding
import com.renaldysabdo.basicandroid.model.Data

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = Data("renaldy", 16)
        binding.data = data
    }

    private fun settingText(){
        binding.tvGreeting.text = "Renaldy Sabdo Jati"
    }
}