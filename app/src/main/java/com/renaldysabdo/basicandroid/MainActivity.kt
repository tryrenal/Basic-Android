package com.renaldysabdo.basicandroid

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.renaldysabdo.basicandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var resource : ResourceManagement
    private lateinit var binding : ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resource = ResourceManagement(this@MainActivity)

        binding.btnSave.setOnClickListener {
            //save value
            val username = binding.etUsername.text.toString()
            val age = binding.etAge.text.toString()

            resource.setUserName(username)
            resource.setUserAge(age)

            //get value
            resource.liveSharedPreferences.listenMultiple(listOf(USER_NAME_KEY, USER_AGE_KEY), "").observe(this, {
                with(binding.textResult){
                    visibility = View.VISIBLE
                    text = "hallo ${it[USER_NAME_KEY]}, your age : ${it[USER_AGE_KEY]}"
                }
            })
        }

    }
}