package com.zs.learn.designpattern;

import com.chenenyu.router.Router;
import com.zs.learn.R;
import com.zs.learn.base.BaseFragment;

/**
 * Created by zhangshao on 2017/9/26.
 */

public class DesignPatternMainFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.designpattern_fragment_main;
    }

    @Override
    protected void initView() {
        getView().findViewById(R.id.designpattern_fragment_main_button_list).setOnClickListener(view->{
            Router.build("designpatternlist")
                    .go(getActivity());
        });
    }
}
