package com.zs.learn.designpattern;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenenyu.router.annotation.Route;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zs.learn.R;
import com.zs.learn.base.BaseActivity;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhangshao on 2017/9/26.
 */
@Route("designpatternlist")
public class DesignPatternListActivity extends BaseActivity {

    RecyclerView mRecyclerView;
    ListAdapter mListAdapter;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_designpattern_list);
        mRecyclerView=(RecyclerView)findViewById(R.id.activity_designpattern_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListAdapter=new ListAdapter(this);
        mRecyclerView.setAdapter(mListAdapter);
    }

    @Override
    protected void initData() {
        try {
            InputStream inputStream=getResources().getAssets().open("designpattern.json");
            InputStreamReader reader=new InputStreamReader(inputStream);
            Gson gson=new Gson();
            Type type=new TypeToken<Collection<DesignPattern>>(){}.getType();
            List<DesignPattern> list= gson.fromJson(reader,type);
            mListAdapter.addAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class ListAdapter extends SuperAdapter<DesignPattern>{


        public ListAdapter(Context context) {
            super(context, null, R.layout.designpattern_activity_list_item);
        }

        @Override
        public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, DesignPattern item) {
                holder.setText(R.id.designpattern_item_name,item.name);
        }
    }
}
