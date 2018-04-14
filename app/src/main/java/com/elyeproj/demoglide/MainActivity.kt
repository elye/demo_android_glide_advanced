package com.elyeproj.demoglide

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MyImageRequestListener.Callback {
    override fun onFailure(message: String?) {
        Toast.makeText(this, "Fail to load: $message", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(dataSource: String) {
        Toast.makeText(this, "Loaded from: $dataSource", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadImage("https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg?auto=compress&cs=tinysrgb&h=50",
                "https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg?auto=compress&cs=tinysrgb&h=1000"
        )
    }

    private fun loadImage(fastLoadUrl: String, fullImageUrl: String) {

        val requestOption = RequestOptions()
                .placeholder(R.drawable.placeholder).centerCrop()

        Glide.with(this).load(MyImageModel(fullImageUrl))
                .transition(DrawableTransitionOptions.withCrossFade())
                .thumbnail(Glide.with(this)
                        .load(MyImageModel(fastLoadUrl))
                        .apply(requestOption))
                .apply(requestOption)
                .listener(MyImageRequestListener(this))
                .into(my_image_view)
    }
}
