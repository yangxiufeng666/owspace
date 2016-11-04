package com.github.baby.owspace.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.baby.owspace.R;
import com.github.baby.owspace.view.widget.SelectTextView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/10
 * owspace
 */
public class PaintViewUtil {
    private final String LINE_H3 = "LINE_H3";
    private final String LINE_H4 = "LINE_H4";
    private final String LINE_HR = "LINE_HR";
    private SelectTextView blockTv;
    private int imgH;
    private int imgW;
    private LinearLayout.LayoutParams lParam;
    private View lineView;
    private SelectTextView ntv;
    private SelectTextView poetryTv;
    private Typeface typeFace;

    public PaintViewUtil(Context context) {
        typeFace =Typeface.createFromAsset(context.getAssets(),"fonts/PMingLiU.ttf");
    }

    private void addBlock(Context paramContext, ViewGroup paramViewGroup, SpannableStringBuilder paramSpannableStringBuilder, int paramInt1, int paramInt2)
    {
        paintBlockLine(paramContext, paramViewGroup, true);
        paintBlockTextView(paramContext, paramViewGroup, paramSpannableStringBuilder, paramInt1, paramInt2);
        paintBlockLine(paramContext, paramViewGroup, false);
    }

    private void addH3TextView(Context paramContext, ViewGroup paramViewGroup, SpannableStringBuilder paramSpannableStringBuilder, int paramInt1, int paramInt2, int paramInt3)
    {
        addLines(paramContext, paramViewGroup, "LINE_H3");
        this.ntv = new SelectTextView(paramContext);
        this.ntv.setSingleLine(false);
        ntv.setTextIsSelectable(true);
        setFont(ntv,paramContext);
        paramViewGroup.addView(this.ntv);
        putTextSpanViewSettings(paramContext, this.ntv, paramSpannableStringBuilder, paramInt1, paramInt2, paramInt3);
    }

    private void addH4TextView(Context paramContext, ViewGroup paramViewGroup, SpannableStringBuilder paramSpannableStringBuilder, int paramInt1, int paramInt2, int paramInt3)
    {
        addLines(paramContext, paramViewGroup, "LINE_H4");
        this.ntv = new SelectTextView(paramContext);
        this.ntv.setSingleLine(false);
        ntv.setTextIsSelectable(true);
        ntv.setTextColor(paramContext.getResources().getColor(R.color.black));
        setFont(ntv,paramContext);
        paramViewGroup.addView(this.ntv);
        putTextSpanViewSettings(paramContext, this.ntv, paramSpannableStringBuilder, paramInt1, paramInt2, paramInt3);
    }

    private void addH5TextView(Context paramContext, ViewGroup paramViewGroup, SpannableStringBuilder paramSpannableStringBuilder, int paramInt1, int paramInt2, int paramInt3)
    {
        this.ntv = new SelectTextView(paramContext);
        this.ntv.setSingleLine(false);
        ntv.setTextIsSelectable(true);
        ntv.setTextColor(paramContext.getResources().getColor(R.color.green));
        ntv.setTextSize(10);
        setFont(ntv,paramContext);
        paramViewGroup.addView(this.ntv);
        putTextSpanViewSettings(paramContext, this.ntv, paramSpannableStringBuilder, paramInt1, paramInt2, paramInt3);
    }

    private void addImageView(final Activity paramActivity, SpannableStringBuilder paramSpannableStringBuilder, ViewGroup paramViewGroup, String imgWidth,String imgHeight, String imgUrl)
    {
        ImageView localImageView = (ImageView)View.inflate(paramActivity, R.layout.item_image_view, null);
        this.lParam = getLinearParams(paramActivity);
        if ((imgWidth != null) && (imgHeight != null) && (!imgWidth.isEmpty()) && (!imgHeight.isEmpty()))
        {
            String str3 = imgWidth.replace("px", "");
            String str4 = imgHeight.replace("px", "");
            this.imgW = AppUtil.dp2px(paramActivity, (float)Double.parseDouble(str3));
            this.imgH = AppUtil.dp2px(paramActivity, (float)Double.parseDouble(str4));
            this.lParam.height = (AppUtil.getWindowWidth(paramActivity) * this.imgH / this.imgW);
        }
        localImageView.setLayoutParams(this.lParam);
        paramViewGroup.addView(localImageView);
        Glide.with(paramActivity).load(imgUrl).into(localImageView);
    }

    private void addLines(Context paramContext, ViewGroup paramViewGroup, String paramString)
    {
        this.lineView = new View(paramContext);
        this.lParam = getLinearParams(paramContext);
        paramViewGroup.addView(this.lineView);
    }

