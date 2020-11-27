package com.chplalex.githubviewer.rx

import android.util.Log
import com.chplalex.githubviewer.TAG
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Creation {

    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {
        fun fromJust(): Observable<String> = Observable.just("1","2", "3")
        fun fromIterable(): Observable<String> = Observable.fromIterable(listOf("1","2", "3"))
        fun fromInterval(): Observable<Long> = Observable.interval(2, TimeUnit.SECONDS)
        fun fromTimer(): Observable<Long> = Observable.timer(3, TimeUnit.SECONDS)
        fun fromRange(): Observable<Int> = Observable.range(1, 10)
        fun fromCallable(): Observable<Boolean> = Observable.fromCallable { randomResultOperation() }

        private fun randomResultOperation(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return arrayOf(true, false)[Random.nextInt(2)]
        }
    }

    class Consumer(val producer: Producer) {

        val stringObserver = object : Observer<String> {
            var disposable: Disposable? = null

            override fun onSubscribe(d: Disposable?) {
                disposable = d
                Log.d(TAG, "stringObserver -> onSubscribe()")
            }

            override fun onNext(s: String?) {
                Log.d(TAG, "stringObserver -> onNext(), s = $s")
            }

            override fun onError(e: Throwable?) {
                Log.d(TAG, "stringObserver -> onError(), e = ${e?.message}")
            }

            override fun onComplete() {
                Log.d(TAG, "ostringObserver -> nComplete()")
            }
        }

        fun exec() {
//            execJust()
//            execLambda()
//            execIterable()
//            execInterval()
//            execTimer()
//            execRange()
            for ( i in 1..10 ) execCallable()
        }

        private fun execJust() {
            Log.d(TAG, "execJust()")
            producer.fromJust().subscribe(stringObserver)
        }

        private fun execLambda() {
            Log.d(TAG, "execLambda()")
            producer.fromJust().subscribe(
                { s -> Log.d(TAG, "execLambda(), onNext(), s = $s") },
                { e -> Log.d(TAG, "execLambda(), onError(), e = ${e?.message}")},
                { Log.d(TAG, "execLambda(), onComplete()")}
            )
        }

        private fun execIterable() {
            Log.d(TAG, "execIterable()")
            producer.fromIterable().subscribe(stringObserver)
        }

        private lateinit var disposableInterval: Disposable

        private fun execInterval() {
            Log.d(TAG, "execInterval()")
            disposableInterval = producer.fromInterval().subscribe(
                { l ->
                    Log.d(TAG, "execInterval(), onNext(), l = $l")
                    if (l == 9L) {
                        disposableInterval.dispose()
                        Log.d(TAG, "execInterval(), dispose()")
                    }
                },
                { e -> Log.d(TAG, "execInterval(), onError(), e = ${e?.message}")},
                { Log.d(TAG, "execInterval(), onComplete()")}
            )
        }

        private fun execTimer() {
            Log.d(TAG, "execTimer()")
            producer.fromTimer().subscribe(
                { l -> Log.d(TAG, "execTimer(), onNext(), l = $l") },
                { e -> Log.d(TAG, "execTimer(), onError(), e = ${e?.message}")},
                { Log.d(TAG, "execTimer(), onComplete()")}
            )
        }

        private fun execRange() {
            Log.d(TAG, "execRange()")
            producer.fromRange().subscribe(
                { i -> Log.d(TAG, "execRange(), onNext(), i = $i") },
                { e -> Log.d(TAG, "execRange(), onError(), e = ${e?.message}")},
                { Log.d(TAG, "execRange(), onComplete()")}
            )
        }
        
        private fun execCallable() {
            producer.fromCallable().subscribe(
                { b -> Log.d(TAG, "execCallable(), onNext(), b = $b") },
                { e -> Log.d(TAG, "execCallable(), onError(), e = ${e?.message}")},
                { Log.d(TAG, "execCallable(), onComplete()")}
            )
        }

    }
}