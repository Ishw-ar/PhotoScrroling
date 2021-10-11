package com.varsha.testproject.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.varsha.testproject.R
import com.varsha.testproject.adapters.ImageAdapter
import com.varsha.testproject.databinding.ActivityMainBinding
import com.varsha.testproject.models.Photo
import com.varsha.testproject.viewmodels.ImageViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ItemClickListener{
    private lateinit var activityMainBinding: ActivityMainBinding
    private val imageViewModel by viewModel<ImageViewModel>()
    lateinit var imageAdapter: ImageAdapter
    lateinit var shimmerFrameLayout: ShimmerFrameLayout
    var isGrid:Boolean=false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        shimmerFrameLayout= findViewById(R.id.shimmer_view)
        initMembers()
        setUpListViews()
        fetchImagesLiveData()
        btn_image.setOnClickListener {
            if (!isGrid) {
                setUpGridViews()
                isGrid = true
                btn_image.text = "Change to List View"
                fetchImagesLiveData()
            } else {
                setUpListViews()
                isGrid = false
                btn_image.text = "Change to Grid View"
                fetchImagesLiveData()
            }
        }
    }
    private fun initMembers() {
        imageAdapter = ImageAdapter(this)
    }
    private fun setUpListViews() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = imageAdapter
    }
    private fun setUpGridViews() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = imageAdapter
    }
    private fun fetchImagesLiveData() {
        imageViewModel.fetchImagesLiveData().observe(this, Observer {
            Handler(Looper.getMainLooper()).postDelayed({
                shimmerFrameLayout.stopShimmerAnimation()
                shimmerFrameLayout.visibility = View.GONE
                lifecycleScope.launch {
                    imageAdapter.submitData(it)
                }
            },5000)
        })
    }
    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmerAnimation()
    }
    override fun onPause() {
        shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }

    override fun onItemClicked(photo: Photo) {
        val intent= Intent(this,ShowImageActivity::class.java)
        intent.putExtra("secret_id",photo.secret)
        intent.putExtra("server_id", photo.server)
        intent.putExtra("key_id", photo.id)
        startActivity(intent)
    }}