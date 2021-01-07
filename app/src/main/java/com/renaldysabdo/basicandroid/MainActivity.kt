package com.renaldysabdo.basicandroid

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.renaldysabdo.basicandroid.databinding.ActivityMainBinding
import com.renaldysabdo.basicandroid.other.PickImage
import com.renaldysabdo.basicandroid.other.StatusImage
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pickImage : PickImage

    private lateinit var filePhoto : File

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
        filePhoto = getPhotoFile("photo.jpg")
        val providerFile = FileProvider.getUriForFile(this, "com.renaldysabdo.basicandroid.provider", filePhoto)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, providerFile)
        startActivityForResult(intent, 1)
    }

    private fun getPhotoFile(fileName : String) : File {
        val directoryStorage = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", directoryStorage)
    }

    private fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == 1){
                val takenPhoto = BitmapFactory.decodeFile(filePhoto.absolutePath)
                binding.imgvPreview.setImageBitmap(takenPhoto)
            }
            else if (requestCode == 2){
                binding.imgvPreview.setImageURI(data?.data)
            }
        }
    }

}