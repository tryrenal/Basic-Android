package com.renaldysabdo.basicandroid

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.renaldysabdo.basicandroid.databinding.ActivityMainBinding
import com.renaldysabdo.basicandroid.other.PickImage
import com.renaldysabdo.basicandroid.other.StatusImage

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pickImage : PickImage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pickImage = PickImage(this@MainActivity)

        binding.imgvPreview.setOnClickListener{
            pickImage.alertImage().observe(this, { response ->
                when (response.status) {
                    StatusImage.CAMERA -> {
                        Toast.makeText(this, "camera", Toast.LENGTH_SHORT).show()
                    }
                    StatusImage.GALLERY -> {
                        Toast.makeText(this, "gallery", Toast.LENGTH_SHORT).show()
                    }
                    else -> Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}