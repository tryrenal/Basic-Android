package com.renaldysabdo.basicandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonId = findViewById<Button>(R.id.btn_bottom_sheet)

        buttonId.setOnClickListener {
            MyBottomSheetDialogFragment().apply {
                show(supportFragmentManager, tag)
            }
        }
    }
}