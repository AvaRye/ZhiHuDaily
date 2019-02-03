package com.example.homework.news

import android.annotation.SuppressLint
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toolbar


import com.example.homework.R
import com.example.homework.data.ItemBean
import com.example.homework.data.Translation

class MainActivity : AppCompatActivity(), NewsContract.NewsUIView {
    private var recyclerView: RecyclerView? = null
    private var adapterOne: AdapterOne? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var isLoading = false
    private val newsPresenter = NewsPresenterImpl(this)

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        initControls()
        refresh()
        loadUp()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun initControls() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setActionBar(toolbar)
        swipeRefreshLayout = findViewById(R.id.refresh)
        adapterOne = AdapterOne(this)
        recyclerView = findViewById(R.id.recycler)
        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = adapterOne
        //        View footerView = LayoutInflater.from(this).inflate(R.layout.footer,null,false);//TODO:footer
    }

    private fun init() {
        newsPresenter.initData()
    }

    fun refresh() {
        swipeRefreshLayout!!.setOnRefreshListener {
            newsPresenter.refreshData()
            swipeRefreshLayout!!.isRefreshing = false
        }
    }

    private fun loadUp() {
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var totalItemCount: Int = 0
            private var firstVisibleItem: Int = 0
            private var visibleItemCount: Int = 0

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
//                                onScrolled(recyclerView)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                totalItemCount = layoutManager!!.itemCount
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                visibleItemCount = recyclerView.childCount
                Log.v("onScrolled", "滑动判定")
                if (totalItemCount - visibleItemCount <= firstVisibleItem && !isLoading) {
                    Log.v("onScrolled", "加载判定")
                    isLoading = true
                    newsPresenter.onLoadMoreData()
                }
            }
        })
    }

    override fun onError() {
        val toast = Toast.makeText(this@MainActivity, "出错辽", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun initItems(translation: Translation) {
        adapterOne!!.initItems(translation)
    }

    override fun refreshItems(translation: Translation) {
        adapterOne!!.refreshItems(translation)
    }


    override fun loadUpItems(translation: Translation) {
        adapterOne!!.loadMoreItems(translation)
        isLoading = false
    }
}
