package com.renaldysabdo.basicandroid.other

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class PickImage(@NonNull val context: Context) {

    fun alertImage(): LiveData<ResourceImage<StatusImage>> {
        val choosedImage = MutableLiveData<ResourceImage<StatusImage>>()
        val items = arrayOf<CharSequence>("Take Photo", "Choose from Library", "Cancel")
        val builder = AlertDialog.Builder(context)
        builder.setItems(items) { dialog, item ->
            if (items[item] == "Take Photo") {
                val request = requestStoragePermision(context, true)
                if (request != 0 || request == Constanta.cameraID) {
                    choosedImage.value = ResourceImage.Camera(StatusImage.CAMERA)
                }
            } else if (items[item] == "Choose from Library") {
                val request = requestStoragePermision(context, false)
                if (request != 0 || request == Constanta.galleryID) {
                    choosedImage.value = ResourceImage.Gallery(StatusImage.GALLERY)
                }
            } else if (items[item] == "Cancel") {
                dialog.dismiss()
            }
        }
        builder.show()
        return choosedImage
    }

    //check permission
    private fun requestStoragePermision(context: Context, isCamera: Boolean): Int {
        //return
        var type = 0
        Dexter.withActivity(context as Activity?)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report != null) {
                        if (report.areAllPermissionsGranted()) {
                            type = if (isCamera) {
                                Constanta.cameraID
                            } else {
                                Constanta.galleryID
                            }
                        }

                        //check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied) {
                            showSettingDialog(context)
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            })
            .withErrorListener {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
            }
            .onSameThread()
            .check()

        return type
    }

    //alert dialog with setting option
    private fun showSettingDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Need Permissions")
        builder.setMessage(
            "This app needs permission to use this feature. You can grant them in app settings."
        )
        builder.setPositiveButton("GOTO SETTINGS") { dialog, _ ->
            dialog.cancel()
            openSettings(context)
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    //navigation user to app setting
    private fun openSettings(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        (context as Activity).startActivityForResult(intent, 101)
    }

    //new file for image
    @SuppressLint("SimpleDateFormat")
    fun createImageFile(context: Context): File? {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val mFileName = "JPEG_" + timeStamp + "_"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(mFileName, ".jpg", storageDir)
    }

    //get real path image
    @SuppressLint("Assert")
    fun getRealPathFromUri(contentUri: Uri, context: Context): String {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            assert(cursor != null)
            val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(columnIndex)
        } finally {
            cursor?.close()
        }
    }

}