    private void addPoetry(Context paramContext, ViewGroup paramViewGroup, SpannableStringBuilder paramSpannableStringBuilder, int paramInt1, int paramInt2)
    {
        this.poetryTv = new SelectTextView(paramContext);
        this.poetryTv.setSingleLine(false);
        this.poetryTv.setText(paramSpannableStringBuilder, TextView.BufferType.SPANNABLE);
        setFont(poetryTv,paramContext);
        paramViewGroup.addView(this.poetryTv);
    }

    private void addTextSpanView(Context paramContext, ViewGroup paramViewGroup, SpannableStringBuilder paramSpannableStringBuilder, int paramInt1, int paramInt2, int paramInt3)
    {
        this.ntv = new SelectTextView(paramContext);
        this.ntv.setSingleLine(false);
        ntv.setLineSpacing(1.5f,1.8f);
        ntv.setTextSize(16);
        ntv.setTextIsSelectable(true);
        setFont(ntv,paramContext);
        paramViewGroup.addView(this.ntv);
        this.lParam = getLinearParams(paramContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,14,0,0);
        ntv.setLayoutParams(layoutParams);
        putTextSpanViewSettings(paramContext, this.ntv, paramSpannableStringBuilder, paramInt1, paramInt2, paramInt3);
    }
    private void setFont(SelectTextView selectTextView,Context context){

        selectTextView.setTypeface(typeFace);
    }
    private int getColorById(Context paramContext, int paramInt)
    {
        return paramContext.getResources().getColor(paramInt);
    }

    private LinearLayout.LayoutParams getLinearParams(Context paramContext)
    {
        this.lParam = new LinearLayout.LayoutParams(-1, -2);
        return this.lParam;
    }

    private void paintBlockLine(Context paramContext, ViewGroup paramViewGroup, boolean paramBoolean)
    {
        this.lineView = new View(paramContext);
        this.lParam = getLinearParams(paramContext);
        this.lineView.setLayoutParams(this.lParam);
        paramViewGroup.addView(this.lineView);
    }

    private void paintBlockTextView(Context paramContext, ViewGroup paramViewGroup, SpannableStringBuilder paramSpannableStringBuilder, int paramInt1, int paramInt2)
    {
        this.blockTv = new SelectTextView(paramContext);
        this.lParam = getLinearParams(paramContext);
        this.blockTv.setText(paramSpannableStringBuilder, TextView.BufferType.SPANNABLE);
        int i = 0;
        int j = 0;
        this.lParam.setMargins(i, j, i, j);
        this.blockTv.setLayoutParams(this.lParam);
        paramViewGroup.addView(this.blockTv);
//        popUtils.initListener(paramContext, this.blockTv, paramSpannableStringBuilder, paramInt1, paramInt2, this.noteList);
    }

    private void putTextSpanViewSettings(Context paramContext, TextView paramTextView, SpannableStringBuilder paramSpannableStringBuilder, int paramInt1, int paramInt2, int paramInt3)
    {
        paramTextView.setText(paramSpannableStringBuilder, TextView.BufferType.SPANNABLE);
    }

    public void addTypeView(Activity paramActivity, ViewGroup paramViewGroup, int type, SpannableStringBuilder paramSpannableStringBuilder, String imgWidth, String imgHeight,String imgUrl, int wordsLength, int spaces)
    {
//        Logger.i("paramInt1="+type);
        if ((paramSpannableStringBuilder != null) && (paramSpannableStringBuilder.length() > 0))
        {
            switch (type){
                case 0:
                case 1:
                case 2:
                case 6:
                    addTextSpanView(paramActivity, paramViewGroup, paramSpannableStringBuilder, type, wordsLength, spaces);
                    break;
                case 3:
                    addH3TextView(paramActivity, paramViewGroup, paramSpannableStringBuilder, type, wordsLength, spaces);
                    break;
                case 4:
                    addH4TextView(paramActivity, paramViewGroup, paramSpannableStringBuilder, type, wordsLength, spaces);
                    break;
                case 5:
                    addH5TextView(paramActivity, paramViewGroup, paramSpannableStringBuilder, type, wordsLength, spaces);
                    break;
                case 7:
                    addBlock(paramActivity, paramViewGroup, paramSpannableStringBuilder, wordsLength, spaces);
                    break;
                case 8:
                    addPoetry(paramActivity, paramViewGroup, paramSpannableStringBuilder, wordsLength, spaces);
                    break;
                case 9:
                    addImageView(paramActivity, paramSpannableStringBuilder, paramViewGroup, imgWidth,imgHeight, imgUrl);
                    break;
                case 10:
                    addLines(paramActivity, paramViewGroup, "LINE_HR");
                    break;
            }

        }
    }
}
