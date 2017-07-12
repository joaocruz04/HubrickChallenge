package com.hubrickchallenge.android.feed



/**
 * Created by joaocruz04 on 10/07/2017.
 */
interface FeedView {

    fun updateData(itemsCount: Int)
    fun showCountTooltip(count: Int, duration: Long)
    fun hideCountTooltip()
    fun showTop()
    fun getCurrentPositionAndOffset(): Pair<Int, Int>
}