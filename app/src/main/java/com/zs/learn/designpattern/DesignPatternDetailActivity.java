package com.zs.learn.designpattern;

import android.widget.TextView;

import com.chenenyu.router.annotation.Route;
import com.zs.learn.R;
import com.zs.learn.base.BaseActivity;

/**
 * Created by zhangshao on 2017/9/27.
 */
@Route("designpattern_detail")
public class DesignPatternDetailActivity extends BaseActivity{
    TextView nameTextView,contentTextView;
    @Override
    protected void initView() {
        setContentView(R.layout.designpattern_activity_detail);
        nameTextView=(TextView)findViewById(R.id.designpattern_detail_name);
        contentTextView=(TextView)findViewById(R.id.designpattern_detail_content);
    }

    @Override
    protected void initData() {
        DesignPattern designPattern=getIntent().getParcelableExtra("dp");
        nameTextView.setText(designPattern.name);
        contentTextView.setText(designPattern.what+"\n"+designPattern.why+"\n"+designPattern.how+"\n"+designPattern.example);
    }
}
