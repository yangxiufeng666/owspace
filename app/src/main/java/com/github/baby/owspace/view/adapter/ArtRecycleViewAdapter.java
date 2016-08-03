package com.github.baby.owspace.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.baby.owspace.R;
import com.github.baby.owspace.model.entity.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/3
 * owspace
 */
public class ArtRecycleViewAdapter extends RecyclerView.Adapter<ArtRecycleViewAdapter.ArtHolder> {

    private List<Item> artList = new ArrayList<>();
    private Context context;

    public ArtRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ArtHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_art, parent, false);
        return new ArtHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtHolder holder, int position) {
        Item item = artList.get(position);
        holder.authorTv.setText(item.getAuthor());
        holder.titleTv.setText(item.getTitle());
        Glide.with(context).load(item.getThumbnail()).centerCrop().into(holder.imageIv);
    }

    @Override
    public int getItemCount() {
        return artList.size();
    }

    public void setArtList(List<Item> artList) {
        int position = artList.size() - 1;
        this.artList.addAll(artList);
        notifyItemChanged(position);
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

    static class ArtHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image_iv)
        ImageView imageIv;
        @Bind(R.id.arrow_iv)
        ImageView arrowIv;
        @Bind(R.id.title_tv)
        TextView titleTv;
        @Bind(R.id.author_tv)
        TextView authorTv;
        @Bind(R.id.type_container)
        RelativeLayout typeContainer;
        public ArtHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
