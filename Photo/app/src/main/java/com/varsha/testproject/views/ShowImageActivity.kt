package com.varsha.testproject.views

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.varsha.testproject.R
import com.varsha.testproject.databinding.ActivityShowImageBinding
import kotlinx.android.synthetic.main.activity_show_image.*
import java.lang.Float.max
import kotlin.math.min

class ShowImageActivity : AppCompatActivity() {
    private lateinit var activityShowImageBinding: ActivityShowImageBinding
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityShowImageBinding= DataBindingUtil.setContentView(this, R.layout.activity_show_image)
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

        /**
         * Setting the data which we are getting from ItemList.
         */

        if (intent.extras != null) {
            val server_id = intent.getStringExtra("server_id")
            val secret_id = intent.getStringExtra("secret_id")
            val key_id = intent.getStringExtra("key_id")

            Glide.with(this)
                .load("https://live.staticflickr.com/" + server_id + "/" + key_id + "_" + secret_id + ".jpg")
                .into(set_image)
        }

    }


    override fun onTouchEvent(motionEvent: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(motionEvent)
        return true
    }



    /**
     * Implemented image ZoomIn and ZoomOut
     */
    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor = max(0.1f, min(scaleFactor, 10.0f))
            set_image.scaleX = scaleFactor
            set_image.scaleY = scaleFactor
            return true
        }
    }

}