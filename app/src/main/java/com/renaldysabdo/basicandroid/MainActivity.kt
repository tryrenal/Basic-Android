package com.renaldysabdo.basicandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.renaldysabdo.basicandroid.databinding.ActivityMainBinding
import com.renaldysabdo.basicandroid.other.PickImage
import com.renaldysabdo.basicandroid.other.StatusImage
import java.io.File


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
                        openCamera()
                    }
                    StatusImage.GALLERY -> {
                        openGallery()
                    }
                    else -> Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun openCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val f = File(Environment.getExternalStorageDirectory(), "temp.jpg")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f))
        startActivityForResult(intent, 1)
    }

    private fun openGallery(){
        val intent = Intent(
            Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(intent, 2)
    }

}