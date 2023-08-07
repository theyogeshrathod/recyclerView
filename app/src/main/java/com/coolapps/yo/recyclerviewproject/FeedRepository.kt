package com.coolapps.yo.recyclerviewproject

/**
 * Repository to keep the data related to feeds.
 */
class FeedRepository {
    private var dataSource = FeedDataSource()

    fun getData(): List<DataItem> = dataSource.getItems()
}