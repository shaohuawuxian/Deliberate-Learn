package com.zs.learn.english.view;


import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.chenenyu.router.Router;
import com.zs.learn.R;
import com.zs.learn.base.BaseFragment;

/**
 * Created by shao on 2017/7/8.
 */

public class EnglishFragment extends BaseFragment implements View.OnClickListener{


    @Override
    protected int getLayoutId() {
        return R.layout.english_fragment_index;
    }

    @Override
    protected void initView() {
        getView().findViewById(R.id.english_fragment_index_today).setOnClickListener(this);
        getView().findViewById(R.id.english_fragment_index_week).setOnClickListener(this);
        getView().findViewById(R.id.english_fragment_index_recall).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.english_fragment_index_today:
                Router.build("wordlist").activityOptions(ActivityOptionsCompat.makeBasic()).go(getActivity());
                break;
            case R.id.english_fragment_index_week:
                break;
            case R.id.english_fragment_index_recall:
                break;
        }
    }
}
