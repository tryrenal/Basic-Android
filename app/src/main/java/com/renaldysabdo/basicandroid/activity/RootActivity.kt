package com.renaldysabdo.basicandroid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.renaldysabdo.basicandroid.R

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
    }
}