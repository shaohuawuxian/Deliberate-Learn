package com.zs.learn.english.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
 */
@Route("wordlist")
public class EnglishWordListActivity extends BaseActivity{
    RecyclerView mRecyclerView;
    WordListAdapter mListAdapter;
    String dataType;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_wordlist);
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
        dataType=getIntent().getStringExtra("dataType");
        List<EnglishWord> wordList=null;
        Calendar c = Calendar.getInstance();
        switch (dataType){
            case "day":
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
        if(wordList!=null){
            mListAdapter.addAll(wordList);
        }

    }

    @Subscribe
    public void dealEventBus(EnglishWord word) {
        if(word!=null){
            mListAdapter.clear();
            List<EnglishWord> list=DataSupport.findAll(EnglishWord.class);
            mListAdapter.addAll(list);
        }
    }

    private static class WordListAdapter extends SuperAdapter<EnglishWord>{

        public WordListAdapter(Context context) {
            super(context, null, R.layout.english_item_wordlist);
        }

        @Override
        public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, EnglishWord item) {
            holder.setText(R.id.english_item_wordlist_word,item.word);
        }
    }
}
