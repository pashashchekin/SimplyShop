package com.somnium.simplyshop.mainpage

import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class DisposableActivity : AppCompatActivity() {
    protected val disposables: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}