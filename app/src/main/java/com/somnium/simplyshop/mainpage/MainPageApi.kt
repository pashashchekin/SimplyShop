package com.somnium.simplyshop.mainpage

import android.content.Context
import com.somnium.simplyshop.App
import com.somnium.simplyshop.GlobalContext
import com.somnium.simplyshop.api.ObserveOnMainThread
import com.somnium.simplyshop.entities.Product
import com.somnium.simplyshop.entities.ResponseModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.Response

class MainPageApi(val  listener: MainPageApiListener ) {

    private lateinit var context: Context
    val disposables = CompositeDisposable()


    fun initAPI(context: Context) {
        this.context = context
    }

    fun loadProducts(){
        disposables.add(App.getSimplyShopApi().products
                .compose(ObserveOnMainThread())
                .subscribeBy(
                        onNext = ::onProductsLoadSuccesss,
                        onError = ::onProductsLoadError
                )
        )
    }

    private fun onProductsLoadSuccesss(response: Response<ResponseModel<List<Product>>>)
    {
        if (response.body() !=  null && response.isSuccessful){
            GlobalContext.products.clear()
            GlobalContext.products.addAll(response.body()!!.data)
            listener.onProductsUpdate()
        }
        else {
            listener.onProductsUpdate()
        }
    }

    private fun onProductsLoadError(e: Throwable){
        listener.onProductsUpdate()
    }

}
