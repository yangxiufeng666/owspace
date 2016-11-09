package com.github.baby.owspace.util.tool;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.widget.LinearLayout;

import com.github.baby.owspace.util.PaintViewUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/10
 * owspace
 */
public class AnalysisHTML {
    public String HTML_STRING = "string";
    public String HTML_URL = "url";
    private int b;
    private Activity context;
    private Document doc;
    private int forwardLen;
    private LinearLayout fuView;
    private int g;
    private Handler handler = new Handler() {
        public void handleMessage(Message paramAnonymousMessage) {
//            infoObj.setDocumentInfo(doc);
            parseDocument(doc);
        }
    };
    private String imgHeight;
    private String imgUrl;
    private String imgWidth;
    private Elements pChild;
    private PaintViewUtil paintViewUtil;
    private int r;
    private int space = 10;
    private StyleSpan span;
    private SpannableStringBuilder ssb;
    private Thread strThread;
    private Thread urlThread;
    private int viewType = -1;
    private int wordsLength;

    private void loadHtmlString(final String paramString) {
        this.strThread = new Thread(new Runnable() {
            public void run() {
                doc = Jsoup.parseBodyFragment(paramString);
                handler.sendEmptyMessage(0);
            }
        });
        this.strThread.start();
    }

    private void loadHtmlUrl(final String paramString) {
        this.urlThread = new Thread(new Runnable() {
            public void run() {
                try {
                    doc = Jsoup.connect(paramString).get();
                    handler.sendEmptyMessage(0);
                    return;
                } catch (IOException localIOException) {
                    localIOException.printStackTrace();
                }
            }
        });
        this.urlThread.start();
    }

    private void parseChildOfPH(Element paramElement) {
        String str1 = paramElement.text().replaceAll("br;", "\n");
        if (!TextUtils.isEmpty(str1)) {
            ssb = new SpannableStringBuilder("\n" + str1);
            if (paramElement.nodeName().equals("h1")) {
                viewType = 1;
            } else if (paramElement.nodeName().equals("h2")) {
                viewType = 2;
            } else if (paramElement.nodeName().equals("h3")) {
                viewType = 3;
            } else if (paramElement.nodeName().equals("h4")) {
                viewType = 4;
            } else if (paramElement.nodeName().equals("h5")) {
                viewType = 5;
            } else if (paramElement.nodeName().equals("h6")) {
                viewType = 6;
            } else if (paramElement.nodeName().equals("block")) {
                viewType = 7;
            } else if (paramElement.nodeName().equals("poetry")) {
                viewType = 8;
            } else if (paramElement.nodeName().equals("hr")) {
                this.viewType = 10;
            } else {
                viewType = 0;
                if (paramElement.nodeName().contains("strong")){
                    viewType=11;
                }
                ssb = new SpannableStringBuilder("\n"+setFirstLineSpace(str1,2));

            }
            paintViewUtil.addTypeView(context, this.fuView, this.viewType, ssb, null, null, null, this.wordsLength, 4 * space);
        }
    }

    private void parseDocument(Document paramDocument) {
//        Logger.e("paramDocument="+paramDocument);
        this.wordsLength = this.forwardLen;
        paintViewUtil = new PaintViewUtil(context);
        Iterator localIterator = paramDocument.getAllElements().iterator();
        while (localIterator.hasNext()) {
            Element localElement = (Element) localIterator.next();
//            Logger.e("localElement="+localElement);
            if ((localElement.nodeName().matches("p[1-9]?[0-9]?")) || (localElement.nodeName().matches("h[1-9]?[0-9]?")) || (localElement.nodeName().matches("poetry")) || (localElement.nodeName().matches("block"))) {
                parseChildOfPH(localElement);
            }
            if (localElement.nodeName().matches("img")) {
                viewType = 9;
                imgUrl = localElement.attr("src");
                if (this.imgUrl.isEmpty())
                    imgUrl = localElement.attr("href");
                if (!TextUtils.isEmpty(localElement.attr("jump_url")))
                    ssb = new SpannableStringBuilder(localElement.attr("jump_url"));
                this.imgWidth = localElement.attr("width");
                this.imgHeight = localElement.attr("height");
                this.paintViewUtil.addTypeView(context, fuView, viewType, ssb, imgWidth, imgHeight, imgUrl, 0, 0);
            }
        }
    }

    private String setFirstLineSpace(String str,int paramInt) {
        for (int i = paramInt; i >= 0; i--) {
            str = "  " + str;
        }
        return str;
    }

    private SpannableStringBuilder setTextSpan(int paramInt1, int paramInt2, int paramInt3) {
        if (paramInt1 < 0)
            paramInt1 = 0;
        if (paramInt2 > this.ssb.length())
            paramInt2 = this.ssb.length();
        this.span = new StyleSpan(paramInt3);
        this.ssb.setSpan(this.span, paramInt1, paramInt2, 33);
        return this.ssb;
    }

    public int getRandomColor() {
        Random localRandom = new Random();
        this.r = (200 + localRandom.nextInt(50));
        this.g = (200 + localRandom.nextInt(50));
        this.b = (200 + localRandom.nextInt(50));
        return Color.rgb(this.r, this.g, this.b);
    }

    public void loadHtml(Activity paramActivity, String content, String type, LinearLayout paramLinearLayout, int paramInt) {
        this.context = paramActivity;
        this.fuView = paramLinearLayout;
        this.forwardLen = paramInt;
        String str = content.replaceAll("<br/>", "br;");
        if (type.equals(this.HTML_URL)) {
            loadHtmlUrl(str);
        } else if (type.equals(this.HTML_STRING)) {
            loadHtmlString(str);
        }

    }

}
