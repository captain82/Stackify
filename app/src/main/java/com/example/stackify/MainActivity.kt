package com.example.stackify

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.credit_amount_layout.*
import kotlinx.android.synthetic.main.select_bank_layout.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewStack = Stack<ViewState>()
    private lateinit var parentLayout: FrameLayout
    private lateinit var creditAmountBottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var bankBottomSheet: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parentLayout = findViewById<View>(R.id.mainLayout) as FrameLayout
        addViewToStack(R.layout.credit_amount_layout, "CREDIT-LAYOUT", 1)
        creditAmountBottomSheetBehavior = BottomSheetBehavior.from(proceedToEmiBottomSheet)

        proceedToEmiButton.setOnClickListener {
            creditAmountBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            addViewToStack(R.layout.select_bank_layout, "BANK-LAYOUT", 2)
            bankBottomSheet = BottomSheetBehavior.from(selectBankBottomSheetLayout)
        }

       /* selectBankButton.setOnClickListener {
            bankBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            addViewToStack(R.layout.pay_layout, "PAY-LAYOUT", 3)
        }*/

        /*creditBottomSheetLayout.addBottomSheetCallback(object :
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
                        *//* stub1.layoutResource = R.layout.select_bank_layout
                         stub1.inflate()*//*

                        layoutInflater.inflate(R.layout.select_bank_layout, parentLayout, true)

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
                                            *//* stub2.layoutResource = R.layout.pay_layout
                                             stub2.inflate()*//*
                                            layoutInflater.inflate(
                                                R.layout.pay_layout,
                                                parentLayout,
                                                true
                                            )
                                            Thread.sleep(2000)
                                            parentLayout.removeViewAt(2)
                                        }
                                    }
                                }
                        })
                    }
                }
            }
        })*/
    }

    private fun addViewToStack(layout: Int, viewTag: String, position: Int) {
        layoutInflater.inflate(layout, parentLayout, true)
        viewStack.add(ViewState(viewTag, position))
    }

    private fun popViewFromStack() {
        val poppedView = viewStack.pop().id
        parentLayout.removeViewAt(poppedView)
    }

    override fun onBackPressed() {
        if (viewStack.isEmpty()) {
            super.onBackPressed()
        } else {
            popViewFromStack()
        }
    }
}