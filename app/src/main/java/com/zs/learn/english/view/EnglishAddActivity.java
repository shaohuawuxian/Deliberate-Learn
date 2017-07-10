package com.zs.learn.english.view;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.chenenyu.router.annotation.Route;
import com.zs.learn.R;
import com.zs.learn.base.BaseActivity;
import com.zs.learn.english.model.EnglishWord;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.callback.SaveCallback;

/**
 * Created by shao on 2017/7/8.
 */
@Route("english_add")
public class EnglishAddActivity extends BaseActivity implements View.OnClickListener{

    EditText englishEdit,chineseEdit;


    @Override
    protected void initView() {
        setContentView(R.layout.english_activity_addword);
        englishEdit=(EditText)findViewById(R.id.english_activity_addword_edittext_word);
        chineseEdit=(EditText)findViewById(R.id.english_activity_addword_edittext_annotation);
        findViewById(R.id.english_activity_addword_button_save).setOnClickListener(
                view->{
                    String english=englishEdit.getText().toString();
                    String chinese=chineseEdit.getText().toString();
                    if(TextUtils.isEmpty(english)){
                        Snackbar.make(view, "还没有输入单词", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(chinese)){
                        Snackbar.make(view, "还没有输入对应汉语翻译", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    final EnglishWord englishWord=new EnglishWord();
                    englishWord.word=english;
                    englishWord.annotation=chinese;
                    englishWord.saveAsync().listen(success -> {
                        EventBus.getDefault().post(englishWord);
                        finish();
                    });
                }
        );
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.english_activity_addword_button_save://sava
                String english=englishEdit.getText().toString();
                String chinese=chineseEdit.getText().toString();
                if(TextUtils.isEmpty(english)){
                    Snackbar.make(v, "还没有输入单词", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(chinese)){
                    Snackbar.make(v, "还没有输入对应汉语翻译", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                final EnglishWord englishWord=new EnglishWord();
                englishWord.word=english;
                englishWord.annotation=chinese;
                englishWord.saveAsync().listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        EventBus.getDefault().post(englishWord);
                        finish();
                        //Snackbar.make(getWindow().getDecorView(), "success="+success, Snackbar.LENGTH_SHORT).show();
                    }
                });
//                EventBus.getDefault().post(englishWord);
//                finish();
                break;
        }
    }
}
