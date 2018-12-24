package com.example.homework.detail;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.homework.R;

public class SecondActivity extends Activity implements DetailContract.DetailUIView {
    private WebView webView;
//    private ImageView imageView;
//    private TextView textViewTitle;
//    private TextView textViewImageSource;
    private Menu menu;
    private DetailPresenterImpl detailPresenter = new DetailPresenterImpl(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.second_activity);
        initControls();
        init();
    }

    private void initControls() {
//        onCreateOptionsMenu(menu);//TODO:这个也是有问题

        webView = this.findViewById(R.id.webView);
//        imageView = (ImageView) findViewById(R.id.detail_image);
//        textViewTitle = (TextView) findViewById(R.id.detail_title);
//        textViewImageSource = (TextView) findViewById(R.id.detail_image_resource);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    private void init() {
        detailPresenter.initData(this);
    }

    @Override
    public void initView(String body) {
        //TODO:详情加图加文字
//        textViewTitle.setText(bean.getText());
//        textViewImageSource.setText(bean.getImageSource());
//        Picasso.with(SecondActivity.this).load(bean.getImageUrl())
//                .fit()
//                .into(imageView);

        String fitBody = body.replace("<img","<img style = 'max-width:100%;height:auto;'");
        webView.loadData(fitBody, "text/html", "utf-8");
    }

    @Override
    public void onError() {
        Toast toast = Toast.makeText(SecondActivity.this, "出错辽", Toast.LENGTH_SHORT);
        toast.show();
    }
}
