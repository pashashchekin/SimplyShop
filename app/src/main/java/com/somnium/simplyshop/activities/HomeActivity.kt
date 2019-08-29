package com.somnium.simplyshop.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.widget.Button
import com.somnium.simplyshop.App
import com.somnium.simplyshop.BaseActivity
import com.somnium.simplyshop.R
import com.somnium.simplyshop.adapters.TabsAdapter
import com.somnium.simplyshop.api.ObserveOnMainThread
import com.somnium.simplyshop.entities.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.Response

const val KEY_ACCOUNT = "account"

class HomeActivity : BaseActivity(0) {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var search : Button
    private val disposables = CompositeDisposable()
    private  lateinit var userCreate: UserCreate
    private lateinit var token : Jwtoken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupBottomNavigation()
        initUI()
        token = Jwtoken.getToken(this)
        loadProducts()
    }

    private fun loadProducts() {

    }


    private fun initUI() {
        val tabs = arrayOf("qwe","eqwe")
        tabLayout = findViewById(R.id.categories_tabs)
        viewPager = findViewById(R.id.products_viewpager)
        viewPager.adapter = TabsAdapter(supportFragmentManager, tabs, TabsAdapter.PageType.CATEGORIES)
        tabLayout.setupWithViewPager(viewPager)
        search = findViewById(R.id.search_btn)
        search.setOnClickListener {
            getAllProducts()
        }
    }

    private fun getAllProducts() {
        disposables.add(App.getSimplyShopApi().getProducts(token.auth_token)
                .compose(ObserveOnMainThread())
                .subscribeBy(
                        onNext = ::onLoginSuccess,
                        onError = ::onLoginError
                )
        )

    }

    private fun onLoginSuccess(response: Response<ResponseModel<List<Product>>>) {

    }

    private fun onLoginError(throwable: Throwable) {


    }

}
