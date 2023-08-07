package com.coolapps.yo.recyclerviewproject

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.ArrayList

/**
 * A class responsible to provide data for feed. For now, it provides hard-caded data.
 */
class FeedDataSource {

    fun getItems(): List<DataItem> {
        val items: MutableList<DataItem> = ArrayList()
        val now = Instant.now()

        val waterImageDataItem = DataItem(R.drawable.water, R.string.water_desc, now)
        items.add(waterImageDataItem)

        val yesterday = now.minus(1, ChronoUnit.DAYS)
        val natureImageDataItem = DataItem(R.drawable.nature, R.string.nature_desc, yesterday)
        val sunsetImageDataItem = DataItem(R.drawable.sunset, R.string.sunset_desc, yesterday)
        val mountainImageDataItem =
            DataItem(R.drawable.mountain, R.string.mountains_desc, yesterday)

        items.add(natureImageDataItem)
        items.add(sunsetImageDataItem)
        items.add(mountainImageDataItem)

        val twoDaysAgo = yesterday.minus(1, ChronoUnit.DAYS)
        val pandaImageDataItem = DataItem(R.drawable.panda, R.string.panda_desc, twoDaysAgo)
        val tigerImageDataItem = DataItem(R.drawable.tiger, R.string.tiger_desc, twoDaysAgo)
        val lionImageDataItem = DataItem(R.drawable.lion, R.string.lion_desc, twoDaysAgo)

        items.add(pandaImageDataItem)
        items.add(tigerImageDataItem)
        items.add(lionImageDataItem)

        val threeDaysAgo = twoDaysAgo.minus(1, ChronoUnit.DAYS)
        val parrotImageDataItem =
            DataItem(R.drawable.parrot, R.string.parrot_desc, threeDaysAgo)
        val dogImageDataItem = DataItem(R.drawable.dog, R.string.dog_desc, threeDaysAgo)
        val catImageDataItem = DataItem(R.drawable.cat, R.string.cat_desc, threeDaysAgo)

        items.add(parrotImageDataItem)
        items.add(dogImageDataItem)
        items.add(catImageDataItem)

        return items
    }
}

/**
 * A feed data item.
 */
class DataItem(
    @get:DrawableRes val imageSrc: Int,
    @get:StringRes val descRes: Int,
    val instant: Instant
)