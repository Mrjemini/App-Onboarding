package com.aspire

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> IntroFragment()
            1 -> CardsFragment()
            2 -> TowerFragment()
            3 -> CoinsFragment()
            else -> IntroFragment()
        }
    }
}