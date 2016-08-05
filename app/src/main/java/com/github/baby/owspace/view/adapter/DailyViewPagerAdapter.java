package com.github.baby.owspace.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.view.fragment.DailyItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/4
 * owspace
 */
public class DailyViewPagerAdapter extends FragmentStatePagerAdapter{
    private List<Item> artList = new ArrayList<>();
    private Context context;

    public DailyViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return DailyItemFragment.getInstance(artList.get(position));
    }

    @Override
    public int getCount() {
        return artList.size();
    }
    public void setArtList(List<Item> artList) {
        int position = artList.size() - 1;
        this.artList.addAll(artList);
        notifyDataSetChanged();
    }
    public void replaceAllData(List<Item> artList){
        this.artList.clear();
        this.artList.addAll(artList);
        notifyDataSetChanged();
    }
    public String getLastItemId(){
        if (artList.size()==0){
            return "0";
        }
        Item item = artList.get(artList.size()-1);
        return item.getId();
    }
    public String getLastItemCreateTime(){
        if (artList.size()==0){
            return "0";
        }
        Item item = artList.get(artList.size()-1);
        return item.getCreate_time();
    }
}
