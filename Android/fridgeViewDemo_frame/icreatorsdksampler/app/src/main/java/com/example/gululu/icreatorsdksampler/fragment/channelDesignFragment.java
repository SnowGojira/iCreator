package com.example.gululu.icreatorsdksampler.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.adapter.channelAdapter;
import com.example.gululu.icreatorsdksampler.bean.ChannelBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class channelDesignFragment extends Fragment {
    @Bind(R.id.rl_channel)
    RecyclerView rlChannel;



    private List mList;
    private channelAdapter mAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_channel,container,false);
        ButterKnife.bind(this, v);
        loadCollection();
        return v;
    }

    private void initData() {
        mList=new ArrayList<ChannelBean>();
        ChannelBean bean1=new ChannelBean("测试数据一");
        ChannelBean bean2=new ChannelBean("测试数据二");
        ChannelBean bean3=new ChannelBean("测试数据三");
        ChannelBean bean4=new ChannelBean("测试数据四");
        ChannelBean bean5=new ChannelBean("测试数据五");
        ChannelBean bean6=new ChannelBean("测试数据六");
        ChannelBean bean7=new ChannelBean("测试数据七");
        ChannelBean bean8=new ChannelBean("测试数据八");

        mList.add(bean1);
        mList.add(bean2);
        mList.add(bean3);
        mList.add(bean4);
        mList.add(bean5);
        mList.add(bean6);
        mList.add(bean7);
        mList.add(bean8);

    }

    private void loadCollection(){
        try{initData();}
        catch (Exception e){ Log.i("channelFragment", e + "");}
        mAdapter=new channelAdapter(mList,getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rlChannel.setLayoutManager(layoutManager);
        rlChannel.setAdapter(mAdapter);

    }



}


