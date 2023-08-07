package com.coolapps.yo.recyclerviewproject

import androidx.recyclerview.widget.RecyclerView
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date

/**
 * A RecyclerView.Adapter which manages feed items for the RecyclerView.
 */
class FeedAdapter(
    private val dataItems: List<DataItem>,
    private val asyncViewPoolInflater: AsyncViewPoolInflater
) : RecyclerView.Adapter<FeedCardViewHolder>() {

    @SuppressLint("SimpleDateFormat")
    var formatter = SimpleDateFormat("dd MMM yyyy")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedCardViewHolder {
        val preInflatedView = asyncViewPoolInflater.getAndRemoveView()
        return if (preInflatedView != null) {
            // Use the pre-inflated view to create ViewHolder.
            FeedCardViewHolder(preInflatedView)
        } else {
            // If we do not find pre-inflated view, inflate a new view
            // and create ViewHolder.
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.feed_image_item_layout, parent, false)
            FeedCardViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: FeedCardViewHolder, position: Int) {
        holder.bindData(dataItems[position])
    }

    override fun getItemCount() = dataItems.size

    val sectionHeaderHeightDimenRes: Int
        get() = R.dimen.section_header_height

    val sectionHeaderCallback: SectionHeaderCallback = object : SectionHeaderCallback {
        override fun isSection(position: Int): Boolean {
            return position == 0 ||
                    dataItems[position].instant != dataItems[position - 1].instant
        }

        override fun getSectionHeader(position: Int): CharSequence {
            val date = Date.from(dataItems[position].instant)
            return formatter.format(date)
        }
    }
}

/**
 * View holder to hold the feed image items.
 */
class FeedCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.image)
    private val descView: TextView = itemView.findViewById(R.id.desc)

    fun bindData(item: DataItem) {
        imageView.setImageResource(item.imageSrc)
        descView.setText(item.descRes)
    }
}