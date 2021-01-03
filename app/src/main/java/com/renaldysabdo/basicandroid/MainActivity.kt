package com.renaldysabdo.basicandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.renaldysabdo.basicandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var resource : ResourceManagement
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resource = ResourceManagement(this@MainActivity)

        binding.btnSave.setOnClickListener {
            //save value
            val username = binding.etUsername.text.toString()
            val age = binding.etAge.text.toString()

            resource.username = username
            resource.age = age
        }

        binding.btnShowData.setOnClickListener {
            val username : String = resource.username
            val age : String = resource.age

            val textGreeting = "Hello, $username your age : $age"

            with(binding.textResult){
                visibility = View.VISIBLE
                text = textGreeting
            }
        }

    }
}