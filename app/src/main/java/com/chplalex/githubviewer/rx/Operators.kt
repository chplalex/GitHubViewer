package com.chplalex.githubviewer.rx

import android.util.Log
import com.chplalex.githubviewer.TAG
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Function5
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Operators {

    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {
        fun createJust(): Observable<String> = Observable.just("1", "2", "3", "3")
        fun createIterable(): Observable<String> = Observable.fromIterable(listOf("4", "5", "6"))
        fun createJust2(): Observable<String> = Observable.just("7", "8", "9")
    }

    class Consumer(private val producer: Producer) {

        private val stringObserver = object : Observer<String> {

            private var disposable: Disposable? = null

            override fun onSubscribe(d: Disposable?) {
                disposable = d
                Log.d(TAG, "stringObserver.onSubscribe()")
            }

            override fun onNext(t: String?) {
                Log.d(TAG, "stringObserver.onNext(), получено -> $t")
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
            execDistinct()
            execFilter()
            execMerge()
            execFlatMap()
            execZip()
        }

        private fun execZip() {
            Log.d(TAG, "execZip()")

            val observable1 = Observable.just("1").delay(5, TimeUnit.SECONDS)
            val observable2 = Observable.just("2").delay(4, TimeUnit.SECONDS)
            val observable3 = Observable.just("3").delay(3, TimeUnit.SECONDS)
            val observable4 = Observable.just("4").delay(2, TimeUnit.SECONDS)
            val observable5 = Observable.just("5").delay(1, TimeUnit.SECONDS)

            Observable.zip(
                observable1,
                observable2,
                observable3,
                observable4,
                observable5,
                Function5<String, String, String, String, String, String> { s1, s2, s3, s4, s5 ->
                    return@Function5 "$s1 $s2 $s3 $s4 $s5"
                })
                .subscribeOn(Schedulers.computation())
                .subscribe(stringObserver)
        }

        private fun execFlatMap() {
            Log.d(TAG, "execFlatMap()")
            producer.createJust()
                .flatMap {
                    val delay = Random.nextInt(1000).toLong()
                    Log.d(TAG, "flatMap(), запуск -> it = $it")
                    return@flatMap Observable.just("$it новая строка")
                        .delay(delay, TimeUnit.MILLISECONDS)
                }
                .subscribe(stringObserver)
        }

        private fun execMerge() {
            Log.d(TAG, "execMerge()")
            producer.createJust()
                .mergeWith(producer.createJust2())
                .mergeWith(producer.createIterable())
                .subscribe(stringObserver)
        }

        private fun execFilter() {
            Log.d(TAG, "execFilter()")
            producer.createJust()
                .filter() { (it.toInt() % 2) != 0 } // фильтруем нечетные значения
                .subscribe(stringObserver)
        }

        private fun execDistinct() {
            Log.d(TAG, "execDistinct()")
            producer.createJust()
                .distinct()
                .subscribe(stringObserver)
        }

        private fun execMap() {
            Log.d(TAG, "execMap()")
            producer.createJust()
                .map() { "$it добавлена строка" }
                .subscribe(stringObserver)
        }

        private fun execSkip() {
            Log.d(TAG, "execSkip()")
            producer.createJust()
                .skip(2)
                .subscribe(stringObserver)
        }

        private fun execTake() {
            Log.d(TAG, "execTake()")
            producer.createJust()
                .take(2)
                .subscribe(stringObserver)
        }

    }

}