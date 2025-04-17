package com.example.raya_edu.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.raya_edu.R
import com.example.raya_edu.view.MyRaya.B_NavFragment
import android.graphics.Color
import android.net.Uri
import com.google.android.material.textfield.TextInputEditText


class Sign_in: Fragment(){
    private var backPressedTime: Long = 0
    private val backPressedInterval: Long = 2000
    private lateinit var logo: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvError = view.findViewById<TextView>(R.id.tvError)
        val userCode = view.findViewById<TextInputEditText>(R.id.usercode)
        val id = view.findViewById<TextInputEditText>(R.id.id)
        val signInButton = view.findViewById<Button>(R.id.sign_in_btn)
        val firstEditTextError = view.findViewById<TextView>(R.id.firstEditTextError)
        val secondEditTextError = view.findViewById<TextView>(R.id.secondEditTextError)
        val logo = view.findViewById<ImageView>(R.id.logo)
        signInButton.setOnClickListener {
            val userCode = userCode.text.toString().trim()
            val id = id.text.toString().trim()
            var isValid = true

            if (userCode.isEmpty()) {
                firstEditTextError.text = "الحقل الاول فارغ"
                firstEditTextError.visibility = View.VISIBLE
                isValid = false
            } else {
                firstEditTextError.visibility = View.GONE
            }

            if (id.isEmpty() || id.length != 14) {
                secondEditTextError.text = "الرقم القومي يجب ان يحتوي علي 14 رقم"
                secondEditTextError.visibility = View.VISIBLE
                isValid = false
            } else {
                secondEditTextError.visibility = View.GONE
            }

            if (!isValid) return@setOnClickListener

            requireActivity().supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, B_NavFragment())
                .addSharedElement(logo, "Logo")
                .commit()
        }



        view.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentTime = System.currentTimeMillis()
                if (currentTime - backPressedTime < backPressedInterval) {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                } else {
                    backPressedTime = currentTime
                    Toast.makeText(requireContext(), "Press back again to exit", Toast.LENGTH_SHORT).show()
                }
            }
        })
        val textView = view.findViewById<TextView>(R.id.terms_condtions)
        val fullText = "بمجرد تسجيلك فأنت توافق علي الشروط والاحكام الخاصة بنا"
        val clickableText = "الشروط والاحكام"
        textView.setTextColor(Color.BLACK)

        val spannableString = SpannableString(fullText)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/document/d/1NdlmMmmH6k2XH4a8O-yMII2YtDt2ru87lvT3NDHvLvk/edit?usp=sharing"))
                widget.context.startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#005e39")
                ds.isUnderlineText = false
            }
        }

        val startIndex = fullText.indexOf(clickableText)
        val endIndex = startIndex + clickableText.length

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#005e39")),
            startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()


    }

private fun hideKeyboard() {
    val inputMethodManager =
        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    view?.let {
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

}