package com.example.gululu.icreatorsdksampler.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.gululu.icreatorsdksampler.PullRefreshModule.PullToRefreshView;
import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.adapter.WorldAdapter;
import com.example.gululu.icreatorsdksampler.adapter.WorldSecondPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WorldSecondPage extends Activity {
    @Bind(R.id.rv_world_model)
    RecyclerView rvWorldModel;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefreshView;
    @Bind(R.id.title_back_show)
    ImageButton titleBack;

    private WorldSecondPageAdapter mAdapter;
    private List mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_second_page);
        ButterKnife.bind(this);
        loadWorldModel();
    }

    private void loadWorldModel(){
        try{initData();}
        catch (Exception e){ Log.i("Haku", e + "");}
        mAdapter=new WorldSecondPageAdapter(mList,WorldSecondPage.this);
        final GridLayoutManager layoutManager = new GridLayoutManager(WorldSecondPage.this,3);
        rvWorldModel.setLayoutManager(layoutManager);
        rvWorldModel.setAdapter(mAdapter);

        /**
         * 下拉刷新
         */
        pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshView.setRefreshing(false);
                        //更新数据的地方
                    }
                }, 2000);
            }
        });

        /**
         * 点击回撤
         */
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initData() {
        mList=new ArrayList<String>();

        mList.add("测试数据一");
        mList.add("测试数据二");
        mList.add("测试数据三");
        mList.add("测试数据四");
        mList.add("测试数据五");
        mList.add("测试数据六");
        mList.add("测试数据七");
        mList.add("测试数据八");
        mList.add("测试数据九");
        mList.add("测试数据十");
        mList.add("测试数据十一");


    }
}
