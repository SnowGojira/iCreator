package com.example.gululu.icreatorsdksampler.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gululu.icreatorsdksampler.R;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gululu.icreatorsdksampler.PullRefreshModule.PullToRefreshView;
import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.adapter.CollectAdapter;
import com.example.gululu.icreatorsdksampler.adapter.WorldAdapter;
import com.example.gululu.icreatorsdksampler.database.HistoryDBConfig;
import com.example.gululu.icreatorsdksampler.database.HistroryDBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class worldFragment extends Fragment {
    @Bind(R.id.rv_world)
    RecyclerView rvWorld;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefreshView;

    private WorldAdapter mAdapter;
    private List mList;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_world,container,false);
        ButterKnife.bind(this, v);
        loadWorld();
        return v;
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

    private void loadWorld(){
        try{initData();}
        catch (Exception e){ Log.i("Haku", e + "");}
        mAdapter=new WorldAdapter(mList,getActivity());
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        rvWorld.setLayoutManager(layoutManager);
        rvWorld.setAdapter(mAdapter);

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

    }


}





