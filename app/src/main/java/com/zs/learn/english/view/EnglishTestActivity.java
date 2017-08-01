package com.zs.learn.english.view;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chenenyu.router.annotation.Route;
import com.zs.learn.R;
import com.zs.learn.base.BaseActivity;

/**
 * Created by shao on 2017/7/8.
 */
@Route("english_test")
public class EnglishTestActivity extends BaseActivity {

    EditText inputText;
    TextView annotationText;
    Button ensureButton;
    @Override
    protected void initView() {
        setContentView(R.layout.english_test);
        inputText=(EditText)findViewById(R.id.english_test_input);
        annotationText=(TextView)findViewById(R.id.english_test_annotation);
        ensureButton=(Button)findViewById(R.id.english_test_ensure);
        ensureButton.setOnClickListener(view->{

        });
    }

    @Override
    protected void initData() {

    }
}
