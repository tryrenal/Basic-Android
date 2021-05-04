package com.renaldysabdo.basicandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.renaldysabdo.basicandroid.databinding.ActivityMainBinding
import com.renaldysabdo.basicandroid.model.Data
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = Data("renaldy", 15, Date())
        binding.data = data

        binding.btnToRootActivity.setOnClickListener {
            toRootActivity()
        }
    }


    fun toRootActivity(){
        startActivity(Intent(this, RootActivity::class.java))
    }
}