package com.coolapps.yo.recyclerviewproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * Home screen of the application.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.list)
        val asyncViewPoolInflater = AsyncViewPoolInflater(this)
        asyncViewPoolInflater.inflateViewsInAdvance(recyclerView,  numberOfViews = 6)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val feedAdapter = FeedAdapter(FeedRepository().getData(), asyncViewPoolInflater)
        recyclerView.addItemDecoration(createItemDecoration(feedAdapter))
        recyclerView.adapter = feedAdapter
    }

    private fun createItemDecoration(feedAdapter: FeedAdapter): StickyHeaderItemDecoration {
        return StickyHeaderItemDecoration(
            resources.getDimensionPixelSize(feedAdapter.sectionHeaderHeightDimenRes),
            feedAdapter.sectionHeaderCallback
        )
    }
}