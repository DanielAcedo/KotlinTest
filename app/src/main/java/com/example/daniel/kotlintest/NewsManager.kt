package com.example.daniel.kotlintest

import com.example.daniel.kotlintest.model.RedditNewsItem
import rx.Observable


/**
 * Created by Daniel on 17/01/2017.
 */

class NewsManager(){

    fun getNews() : Observable<List<RedditNewsItem>>{
        return Observable.create {
            subscriber ->

            val news = mutableListOf<RedditNewsItem>()
            for (i in 0..10) {
                news.add(RedditNewsItem(
                        "author$i",
                        "Title $i",
                        i, // number of comments
                        1457207701L - i * 200, // time
                        "http://lorempixel.com/200/200/technics/$i", // image url
                        "url"
                ))
                if(i>0) subscriber.onNext(news.subList(i-1,i))
                Thread.sleep(400)
            }

            subscriber.onCompleted()
        }
    }
}