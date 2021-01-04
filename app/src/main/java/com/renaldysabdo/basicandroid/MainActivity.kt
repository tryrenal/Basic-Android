package com.renaldysabdo.basicandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.renaldysabdo.basicandroid.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var userManager: UserManager

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userManager = UserManager(this@MainActivity)

        //get value
        observeData()

        binding.btnSave.setOnClickListener {
           saveData()
        }
    }

    private fun saveData(){
        //save value
        val username = binding.etUsername.text.toString()
        val age = binding.etAge.text.toString()

        GlobalScope.launch {
            userManager.setDataUser(username, age)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeData(){
        userManager.userNameFlow.asLiveData().observe(this, { dataNama ->
            with(binding.textResultName){
                if (!dataNama.isEmpty()){
                    visibility = View.VISIBLE
                    text = "hello $dataNama"
                }
            }
        })
        userManager.userAgeFlow.asLiveData().observe(this, { dataAge ->
            with(binding.textResultAge){
                if (!dataAge.isEmpty()){
                    visibility = View.VISIBLE
                    text = "your age $dataAge"
                }
            }
        })
    }
}