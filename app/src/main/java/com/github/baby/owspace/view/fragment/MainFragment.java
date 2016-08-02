package com.github.baby.owspace.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.baby.owspace.R;
import com.github.baby.owspace.model.entity.Item;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/2
 * owspace
 */
public class MainFragment extends Fragment {
    String title;
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

    public static Fragment instance(Item item) {
        Fragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("item", item);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.item_main_page, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Item item = getArguments().getParcelable("item");
        title = item.getTitle();
        Glide.with(this.getContext()).load(item.getThumbnail()).centerCrop().into(imageIv);
        commentTv.setText(item.getComment());
        likeTv.setText(item.getGood());
        readcountTv.setText(item.getView());
        titleTv.setText(item.getTitle());
        contentTv.setText(item.getExcerpt());
        authorTv.setText(item.getAuthor());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
