package com.example.daniel.kotlintest.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Daniel on 16/01/2017.
 */

interface ViewTypeDelegateAdapter{
    fun onCreateViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder
    fun onBindViewHolder(holder : RecyclerView.ViewHolder, item : ViewType)
}