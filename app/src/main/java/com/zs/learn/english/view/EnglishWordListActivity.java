package com.zs.learn.english.view;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.zs.learn.R;
import com.zs.learn.base.BaseActivity;
import com.zs.learn.english.model.EnglishWord;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;
import org.greenrobot.eventbus.Subscribe;
import org.litepal.crud.DataSupport;

import java.util.Calendar;
import java.util.List;

/**
 * Created by shao on 2017/7/8.
 * 金山词霸每日一句：http://sentence.iciba.com/index.php?c=dailysentence&m=getdetail&title=2017-07-11
 * 每日一句API http://open.iciba.com/dsapi/
 */
@Route("wordlist")
public class EnglishWordListActivity extends BaseActivity{
    RecyclerView mRecyclerView;
    WordListAdapter mListAdapter;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    String dataType;
    ImageView headerView;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_englishwordlist);
        headerView=(ImageView)findViewById(R.id.englishwordlist_header_imageview);
        mCollapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.englishwordlist_collapsingToolbarLayout);
        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.wordlist_floating_add);
        fabAdd.setOnClickListener(view->Router.build("english_add").go(getApplicationContext()));
        FloatingActionButton fabTest = (FloatingActionButton) findViewById(R.id.wordlist_floating_test);
        fabTest.setOnClickListener(view->Router.build("english_test").go(getApplicationContext()));
        mRecyclerView=(RecyclerView)findViewById(R.id.wordlist_recyclerview);
        mListAdapter=new WordListAdapter(getApplicationContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mListAdapter);
    }

    @Override
    protected void initData() {
        Glide.with(this).load("http://cdn.iciba.com/news/word/big_20170712b.jpg").into(headerView);
        dataType=getIntent().getStringExtra("dataType");
        List<EnglishWord> wordList=null;
        Calendar c = Calendar.getInstance();
        switch (dataType){
            case "day":
                mCollapsingToolbarLayout.setTitle("今日单词");
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND,0);
                c.set(Calendar.MILLISECOND, 0);
                long start=c.getTimeInMillis();
                c.set(Calendar.HOUR_OF_DAY, 23);
                c.set(Calendar.MINUTE, 59);
                c.set(Calendar.SECOND,59);
                c.set(Calendar.MILLISECOND, 999);
                long end=c.getTimeInMillis();
                wordList=DataSupport
                        .where("insertTime>? and insertTime<?",String.valueOf(start),String.valueOf(end))
                        .find(EnglishWord.class);
                break;
            case "week":
                mCollapsingToolbarLayout.setTitle("本周单词");
                c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);//周日
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND,0);
                c.set(Calendar.MILLISECOND, 0);
                long startWeek=c.getTimeInMillis();
                c.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);//周六
                c.set(Calendar.HOUR_OF_DAY, 23);
                c.set(Calendar.MINUTE, 59);
                c.set(Calendar.SECOND,59);
                c.set(Calendar.MILLISECOND, 999);
                long endWeek=c.getTimeInMillis();
                wordList=DataSupport
                        .where("insertTime>? and insertTime<?",String.valueOf(startWeek),String.valueOf(endWeek))
                        .find(EnglishWord.class);
                break;
        }
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        if(wordList!=null){
            mListAdapter.addAll(wordList);
        }

    }

    @Subscribe
    public void dealEventBus(EnglishWord word) {
        if(word!=null){
            mListAdapter.clear();
            initData();
//            List<EnglishWord> list=DataSupport.findAll(EnglishWord.class);
//            mListAdapter.addAll(list);
        }
    }

    private static class WordListAdapter extends SuperAdapter<EnglishWord>{

        public WordListAdapter(Context context) {
            super(context, null, R.layout.english_item_wordlist);
        }

        @Override
        public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, EnglishWord item) {
            holder.setText(R.id.english_item_wordlist_word,item.word);
            if(TextUtils.isEmpty(item.phonogram)){
                holder.setVisibility(R.id.english_item_wordlist_phonogram,View.GONE);
            }else{
                holder.setVisibility(R.id.english_item_wordlist_phonogram,View.VISIBLE);
                holder.setText(R.id.english_item_wordlist_phonogram,item.phonogram);
            }
            holder.setText(R.id.english_item_wordlist_annotation,item.annotation);
        }
    }
}
