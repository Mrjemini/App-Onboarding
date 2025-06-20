package com.aspire

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import kotlin.random.Random

class CoinsFragment : Fragment() {

    private lateinit var rootLayout: RelativeLayout
    private val handler = Handler(Looper.getMainLooper())
    private val coinBaseSize = 200

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_coins, container, false)
        rootLayout = view.findViewById(R.id.rootLayout)
        return view
    }

    override fun onResume() {
        super.onResume()
        startCoinRain()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacksAndMessages(null)
    }

    private fun startCoinRain() {
        val runnable = object : Runnable {
            override fun run() {
                createFallingCoin()
                handler.postDelayed(this, 400)
            }
        }
        handler.post(runnable)
    }

    private fun createFallingCoin() {
        val coin = ImageView(requireContext())
        coin.setImageResource(R.drawable.ic_coins)
        val sizePx = Random.nextInt(coinBaseSize, coinBaseSize + 60)
        val params = RelativeLayout.LayoutParams(sizePx, sizePx)
        val maxX = rootLayout.width - sizePx
        val randomX = Random.nextInt(0, if (maxX > 0) maxX else 1)
        params.leftMargin = randomX
        params.topMargin = -sizePx
        coin.layoutParams = params
        rootLayout.addView(coin)
        val screenHeight = rootLayout.height + 200
        val duration = Random.nextLong(2500, 5000)

        val fallAnim = ObjectAnimator.ofFloat(coin, "translationY", 0f, screenHeight.toFloat()).apply {
            this.duration = duration
            interpolator = LinearInterpolator()
            start()
        }
        fallAnim.addListener(object : android.animation.Animator.AnimatorListener {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                rootLayout.removeView(coin)
            }
            override fun onAnimationStart(animation: android.animation.Animator) {}
            override fun onAnimationCancel(animation: android.animation.Animator) {}
            override fun onAnimationRepeat(animation: android.animation.Animator) {}
        })
    }
}
