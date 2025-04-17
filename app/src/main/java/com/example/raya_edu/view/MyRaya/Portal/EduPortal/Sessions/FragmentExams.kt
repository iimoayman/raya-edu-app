package com.example.raya_edu.view.MyRaya.Portal.EduPortal.Sessions

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import com.example.raya_edu.R

class Fragment_Exams : Fragment(R.layout.fragment_exams) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val examItem = view.findViewById<ConstraintLayout>(R.id.exam_item_1)
        val questionsList = view.findViewById<LinearLayout>(R.id.questions_list)
        val arrowIcon = view.findViewById<ImageView>(R.id.arrow_icon)

        var isExpanded = false

        examItem.setOnClickListener {
            if (!isExpanded) {
                expandView(questionsList)
                arrowIcon.rotation = -90f
            } else {
                collapseView(questionsList)
                arrowIcon.rotation = 0f
            }
            isExpanded = !isExpanded
        }


    }
    fun expandView(view: View) {
        view.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED)
        val targetHeight = view.measuredHeight

        val animator = ValueAnimator.ofInt(0,targetHeight).apply{
            duration = 300
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animation ->
                view.layoutParams.height = animation.animatedValue as Int
                view.requestLayout()
            }
        }
        view.visibility = View.VISIBLE
        animator.start()
    }

    fun collapseView(view: View) {
        val initialHeight = view.height
        val animator = ValueAnimator.ofInt(initialHeight, 0).apply {
            duration = 300
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animation ->
                view.layoutParams.height = animation.animatedValue as Int
                view.requestLayout()
            }
            doOnEnd {
                view.visibility = View.GONE
            }
        }
        animator.start()
    }

}
