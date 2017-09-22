package com.zs.learn.codeview.view;

import android.support.v7.widget.RecyclerView;

import com.zs.learn.R;
import com.zs.learn.base.BaseFragment;

/**
 * Created by zhangshao on 2017/9/22.
 */

public class CodeViewMainFragment extends BaseFragment {

    RecyclerView mRecyclerView;
    @Override
    protected int getLayoutId() {
        return R.layout.codeview_fragment;
    }

    @Override
    protected void initView() {
        mRecyclerView=(RecyclerView)getView().findViewById(R.id.codeview_list);
    }
}
