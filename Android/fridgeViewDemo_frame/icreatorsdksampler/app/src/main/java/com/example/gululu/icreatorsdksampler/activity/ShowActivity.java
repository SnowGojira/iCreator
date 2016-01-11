package com.example.gululu.icreatorsdksampler.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.gululu.icreatorsdksampler.QrModule.encoding.EncodingHandler;
import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.fragment.NotFoundFragment;
import com.example.gululu.icreatorsdksampler.fragment.ShowViewFragment;
import com.example.gululu.icreatorsdksampler.utils.ConvertStringUtils;
import com.google.zxing.WriterException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShowActivity extends FragmentActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private ShowViewFragment fragment;
    private NotFoundFragment fragment404;

    @Bind(R.id.title_back)
    ImageButton btn_back;
   /* @Bind(R.id.tv_title)
    TextView tv_title;*/
    @Bind(R.id.iv_encode)
    ImageView ivEncode;
    @Bind(R.id.tv_history)
    TextView tvHistory;
    @Bind(R.id.tv_history_eng)
    TextView tvHistoryEng;
    @Bind(R.id.ll_collection)
    LinearLayout llCollection;


    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        ButterKnife.bind(this);
        initLayout();

        mIntent=getIntent();

        if (mIntent!=null){
            String strUID=mIntent.getExtras().getString("UID");
            try {
                Bitmap bitQR = EncodingHandler.createQRCode(strUID, 400);
                ivEncode.setImageBitmap(bitQR);
                tvHistory.setText(ConvertStringUtils.convertToName(strUID));
                tvHistoryEng.setText(ConvertStringUtils.translateName(strUID));
            } catch (WriterException e) {
                e.printStackTrace();
            }
            if (ConvertStringUtils.isUuid(strUID)){

                    LoadGLViewFragment(ShowActivity.this,strUID);


            }else{
                Load404Fragment();
            }
        }

        ImageButton btn= (ImageButton) findViewById(R.id.title_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void initLayout(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //tv_title.setText("详细页");
    }

    private void LoadGLViewFragment(Context context,String uid){
        Log.i("Haku+testActi", uid);
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        //fragment=new ShowViewFragment(context,uid);
        fragment=new ShowViewFragment();
        fragment.setUid(uid);
        ft.replace(R.id.frame_glview, fragment);
        ft.commit();
    }

    private void Load404Fragment(){
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        fragment404=new NotFoundFragment();
        ft.replace(R.id.frame_glview, fragment404);
        ft.commit();

        /**
         * 不显示收藏区域
         * update 2016-1-11
         */

        llCollection.setVisibility(View.GONE);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

}
