package com.somnium.simplyshop.fragments

import android.Manifest
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.somnium.simplyshop.App
import com.somnium.simplyshop.DisposableFragment
import com.somnium.simplyshop.R
import com.somnium.simplyshop.activities.LoginPageActivity
import com.somnium.simplyshop.adapters.ProductsAdapter
import com.somnium.simplyshop.api.ObserveOnMainThread
import com.somnium.simplyshop.entities.Jwtoken
import com.somnium.simplyshop.entities.Product
import com.somnium.simplyshop.entities.ResponseModel
import com.somnium.simplyshop.enums.ServerStatus
import com.somnium.simplyshop.listeners.OnAdapterItemClickListener
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.Response

class CategoriesFragment : DisposableFragment(), OnAdapterItemClickListener<Product> {


    private lateinit var productsView: RecyclerView
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var token : Jwtoken


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_categories_products, container, false)
        initUI(view)
        token = Jwtoken.getToken(context)
        return view

    }

    private fun initUI(view: View) {
        productsAdapter = ProductsAdapter()
        productsAdapter.listener = this
        productsView = view.findViewById(R.id.fragment_products_in_category_recyclerview)
        productsView.layoutManager = GridLayoutManager(context, 2)
        productsView.adapter = productsAdapter
        productsAdapter.progressView = view.findViewById(R.id.fragment_products_in_category_progress)
    }

    override fun onResume() {
        super.onResume()
        loadProducts()
    }

    private fun loadProducts() {
        apiCall(App.getSimplyShopApi().getProducts(token.auth_token)
                .compose(ObserveOnMainThread())
                .subscribeBy(
                        onNext = this::onProductsLoaded,
                        onError = this::onProductLoadError
                )
        )
    }

    private fun onProductsLoaded(response: Response<ResponseModel<List<Product>>>) {
        productsAdapter.progressView?.visibility = View.GONE
        if (response.isSuccessful && response.body()?.status == ServerStatus.SUCCESS){
            productsAdapter.items = response.body()?.data!!
            productsAdapter.notifyItemsChanged()
        }

    }

    private fun onProductLoadError(throwable: Throwable) {
        productsAdapter.notifyItemsChanged()
    }

    override fun onAdapterItemClick(item: Product) {
        val intent  = Intent(activity, LoginPageActivity::class.java)
        startActivity(intent)
    }

    companion object {
        fun getInstance(): CategoriesFragment {
            val fragmentProductInCategory = CategoriesFragment()
            fragmentProductInCategory.arguments = Bundle()
            return fragmentProductInCategory
        }
    }
}