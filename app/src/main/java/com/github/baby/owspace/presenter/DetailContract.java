package com.github.baby.owspace.presenter;

import com.github.baby.owspace.model.entity.DetailEntity;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/9
 * owspace
 */
public interface DetailContract {
    interface Presenter{
        void getDetail(String itemId);
    }
    interface View{
        void showLoading();
        void dismissLoading();
        void updateListUI(DetailEntity detailEntity);
        void showOnFailure();
    }
}
