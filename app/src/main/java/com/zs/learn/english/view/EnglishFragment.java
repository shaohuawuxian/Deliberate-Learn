package com.zs.learn.english.view;


import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.chenenyu.router.Router;
import com.zs.learn.R;
import com.zs.learn.base.BaseFragment;

/**
 * Created by shao on 2017/7/8.
 */

public class EnglishFragment extends BaseFragment{


    @Override
    protected int getLayoutId() {
        return R.layout.english_fragment_index;
    }

    @Override
    protected void initView() {
        getView().findViewById(R.id.english_fragment_index_today)
                .setOnClickListener(view -> {
                    Router.build("wordlist")
                            .with("dataType","day")
                            .go(getActivity());
                });
        getView().findViewById(R.id.english_fragment_index_week)
                .setOnClickListener(view->{
                    Router.build("wordlist")
                            .with("dataType","week")
                            .go(getActivity());
                });
        getView().findViewById(R.id.english_fragment_index_recall).setOnClickListener(view->{});
    }

}
