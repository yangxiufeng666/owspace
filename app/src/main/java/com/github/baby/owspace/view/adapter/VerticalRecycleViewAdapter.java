package com.github.baby.owspace.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.baby.owspace.R;
import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.view.activity.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/9/1
 * owspace
 */
public class VerticalRecycleViewAdapter extends RecyclerView.Adapter<VerticalRecycleViewAdapter.ViewHolder>{
    private List<Item> dataList=new ArrayList<>();
    private Context context;

    public VerticalRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_main_page, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = dataList.get(position);
        final int model = Integer.valueOf(item.getModel());
        if (model == 5){
            holder.pagerContent.setVisibility(View.GONE);
            holder.homeAdvertiseIv.setVisibility(View.VISIBLE);
            Glide.with(context).load(item.getThumbnail()).centerCrop().into(holder.homeAdvertiseIv);
        }else{
            holder.pagerContent.setVisibility(View.VISIBLE);
            holder.homeAdvertiseIv.setVisibility(View.GONE);
            Glide.with(context).load(item.getThumbnail()).centerCrop().into(holder.imageIv);
            holder.commentTv.setText(item.getComment());
            holder.likeTv.setText(item.getGood());
            holder.readcountTv.setText(item.getView());
            holder.titleTv.setText(item.getTitle());
            holder.contentTv.setText(item.getExcerpt());
            holder.authorTv.setText(item.getAuthor());
            holder.typeTv.setText(item.getCategory());
            switch (model) {
                case 2:
                    holder.imageType.setVisibility(View.VISIBLE);
                    holder.downloadStartWhite.setVisibility(View.GONE);
                    holder.imageType.setImageResource(R.drawable.library_video_play_symbol);
                    break;
                case 3:
                    holder.imageType.setVisibility(View.VISIBLE);
                    holder.downloadStartWhite.setVisibility(View.VISIBLE);
                    holder.imageType.setImageResource(R.drawable.library_voice_play_symbol);
                    break;
                default:
                    holder.downloadStartWhite.setVisibility(View.GONE);
                    holder.imageType.setVisibility(View.GONE);
            }
        }
        holder.typeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model==5){
                    Uri uri = Uri.parse(item.getHtml5());
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("itemId", item.getId());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void addItems(List<Item> items){
        int position = dataList.size();
        dataList.addAll(items);
        notifyItemChanged(position);
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
    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.image_iv)
        ImageView imageIv;
        @Bind(R.id.type_container)
        LinearLayout typeContainer;
        @Bind(R.id.comment_tv)
        TextView commentTv;
        @Bind(R.id.like_tv)
        TextView likeTv;
        @Bind(R.id.readcount_tv)
        TextView readcountTv;
        @Bind(R.id.title_tv)
        TextView titleTv;
        @Bind(R.id.content_tv)
        TextView contentTv;
        @Bind(R.id.author_tv)
        TextView authorTv;
        @Bind(R.id.type_tv)
        TextView typeTv;
        @Bind(R.id.time_tv)
        TextView timeTv;
        @Bind(R.id.image_type)
        ImageView imageType;
        @Bind(R.id.download_start_white)
        ImageView downloadStartWhite;
        @Bind(R.id.home_advertise_iv)
        ImageView homeAdvertiseIv;
        @Bind(R.id.pager_content)
        LinearLayout pagerContent;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
