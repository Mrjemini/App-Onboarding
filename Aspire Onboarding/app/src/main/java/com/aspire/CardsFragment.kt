package com.aspire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.aspire.adapter.CardAdapter
import kotlin.math.abs

class CardsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cardAdapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_cards, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewCards)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val cardList = listOf(
            CardItem(
                "4 Months",
                "Sleep well",
                "150 m",
                "150",
                "25+ have joined",
                "This program helps you get a better sleep routine for yourself",
                listOf(
                    "Document the hours of sleep",
                    "Have a wake-up alarm by 5PM",
                    "Have a sleep alarm by 10PM",
                    "No device policy, 30mins before bed"
                )
            ), CardItem(
                "3 Months",
                "Focus time",
                "120 m",
                "120",
                "40+ have joined",
                "Build better focus habits with less screen time",
                listOf(
                    "Set screen time goal",
                    "Focus Pomodoro 25 mins",
                    "No phone during work",
                    "Track daily focus"
                )
            ), CardItem(
                "3 Months",
                "Focus time",
                "120 m",
                "120",
                "40+ have joined",
                "Build better focus habits with less screen time",
                listOf(
                    "Set screen time goal",
                    "Focus Pomodoro 25 mins",
                    "No phone during work",
                    "Track daily focus"
                )
            ), CardItem(
                "3 Months",
                "Focus time",
                "120 m",
                "120",
                "40+ have joined",
                "Build better focus habits with less screen time",
                listOf(
                    "Set screen time goal",
                    "Focus Pomodoro 25 mins",
                    "No phone during work",
                    "Track daily focus"
                )
            ), CardItem(
                "3 Months",
                "Focus time",
                "120 m",
                "120",
                "40+ have joined",
                "Build better focus habits with less screen time",
                listOf(
                    "Set screen time goal",
                    "Focus Pomodoro 25 mins",
                    "No phone during work",
                    "Track daily focus"
                )
            ), CardItem(
                "3 Months",
                "Focus time",
                "120 m",
                "120",
                "40+ have joined",
                "Build better focus habits with less screen time",
                listOf(
                    "Set screen time goal",
                    "Focus Pomodoro 25 mins",
                    "No phone during work",
                    "Track daily focus"
                )
            ), CardItem(
                "3 Months",
                "Focus time",
                "120 m",
                "120",
                "40+ have joined",
                "Build better focus habits with less screen time",
                listOf(
                    "Set screen time goal",
                    "Focus Pomodoro 25 mins",
                    "No phone during work",
                    "Track daily focus"
                )
            )
        )
        cardAdapter = CardAdapter(cardList)
        recyclerView.adapter = cardAdapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        val displayWidth = resources.displayMetrics.widthPixels
        val cardWidth = (displayWidth * 0.8).toInt()
        val sidePadding = ((displayWidth - cardWidth) / 2)
        recyclerView.setPadding(sidePadding, 0, sidePadding, 0)
        recyclerView.clipToPadding = false
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                val centerX = rv.width / 2
                for (i in 0 until rv.childCount) {
                    val child = rv.getChildAt(i)
                    val childCenterX = (child.left + child.right) / 2
                    val distanceFromCenter = abs(centerX - childCenterX)
                    val scale = 1 - (distanceFromCenter.toFloat() / centerX) * 0.2f
                    val finalScale = scale.coerceIn(0.85f, 1f)
                    child.pivotY = 0f
                    child.pivotX = (child.width / 2).toFloat()

                    child.scaleX = finalScale
                    child.scaleY = finalScale
                    child.alpha = finalScale
                }
            }
        })
        recyclerView.post {
            recyclerView.scrollToPosition(0)
        }
        recyclerView.viewTreeObserver.addOnGlobalLayoutListener {
            val itemWidth = (resources.displayMetrics.widthPixels * 0.7).toInt()
            val offset = (resources.displayMetrics.widthPixels - itemWidth) / 2
            recyclerView.scrollBy(-offset, 0)
        }
        return view
    }
}
