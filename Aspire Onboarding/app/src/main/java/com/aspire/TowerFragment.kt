package com.aspire

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment

class TowerFragment : Fragment() {

    private lateinit var tower1: FrameLayout
    private lateinit var tower2: FrameLayout
    private lateinit var tower3: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_tower, container, false)

        tower1 = view.findViewById(R.id.tower1)
        tower2 = view.findViewById(R.id.tower2)
        tower3 = view.findViewById(R.id.tower3)

        return view
    }
    private fun startAnimation() {
        tower1.removeAllViews()
        tower2.removeAllViews()
        tower3.removeAllViews()
        buildTower(tower1, listOf(
            R.drawable.block1, R.drawable.avatar1, R.drawable.block2, R.drawable.block3
        ), 0)

        buildTower(tower2, listOf(
            R.drawable.block1, R.drawable.block2, R.drawable.avatar2,
            R.drawable.block3, R.drawable.block4, R.drawable.block5
        ), 300)

        buildTower(tower3, listOf(
            R.drawable.block1, R.drawable.avatar3, R.drawable.block2, R.drawable.block3
        ), 600)

    }
    private fun buildTower(container: FrameLayout, blocks: List<Int>, startDelay: Long) {
        val animators = mutableListOf<Animator>()
        val blockSize = 120
        var stackedHeight = 0

        for ((i, resId) in blocks.withIndex()) {
            val blockView = ImageView(requireContext())
            blockView.setImageResource(resId)

            blockView.scaleType = ImageView.ScaleType.FIT_XY

            val params = FrameLayout.LayoutParams(blockSize, blockSize)
            params.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            params.bottomMargin = stackedHeight
            blockView.layoutParams = params

            val startY = -blockSize.toFloat() * (blocks.size - i)
            blockView.translationY = startY

            container.addView(blockView)

            val animator = ObjectAnimator.ofFloat(blockView, "translationY", startY, 0f)
            animator.duration = 800L
            animator.startDelay = startDelay + (i * 250L)
            animator.interpolator = DecelerateInterpolator()
            animators.add(animator)

            stackedHeight += blockSize
        }

        AnimatorSet().apply {
            playTogether(animators)
            start()
        }
    }
    override fun onResume() {
        super.onResume()
        startAnimation()
    }

}
