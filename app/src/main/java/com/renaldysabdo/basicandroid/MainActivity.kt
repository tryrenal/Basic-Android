package com.renaldysabdo.basicandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSheet = findViewById<Button>(R.id.btn_open_sheet)
        val bottomSheetLayout = findViewById<LinearLayout>(R.id.bottomSheet)

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                buttonSheet.text = when(newState){
                    BottomSheetBehavior.STATE_EXPANDED -> "Close"
                    BottomSheetBehavior.STATE_COLLAPSED -> "Open"
                    else -> ""
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })

        buttonSheet.setOnClickListener {
            val state =
                if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                    BottomSheetBehavior.STATE_COLLAPSED
                } else {
                    BottomSheetBehavior.STATE_EXPANDED
                }
            bottomSheetBehavior.state = state
        }
    }
}