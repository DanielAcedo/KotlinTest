package com.example.daniel.kotlintest.adapter

import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.daniel.kotlintest.R
import com.example.daniel.kotlintest.inflate
import com.example.daniel.kotlintest.model.RedditNewsItem

/**
 * Created by Daniel on 16/01/2017.
 */

class LoadingDelegateAdapter : ViewTypeDelegateAdapter{
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            TurnsViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
    }

    class TurnsViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item_loading))
}