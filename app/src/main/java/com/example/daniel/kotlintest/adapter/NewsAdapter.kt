package com.example.daniel.kotlintest.adapter

import android.content.Context
import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.daniel.kotlintest.model.RedditNewsItem
import java.util.*

/**
 * Created by Daniel on 16/01/2017.
 */

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var items : ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()


    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING,
                LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS,
                NewsDelegateAdapter())

        items = ArrayList()
        items.add(loadingItem)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        delegateAdapters.get(items.get(position).getViewType()).onBindViewHolder(holder as RecyclerView.ViewHolder,
                items.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return items.get(position).getViewType()
    }

    fun addNews(list : List<RedditNewsItem>){
        val initPosition = getLastPosition()
        items.addAll(initPosition, list)
        notifyItemRangeChanged(initPosition, initPosition+list.size)
    }

    fun getNews() : List<RedditNewsItem>{
        return items.filter {
            it.getViewType() == AdapterConstants.NEWS
        }.map { it as RedditNewsItem }
    }

    fun enableLoadingBar(active: Boolean) {
        if (active) {
            items.add(loadingItem)
        } else {
            items.removeAt(getLastPosition())
        }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}