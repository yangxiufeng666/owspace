package com.github.baby.owspace.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.view.fragment.MainFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/2
 * owspace
 */
public class VerticalPagerAdapter extends FragmentStatePagerAdapter{
    private List<Item> dataList=new ArrayList<>();
    public VerticalPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.instance(dataList.get(position));
    }

    @Override
    public int getCount() {
        return dataList.size();
    }
    public void setDataList(List<Item> data){
        dataList.addAll(data);
        notifyDataSetChanged();
    }
    public String getLastItemId(){
        if (dataList.size()==0){
            return "0";
        }
        Item item = dataList.get(dataList.size()-1);
        return item.getId();
    }
    public String getLastItemCreateTime(){
        if (dataList.size()==0){
            return "0";
        }
        Item item = dataList.get(dataList.size()-1);
        return item.getCreate_time();
    }
}
