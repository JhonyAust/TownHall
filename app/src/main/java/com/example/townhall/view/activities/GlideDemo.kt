package com.example.townhall.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.townhall.R

class GlideDemo : AppCompatActivity() {
    private val image = "https://cdn.pixabay.com/photo/2018/05/03/21/49/android-3372580_960_720.png"
    private val mpl = "https://mutual-property.com/wp-content/uploads/2021/10/150416_Exterior_Night-min.jpg"
    private val mal = "https://mutual-property.com/wp-content/uploads/2021/10/7-1-min-scaled.jpg"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_demo)

        val imageOne = findViewById<ImageView>(R.id.image_one)
        val imageTwo = findViewById<ImageView>(R.id.image_two)
        val imageThree = findViewById<ImageView>(R.id.image_three)

        Glide.with(this)
            .load(image) // A new request builder for loading a Drawable using the given model.
            .into(imageOne) // Sets the ImageView the resource will be loaded into

        Glide.with(this)
            .load(mpl)
            .into(imageTwo)
        


        Glide.with(this)
            // To check the error you can change the link by adding some char
            .load("https://mutual-property.com/wp-content/uploads/2021/10/7-1-min-scaled.jpg")
            .override(1500, 1600)
            .centerCrop() // scale to fill the ImageView and crop any extra
            .into(imageThree)
        // END

    }
}