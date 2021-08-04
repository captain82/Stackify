package com.example.stackify

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.credit_amount_layout.*
import kotlinx.android.synthetic.main.pay_layout.*
import kotlinx.android.synthetic.main.select_bank_layout.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewStack = Stack<ViewState>()
    private lateinit var parentLayout: FrameLayout
    private lateinit var creditAmountBottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var bankBottomSheet: BottomSheetBehavior<ConstraintLayout>
    private lateinit var paymentBottomSheet: BottomSheetBehavior<ConstraintLayout>
    private var creditAmountOffset = 1000
    private var paymentOffset = creditAmountOffset + 200
    private var bankOffset = paymentOffset + 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parentLayout = findViewById<View>(R.id.mainLayout) as FrameLayout
        addViewToStack(R.layout.credit_amount_layout, "CREDIT-LAYOUT", 1)
        creditAmountBottomSheetBehavior = BottomSheetBehavior.from(proceedToEmiBottomSheet)
        proceedToEmiButton.setOnClickListener {
            setUpCreditAmountBottomSheet()
        }

        creditAmountBottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    pleasePay.setOnClickListener {
                        setUpPaymentAmountBottomSheet()
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })
    }

    private fun setUpPaymentAmountBottomSheet() {
        viewStack.add(ViewState("PAYMENT-LAYOUT", 2))
        paymentBottomSheet.expandedOffset = paymentOffset
        paymentBottomSheet.isFitToContents = false
        paymentBottomSheet.isDraggable = false
        paymentBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
        addViewToStack(R.layout.select_bank_layout, "BANK_LAYOUT", 3)
        bankBottomSheet = BottomSheetBehavior.from(selectBankBottomSheetLayout)
        bankBottomSheet.isDraggable = false

        paymentBottomSheet.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    selectBankButton.setOnClickListener {
                        setUpBankBottomSheet()
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }

    private fun setUpBankBottomSheet() {
        viewStack.add(ViewState("BANK_LAYOUT", 3))
        bankBottomSheet.expandedOffset = bankOffset
        bankBottomSheet.isFitToContents = false
        bankBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun setUpCreditAmountBottomSheet() {
        if (viewStack.isEmpty())
            viewStack.add(ViewState("CREDIT-LAYOUT", 1))
        creditAmountBottomSheetBehavior.expandedOffset = creditAmountOffset
        creditAmountBottomSheetBehavior.isFitToContents = false
        creditAmountBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        creditAmountBottomSheetBehavior.isDraggable = false
        addViewToStack(R.layout.pay_layout, "PAYMENT-LAYOUT", 2)
        paymentBottomSheet = BottomSheetBehavior.from(pleasePayBottomSheet)
        paymentBottomSheet.isDraggable = false
    }

    private fun addViewToStack(layout: Int, viewTag: String, position: Int) {
        layoutInflater.inflate(layout, parentLayout, true)
    }

    private fun popViewFromStack() {
        val poppedView = viewStack.pop().id
        when (poppedView) {
            1 -> {
                creditAmountBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                parentLayout.removeViewAt(2)
            }
            2 -> {
                paymentBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                parentLayout.removeViewAt(3)
            }
            3 -> {
                bankBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    override fun onBackPressed() {
        if (viewStack.isEmpty()) {
            super.onBackPressed()
        } else {
            popViewFromStack()
        }
    }
}