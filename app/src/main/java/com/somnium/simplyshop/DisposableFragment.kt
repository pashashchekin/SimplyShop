package com.somnium.simplyshop

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class DisposableFragment: Fragment(){
    private var disposables = CompositeDisposable()

    fun apiCall(disposable: Disposable){
        disposables.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}