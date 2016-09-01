package com.github.baby.owspace.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import com.github.baby.owspace.R;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/9/1
 * owspace
 */
public class LunarDialog extends Dialog{
    private Context context;
    public LunarDialog(Context context) {
        super(context,R.style.LunarDialg);
        setCanceledOnTouchOutside(true);
        this.context = context;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }
}
