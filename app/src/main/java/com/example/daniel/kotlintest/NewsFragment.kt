package com.example.daniel.kotlintest

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.daniel.kotlintest.adapter.NewsAdapter
import com.example.daniel.kotlintest.adapter.ViewType
import com.example.daniel.kotlintest.model.RedditNewsItem

import kotlinx.android.synthetic.main.fragment_newslist.*
import rx.android.schedulers.AndroidSchedulers
import rx.observers.Subscribers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by Daniel on 16/01/2017.
 */
class NewsFragment : Fragment() {

    var subscriptions = CompositeSubscription()
    private val newsManager by lazy { NewsManager() }
    private val newsList : RecyclerView by lazy {
        news_list
    }
    private val btnLoad : Button by lazy {
        btn_LoadMore
    }

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newsList.setHasFixedSize(true)
        newsList.layoutManager = LinearLayoutManager(context)

        init_adapter()

        if (savedInstanceState == null) {
            requestNews()
        }

        btnLoad.setOnClickListener { (newsList.adapter as NewsAdapter).addNews((newsList.adapter as NewsAdapter).getNews()) }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.fragment_newslist)
        return view
    }

    fun requestNews() {
        val subscription = newsManager.getNews().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe (
                {retrievedNews ->  (newsList.adapter as NewsAdapter).addNews(retrievedNews)},
                {e -> Snackbar.make(newsList, e.message.toString(), Snackbar.LENGTH_SHORT).show()},
                {(newsList.adapter as NewsAdapter).enableLoadingBar(false)}
        )

        subscriptions.add(subscription)
    }

    fun init_adapter(){
        if (newsList.adapter == null){
            newsList.adapter = NewsAdapter()
        }
    }
}