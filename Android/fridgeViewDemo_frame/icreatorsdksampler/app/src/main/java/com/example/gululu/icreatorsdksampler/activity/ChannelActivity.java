package com.example.gululu.icreatorsdksampler.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.gululu.icreatorsdksampler.QrModule.activity.CaptureActivity;
import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.customizeview.fab.FloatingActionButton;
import com.example.gululu.icreatorsdksampler.customizeview.fab.FloatingActionMenu;
import com.example.gululu.icreatorsdksampler.fragment.channelArtFragment;
import com.example.gululu.icreatorsdksampler.fragment.channelDesignFragment;
import com.example.gululu.icreatorsdksampler.fragment.channelFragment;
import com.example.gululu.icreatorsdksampler.fragment.channelIndustryFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChannelActivity extends FragmentActivity implements View.OnClickListener{
    @Bind(R.id.btn_channel_art)
    Button btnArt;
    @Bind(R.id.btn_channel_design)
    Button btnDesign;
    @Bind(R.id.btn_channel_product)
    Button btnIndustry;
    @Bind(R.id.btn_channel_property)
    Button btnHome;
    @Bind(R.id.fab_qr)
    FloatingActionButton fabQR;
    @Bind(R.id.fab_design)
    FloatingActionButton fabDesign;
    @Bind(R.id.fab_home)
    FloatingActionButton fabHome;
    @Bind(R.id.btnCreate)
    FloatingActionMenu fabCreate;

    @Bind(R.id.tv_tag_capital)
    TextView tvTagCapital;
    @Bind(R.id.tv_tag_eng)
    TextView tvTagEng;
    @Bind(R.id.tv_tag_cn)
    TextView tvTagCn;

    private Intent mIntent;

    private String[] Design={"D","Design","设计"};
    private String[] Art={"A","Art","艺术"};
    private String[] Industry={"I","Industry","工业"};
    private String[] Home={"H","Home","居家"};

    private FragmentManager fm;
    private FragmentTransaction ft;
    private channelFragment homeFragment;
    private channelArtFragment artFragment;
    private channelDesignFragment designFragment;
    private channelIndustryFragment industryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        ButterKnife.bind(this);

        initLayout();
    }

    private void initLayout() {
        LoadHomeChannel();
        btnArt.setOnClickListener(this);
        btnDesign.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        btnIndustry.setOnClickListener(this);
        fabDesign.setImageDrawable(getResources().getDrawable(R.drawable.ic_mine));
        fabDesign.setOnClickListener(this);
        fabHome.setOnClickListener(this);
        fabQR.setOnClickListener(this);
        /**
         * fabCreate的动效进入。
         */
        fabCreate.hideMenuButton(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fabCreate.showMenuButton(true);
                fabCreate.setMenuButtonShowAnimation(AnimationUtils.loadAnimation(ChannelActivity.this, R.anim.show_from_bottom));
                fabCreate.setMenuButtonHideAnimation(AnimationUtils.loadAnimation(ChannelActivity.this, R.anim.hide_to_bottom));
            }
        }, 300);
    }

    private void LoadHomeChannel(){
        ChangeTag(Home);
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        homeFragment=new channelFragment();
        ft.replace(R.id.fl_replace_channel, homeFragment);
        ft.commit();

    }

    private void LoadArtChannel(){
        ChangeTag(Art);
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        artFragment=new channelArtFragment();
        ft.replace(R.id.fl_replace_channel, artFragment);
        ft.commit();

    }
    private void LoadIndustryChannel(){
        ChangeTag(Industry);
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        industryFragment=new channelIndustryFragment();
        ft.replace(R.id.fl_replace_channel, industryFragment);
        ft.commit();

    }
    private void LoadDesignChannel(){
        ChangeTag(Design);
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        designFragment=new channelDesignFragment();
        ft.replace(R.id.fl_replace_channel, designFragment);
        ft.commit();

    }
    private void ChangeTag(String[] tags){
        tvTagCapital.setText(tags[0]);
        tvTagEng.setText(tags[1]);
        tvTagCn.setText(tags[2]);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_channel_property:
                LoadHomeChannel();
            break;
            case R.id.btn_channel_art:
                LoadArtChannel();
            break;
            case R.id.btn_channel_product:
                LoadIndustryChannel();
            break;
            case R.id.btn_channel_design:
                LoadDesignChannel();
            break;
            case R.id.fab_design:
                mIntent=new Intent(ChannelActivity.this,MineActivity.class);
                startActivity(mIntent);
            break;
            case R.id.fab_home:

            break;
            case R.id.fab_qr:
                mIntent=new Intent(ChannelActivity.this, CaptureActivity.class);
                startActivity(mIntent);
            break;

        }
    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }
}
