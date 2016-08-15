package com.github.baby.owspace.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.baby.owspace.R;
import com.github.baby.owspace.model.entity.DetailEntity;
import com.github.baby.owspace.presenter.DetailContract;
import com.github.baby.owspace.presenter.DetailPresenter;
import com.github.baby.owspace.util.AppUtil;
import com.github.baby.owspace.util.tool.AnalysisHTML;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/9
 * owspace
 */
public class DetailActivity extends AppCompatActivity implements DetailContract.View, ObservableScrollViewCallbacks {
    @Bind(R.id.favorite)
    ImageView favorite;
    @Bind(R.id.write)
    ImageView write;
    @Bind(R.id.share)
    ImageView share;
    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.scrollView)
    ObservableScrollView scrollView;
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.news_parse_web)
    LinearLayout newsParseWeb;
    @Bind(R.id.news_top_type)
    TextView newsTopType;
    @Bind(R.id.news_top_date)
    TextView newsTopDate;
    @Bind(R.id.news_top_title)
    TextView newsTopTitle;
    @Bind(R.id.news_top_author)
    TextView newsTopAuthor;
    @Bind(R.id.news_top_lead)
    TextView newsTopLead;
    @Bind(R.id.news_top)
    LinearLayout newsTop;
    @Bind(R.id.news_top_img_under_line)
    View newsTopImgUnderLine;
    @Bind(R.id.news_top_lead_line)
    View newsTopLeadLine;

    private DetailPresenter presenter;
    private int mParallaxImageHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_detail);
        ButterKnife.bind(this);
        initView();
        presenter = new DetailPresenter(this);
        String itemId = getIntent().getStringExtra("itemId");
        presenter.getDetail(itemId);
    }

    private void initView() {
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolBar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.primary)));
        scrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);
    }

    private void initWebViewSetting() {
        WebSettings localWebSettings = this.webView.getSettings();
        localWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        localWebSettings.setJavaScriptEnabled(true);
        localWebSettings.setSupportZoom(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        localWebSettings.setUseWideViewPort(true);
        localWebSettings.setLoadWithOverviewMode(true);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void updateListUI(DetailEntity detailEntity) {
        Glide.with(this).load(detailEntity.getThumbnail()).centerCrop().into(image);
        newsTopDate.setText(detailEntity.getUpdate_time());
        newsTopTitle.setText(detailEntity.getTitle());
        newsTopAuthor.setText(detailEntity.getAuthor());
        newsTopLead.setText(detailEntity.getLead());
        if (detailEntity.getParseXML() == 1) {
            newsTopLeadLine.setVisibility(View.VISIBLE);
            newsTopImgUnderLine.setVisibility(View.VISIBLE);
            int i = detailEntity.getLead().trim().length();
            AnalysisHTML analysisHTML = new AnalysisHTML();
            analysisHTML.loadHtml(this, detailEntity.getContent(), analysisHTML.HTML_STRING, newsParseWeb, i);
            int mode = Integer.valueOf(detailEntity.getModel());
            switch (mode){
                case 1:
                    newsTopType.setText("文 字");
                    break;
                case 2:
                    newsTopType.setText("视 频");
                    break;
                case 3:
                    newsTopType.setText("音 频");
                    break;
            }
        } else {
            initWebViewSetting();
            newsParseWeb.setVisibility(View.GONE);
            image.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            newsTop.setVisibility(View.GONE);
            webView.loadUrl(addParams2WezeitUrl(detailEntity.getHtml5(), false));
        }

    }

    @Override
    public void showOnFailure() {

    }

    @Override
    public void onScrollChanged(int i, boolean b, boolean b1) {
        int baseColor = getResources().getColor(R.color.primary);
        float alpha = Math.min(1, (float) i / mParallaxImageHeight);
        toolBar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
//        ViewHelper.setTranslationY(image, i / 2);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    public String addParams2WezeitUrl(String url, boolean paramBoolean) {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append(url);
        localStringBuffer.append("?client=android");
        localStringBuffer.append("&device_id=" + AppUtil.getDeviceId(this));
        localStringBuffer.append("&version=" + "1.3.0");
        if (paramBoolean)
            localStringBuffer.append("&show_video=0");
        else {
            localStringBuffer.append("&show_video=1");
        }
        return localStringBuffer.toString();
    }

}
