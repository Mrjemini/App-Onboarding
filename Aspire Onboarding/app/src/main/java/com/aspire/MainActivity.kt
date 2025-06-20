package com.aspire

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var indicatorLayout: LinearLayout
    private lateinit var btnNext: ImageButton
    private lateinit var btnSkip: TextView

    private val totalPages = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        indicatorLayout = findViewById(R.id.indicatorLayout)
        btnNext = findViewById(R.id.btnNext)
        btnSkip = findViewById(R.id.btnSkip)

        viewPager.adapter = OnboardingAdapter(this)

        setupIndicators()
        setCurrentIndicator(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCurrentIndicator(position)

                if (position == totalPages - 1) {
                    btnNext.setImageResource(R.drawable.ic_done)
                } else {
                    btnNext.setImageResource(R.drawable.ic_next)
                }
            }
        })

        btnNext.setOnClickListener {
            val current = viewPager.currentItem
            if (current < totalPages - 1) {
                viewPager.currentItem = current + 1
            } else {
                // On last page -> go to login
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
            }
        }

    }

    private fun setupIndicators() {
        indicatorLayout.removeAllViews()
        for (i in 0 until totalPages) {
            val dot = ImageView(this)
            dot.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.indicator_unselected
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            dot.layoutParams = params
            indicatorLayout.addView(dot)
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val count = indicatorLayout.childCount
        for (i in 0 until count) {
            val imageView = indicatorLayout.getChildAt(i) as ImageView
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    this, if (i == index) R.drawable.indicator_selected
                    else R.drawable.indicator_unselected
                )
            )
        }
    }
}
