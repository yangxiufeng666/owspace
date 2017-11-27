package com.github.baby.owspace.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.baby.owspace.R;
import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.view.activity.AudioDetailActivity;
import com.github.baby.owspace.view.activity.DetailActivity;
import com.github.baby.owspace.view.activity.VideoDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/2
 * owspace
 */
public class MainFragment extends Fragment {
    String title;
    @BindView(R.id.image_iv)
    ImageView imageIv;
    @BindView(R.id.type_container)
    LinearLayout typeContainer;
    @BindView(R.id.comment_tv)
    TextView commentTv;
    @BindView(R.id.like_tv)
    TextView likeTv;
    @BindView(R.id.readcount_tv)
    TextView readcountTv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.content_tv)
    TextView contentTv;
    @BindView(R.id.author_tv)
    TextView authorTv;
    @BindView(R.id.type_tv)
    TextView typeTv;
    @BindView(R.id.time_tv)
    TextView timeTv;
    @BindView(R.id.image_type)
    ImageView imageType;
    @BindView(R.id.download_start_white)
    ImageView downloadStartWhite;
    @BindView(R.id.home_advertise_iv)
    ImageView homeAdvertiseIv;
    @BindView(R.id.pager_content)
    RelativeLayout pagerContent;

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
        final Item item = getArguments().getParcelable("item");
        final int model = Integer.valueOf(item.getModel());
        if (model == 5){
            pagerContent.setVisibility(View.GONE);
            homeAdvertiseIv.setVisibility(View.VISIBLE);
            Glide.with(this.getContext()).load(item.getThumbnail()).centerCrop().into(homeAdvertiseIv);
        }else{
            pagerContent.setVisibility(View.VISIBLE);
            homeAdvertiseIv.setVisibility(View.GONE);
            title = item.getTitle();
            Glide.with(this.getContext()).load(item.getThumbnail()).centerCrop().into(imageIv);
            commentTv.setText(item.getComment());
            likeTv.setText(item.getGood());
            readcountTv.setText(item.getView());
            titleTv.setText(item.getTitle());
            contentTv.setText(item.getExcerpt());
            authorTv.setText(item.getAuthor());
            typeTv.setText(item.getCategory());
            switch (model) {
                case 2:
                    imageType.setVisibility(View.VISIBLE);
                    downloadStartWhite.setVisibility(View.GONE);
                    imageType.setImageResource(R.drawable.library_video_play_symbol);
                    break;
                case 3:
                    imageType.setVisibility(View.VISIBLE);
                    downloadStartWhite.setVisibility(View.VISIBLE);
                    imageType.setImageResource(R.drawable.library_voice_play_symbol);
                    break;
                default:
                    downloadStartWhite.setVisibility(View.GONE);
                    imageType.setVisibility(View.GONE);
            }
        }
        typeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                switch (model){
                    case 5:
                        Uri uri = Uri.parse(item.getHtml5());
                        intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getActivity(), AudioDetailActivity.class);
                        intent.putExtra("item",item);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), VideoDetailActivity.class);
                        intent.putExtra("item",item);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), DetailActivity.class);
                        intent.putExtra("item",item);
                        startActivity(intent);
                        break;
                    default:
                        intent = new Intent(getActivity(), DetailActivity.class);
                        intent.putExtra("item",item);
                        startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
