package com.example.homework.detail

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.webkit.WebView
import android.widget.Toast

import com.example.homework.R

class SecondActivity : Activity(), DetailContract.DetailUIView {
    private var webView: WebView? = null
    //    private ImageView imageView;
    //    private TextView textViewTitle;
    //    private TextView textViewImageSource;
    private var menu: Menu? = null
    private val detailPresenter = DetailPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.second_activity)
        initControls()
        init()
    }

    private fun initControls() {
        //        onCreateOptionsMenu(menu);//TODO:这个也是有问题

        webView = this.findViewById(R.id.webView)
        //        imageView = (ImageView) findViewById(R.id.detail_image);
        //        textViewTitle = (TextView) findViewById(R.id.detail_title);
        //        textViewImageSource = (TextView) findViewById(R.id.detail_image_resource);

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    private fun init() {
        detailPresenter.initData(this)
    }

    override fun initView(body: String) {
        //TODO:详情加图加文字
        //        textViewTitle.setText(bean.getText());
        //        textViewImageSource.setText(bean.getImageSource());
        //        Picasso.with(SecondActivity.this).load(bean.getImageUrl())
        //                .fit()
        //                .into(imageView);

        val fitBody = body.replace("<img", "<img style = 'max-width:100%;height:auto;'")
        webView!!.loadData(fitBody, "text/html", "utf-8")
    }

    override fun onError() {
        val toast = Toast.makeText(this@SecondActivity, "出错辽", Toast.LENGTH_SHORT)
        toast.show()
    }
}
