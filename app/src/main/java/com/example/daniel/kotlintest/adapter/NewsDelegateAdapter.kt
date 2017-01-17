package com.example.daniel.kotlintest.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.daniel.kotlintest.R
import com.example.daniel.kotlintest.getFriendlyTime
import com.example.daniel.kotlintest.inflate
import com.example.daniel.kotlintest.loadImg
import com.example.daniel.kotlintest.model.RedditNewsItem
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by Daniel on 16/01/2017.
 */

class NewsDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder =
        TurnsViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item)) {

        fun bind(item: RedditNewsItem) = with(itemView){
            img_thumbnail.loadImg(item.thumbnail)
            txv_title.text = item.title
            txv_author.text = item.author
            txv_comments.text = "${item.numComments} comments"
            txv_time.text = item.created.getFriendlyTime()
        }
    }
}