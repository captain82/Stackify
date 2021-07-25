package com.example.stackify

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.credit_amount_layout.*
import kotlinx.android.synthetic.main.select_bank_layout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val item = findViewById<View>(R.id.mainLayout) as FrameLayout
             val inflator = layoutInflater.inflate(R.layout.credit_amount_layout, item,true)


           /* stub.layoutResource = R.layout.credit_amount_layout
            stub.inflate()*/
            val creditBottomSheetLayout = BottomSheetBehavior.from(bottom_sheet)

            proceedToEmiButton.setOnClickListener(View.OnClickListener {
                creditBottomSheetLayout.state = BottomSheetBehavior.STATE_EXPANDED
            })
            creditBottomSheetLayout.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    // handle onSlide
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            Log.i("collapsed", "true")
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            creditBottomSheetLayout.isDraggable = true
                            creditBottomSheetLayout.isHideable = false
                           /* stub1.layoutResource = R.layout.select_bank_layout
                            stub1.inflate()*/

                            val item = findViewById<View>(R.id.mainLayout) as FrameLayout
                            val inflator = layoutInflater.inflate(R.layout.select_bank_layout, item,true)


                            val selectbank = BottomSheetBehavior.from(select_bank_bottom_sheet)

                            selectBankButton.setOnClickListener {
                                selectbank.state = BottomSheetBehavior.STATE_EXPANDED
                            }
                            selectbank.addBottomSheetCallback(object :
                                BottomSheetBehavior.BottomSheetCallback() {

                                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                                    // handle onSlide
                                }

                                override fun onStateChanged(bottomSheet: View, newState: Int) {
                                    when (newState) {
                                        BottomSheetBehavior.STATE_COLLAPSED -> {
                                            //Log.i("collapsed", "true")
                                        }
                                        BottomSheetBehavior.STATE_EXPANDED -> {
                                            Log.i("pay", "true")
                                           /* stub2.layoutResource = R.layout.pay_layout
                                            stub2.inflate()*/
                                            val item = findViewById<View>(R.id.mainLayout) as FrameLayout
                                            val inflator = layoutInflater.inflate(R.layout.pay_layout, item,true)
                                        }
                                    }
                                }
                            })
                        }
                    }
                }
            })
        }


    }
}
