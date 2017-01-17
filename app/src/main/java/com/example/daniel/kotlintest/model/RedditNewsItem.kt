package com.example.daniel.kotlintest.model

import com.example.daniel.kotlintest.adapter.AdapterConstants
import com.example.daniel.kotlintest.adapter.ViewType

/**
 * Created by Daniel on 16/01/2017.
 */

data class RedditNewsItem(
        val  author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
    ) : ViewType{

    override fun getViewType() = AdapterConstants.NEWS
}