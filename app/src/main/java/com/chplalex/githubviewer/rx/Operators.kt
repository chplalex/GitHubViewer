package com.chplalex.githubviewer.rx

import android.util.Log
import com.chplalex.githubviewer.TAG
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class Operators {

    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {
        fun createJust(): Observable<String> = Observable.just("1", "2", "3", "3")
    }

    class Consumer(private val producer: Producer) {

        private val stringObserver = object : Observer<String> {

            private var disposable: Disposable? = null

            override fun onSubscribe(d: Disposable?) {
                disposable = d
                Log.d(TAG, "stringObserver.onSubscribe()")
            }

            override fun onNext(t: String?) {
                Log.d(TAG, "stringObserver.onNext(), string = $t")
            }

            override fun onError(e: Throwable?) {
                Log.d(TAG, "stringObserver.onError(), error = ${e?.message}")
            }

            override fun onComplete() {
                Log.d(TAG, "stringObserver.onComplete()")
            }

        }

        fun exec() {
            execTake()
            execSkip()
            execMap()
        }

        private fun execMap() {

            producer.createJust()
                .map { "$it добавлена строка" }
                .subscribe(stringObserver)
        }

        private fun execSkip() {
            producer.createJust()
                .skip(2)
                .subscribe(stringObserver)
        }

        private fun execTake() {
            producer.createJust()
                .take(2)
                .subscribe(stringObserver)
        }

    }

}