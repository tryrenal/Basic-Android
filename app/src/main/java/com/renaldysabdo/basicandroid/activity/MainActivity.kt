package com.renaldysabdo.basicandroid.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.renaldysabdo.basicandroid.databinding.ActivityMainBinding
import com.renaldysabdo.basicandroid.model.Data
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = Data(age =  15, date = Date(), name = null)
        binding.data = data

        binding.btnToRootActivity.setOnClickListener {
            toRootActivity()
        }
    }


    fun toRootActivity(){
        startActivity(Intent(this, RootActivity::class.java))
    }
}