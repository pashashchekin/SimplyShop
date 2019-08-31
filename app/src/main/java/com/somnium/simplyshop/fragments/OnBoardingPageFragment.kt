package com.somnium.simplyshop.fragments

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.somnium.simplyshop.R


class OnBoardingPageFragment : Fragment() {

    companion object Factory{
        fun create(@StringRes title: Int, @StringRes body: Int, @DrawableRes image: Int, index: Int): OnBoardingPageFragment{
            val fragment = OnBoardingPageFragment()
            fragment.bind(title, body, image, index)
            return fragment
        }
    }

    private var title: Int = 0
    private var body: Int = 0
    private var image: Int = 0
    private var index: Int = 0

    private var titleView: TextView? = null
    private var bodyView: TextView? = null
    private var imageView: ImageView? = null
    private var bgView: View? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.soft_on_boarding_page, container, false)
        initUI(view)
        showData()
        return view
    }



    private fun initUI(view: View?){
        bgView = view?.findViewById(R.id.soft_on_boarding_page_bg)
        titleView = view?.findViewById(R.id.soft_on_boarding_page_title)
        bodyView = view?.findViewById(R.id.soft_on_boarding_page_body)
        imageView = view?.findViewById(R.id.soft_on_boarding_page_image)
    }

    private fun showData() {
        if(title > 0 && titleView != null) {
            titleView?.setText(title)
            bodyView?.setText(body)
            imageView?.setImageResource(image)
        }
    }



    private fun bind(@StringRes title: Int, @StringRes body: Int, @DrawableRes image: Int, index: Int){
        this.title = title
        this.body = body
        this.image = image
        this.index = index
        showData()
    }
}
