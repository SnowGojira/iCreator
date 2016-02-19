package com.example.gululu.icreatorsdksampler.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gululu.icreatorsdksampler.GL2JNIView;
import com.example.gululu.icreatorsdksampler.R;

/**
 * @author HakuHak
 * @Function generate a new GL2JNIView
 * @date 2015-12-15
 * @version 0.1
 */


public class ShowViewFragment extends Fragment {
    private GL2JNIView myView;
    private String mUid=null;
    private Context mContext;


    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        mUid = uid;
    }

    /*public (Context context, String uid) {
        mContext=context;
        mUid=uid;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View v=inflater.inflate(R.layout.fragment_error,container,false);
       myView=new GL2JNIView(getActivity(),"icreator@dev.com","www.icreator.cn",getUid());
        Log.i("track","fragment create");
       return myView;
       // return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("track","fragment destroy");
    }
}


