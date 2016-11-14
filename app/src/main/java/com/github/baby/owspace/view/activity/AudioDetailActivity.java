package com.github.baby.owspace.view.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.baby.owspace.R;
import com.github.baby.owspace.app.OwspaceApplication;
import com.github.baby.owspace.di.components.DaggerDetailComponent;
import com.github.baby.owspace.di.modules.DetailModule;
import com.github.baby.owspace.model.entity.DetailEntity;
import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.player.IPlayback;
import com.github.baby.owspace.player.PlaybackService;
import com.github.baby.owspace.presenter.DetailContract;
import com.github.baby.owspace.presenter.DetailPresenter;
import com.github.baby.owspace.util.AppUtil;
import com.github.baby.owspace.util.tool.AnalysisHTML;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/11/8
 * owspace
 */

public class AudioDetailActivity extends BaseActivity implements DetailContract.View, ObservableScrollViewCallbacks {
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.news_top_img_under_line)
    View newsTopImgUnderLine;
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
    @Bind(R.id.news_top_lead_line)
    View newsTopLeadLine;
    @Bind(R.id.news_top)
    LinearLayout newsTop;
    @Bind(R.id.news_parse_web)
    LinearLayout newsParseWeb;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.scrollView)
    ObservableScrollView scrollView;
    @Bind(R.id.favorite)
    ImageView favorite;
    @Bind(R.id.write)
    ImageView write;
    @Bind(R.id.share)
    ImageView share;
    @Bind(R.id.toolBar)
    Toolbar toolBar;

    @Inject
    DetailPresenter presenter;
    @Bind(R.id.button_play_last)
    AppCompatImageView buttonPlayLast;
    @Bind(R.id.button_play_toggle)
    AppCompatImageView buttonPlayToggle;
    @Bind(R.id.button_play_next)
    AppCompatImageView buttonPlayNext;
    private int mParallaxImageHeight;

    private PlaybackService mPlaybackService;

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the service object we can use to
            // interact with the service.  Because we have bound to a explicit
            // service that we know is running in our own process, we can
            // cast its IBinder to a concrete class and directly access it.
            mPlaybackService = ((PlaybackService.LocalBinder) service).getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            // Because it is running in our same process, we should never
            // see this happen.
            mPlaybackService = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        ButterKnife.bind(this);
        initView();
        initPresenter();
        bindPlaybackService();
    }
    public void bindPlaybackService() {
        this.bindService(new Intent(this, PlaybackService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        Item item = bundle.getParcelable("item");
        if (item != null) {
            Glide.with(this).load(item.getThumbnail()).centerCrop().into(image);
            newsTopLeadLine.setVisibility(View.VISIBLE);
            newsTopImgUnderLine.setVisibility(View.VISIBLE);
            newsTopType.setText("音 频");
            newsTopDate.setText(item.getUpdate_time());
            newsTopTitle.setText(item.getTitle());
            newsTopAuthor.setText(item.getAuthor());
            newsTopLead.setText(item.getLead());
            newsTopLead.setLineSpacing(1.5f, 1.8f);
            presenter.getDetail(item.getId());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void initPresenter() {
        DaggerDetailComponent.builder()
                .netComponent(OwspaceApplication.get(this).getNetComponent())
                .detailModule(new DetailModule(this))
                .build()
                .inject(this);
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
    String song;
    @Override
    public void updateListUI(DetailEntity detailEntity) {
        song = detailEntity.getFm();
        if (detailEntity.getParseXML() == 1) {
            int i = detailEntity.getLead().trim().length();
            AnalysisHTML analysisHTML = new AnalysisHTML();
            analysisHTML.loadHtml(this, detailEntity.getContent(), analysisHTML.HTML_STRING, newsParseWeb, i);
            newsTopType.setText("音 频");
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

    @OnClick(R.id.button_play_toggle)
    public void onClick() {
        if (mPlaybackService == null) return;
        buttonPlayToggle.setImageResource(R.drawable.ic_pause);
        if (mPlaybackService.isPlaying()) {
            mPlaybackService.pause();
            buttonPlayToggle.setImageResource(R.drawable.ic_pause);
        }else {
            mPlaybackService.play(song);
        }
    }
}
