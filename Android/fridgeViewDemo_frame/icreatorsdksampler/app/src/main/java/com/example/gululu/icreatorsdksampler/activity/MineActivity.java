package com.example.gululu.icreatorsdksampler.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.gululu.icreatorsdksampler.QrModule.activity.CaptureActivity;
import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.adapter.CollectAdapter;
import com.example.gululu.icreatorsdksampler.adapter.tabFragmentAdapter;
import com.example.gululu.icreatorsdksampler.customizeview.RoundedImageViewEx;
import com.example.gululu.icreatorsdksampler.customizeview.fab.FloatingActionMenu;
import com.example.gululu.icreatorsdksampler.database.HistoryDBConfig;
import com.example.gululu.icreatorsdksampler.database.HistroryDBHelper;
import com.example.gululu.icreatorsdksampler.fragment.collectFragment;
import com.example.gululu.icreatorsdksampler.fragment.worldFragment;
import com.example.gululu.icreatorsdksampler.utils.AppUtils;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MineActivity extends AppCompatActivity {
    private static final int USER_OPTIONS_ANIMATION_DELAY = 300;
    private static final Interpolator INTERPOLATOR = new DecelerateInterpolator();
    public static final int CHANGE_USERINFO = 101;

    @Bind(R.id.ivLogo)
    ImageButton ivLogo;
    @Bind(R.id.fab_design)
    com.example.gululu.icreatorsdksampler.customizeview.fab.FloatingActionButton fabDesign;
    @Bind(R.id.fab_home)
    com.example.gululu.icreatorsdksampler.customizeview.fab.FloatingActionButton fabHome;
    @Bind(R.id.fab_qr)
    com.example.gululu.icreatorsdksampler.customizeview.fab.FloatingActionButton fabQR;
    @Bind(R.id.btnCreate)
    FloatingActionMenu fabCreate;
    /**
     * 更换User的信息
     */
    @Bind(R.id.ivUserProfilePhoto)
    RoundedImageViewEx ivUserPhoto;
    @Bind(R.id.tvUserName)
    TextView tvUserName;

    /**
     * 设置tabLayout
     */
    @Bind(R.id.tlUserProfileTabs)
    TabLayout tlUserProfilTabs;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;



    /**
     * 设置recycleView
     */
    /*@Bind(R.id.rvUserProfile)
    RecyclerView rvUserProfile;*/
    CollectAdapter collectAdapter;

    private Intent mIntent;
    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";

    private List mList;
    private CollectAdapter mAdapter;
    private StringBuilder strBuild;

    private HistroryDBHelper hisDBHelper;
    private SQLiteDatabase hisDB;
    private HashMap<String,Object> queryMap;

    private String name=null;
    private byte photo[]=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
        ButterKnife.bind(this);

        jumpToActivities();
        setupTabs();
    }

    /**
     * 跳转到配置页面
     */
    private void jumpToActivities() {
        fabCreate.hideMenuButton(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fabCreate.showMenuButton(true);
                fabCreate.setMenuButtonShowAnimation(AnimationUtils.loadAnimation(MineActivity.this, R.anim.show_from_bottom));
                fabCreate.setMenuButtonHideAnimation(AnimationUtils.loadAnimation(MineActivity.this, R.anim.hide_to_bottom));
            }
        }, 300);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(getApplicationContext(), ConfigActivity.class);
                startActivityForResult(mIntent, 101);
            }
        });

        fabDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*添加function*/
            }
        });
        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*添加home跳转*/
                mIntent=new Intent(MineActivity.this,ChannelActivity.class);
                startActivity(mIntent);
                finish();
            }
        });
        fabQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*跳转QR*/
                mIntent = new Intent(MineActivity.this, CaptureActivity.class);
                startActivityForResult(mIntent, 102);
            }
        });

        /**
         * 跳转到到ChangeUserInfo界面
         */


        ivUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name= tvUserName.getText().toString();
                byte photo[]= AppUtils.drawable2Bytes(ivUserPhoto.getDrawable());
                mIntent=new Intent(MineActivity.this,ChangeUserInfoActivity.class);
                mIntent.putExtra("Photo",photo);
                mIntent.putExtra("Name", name);
                Log.i("MineActivity", name + " " + photo);
                startActivityForResult(mIntent, 103);
                Log.i("MineActivity", "start activity");
            }
        });
    }

    /**
     * 滑动的切换tab
     */
    private void setupTabs() {
        /*默认选中*/
        List<String> tabList = new ArrayList<>();
        tabList.add("我的收藏");
        tabList.add("我的世界");
        //设置tab模式，当前为系统默认模式
        tlUserProfilTabs.setTabMode(TabLayout.MODE_FIXED);
        //添加tab选项卡
        tlUserProfilTabs.addTab(tlUserProfilTabs.newTab().setText(tabList.get(0)));
        tlUserProfilTabs.addTab(tlUserProfilTabs.newTab().setText(tabList.get(1)));

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new collectFragment());
        fragmentList.add(new worldFragment());

        tabFragmentAdapter fragmentAdapter = new tabFragmentAdapter(getSupportFragmentManager(), fragmentList, tabList);
        mViewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
        tlUserProfilTabs.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        tlUserProfilTabs.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器

    }

   /* //*滑动的动画效果*//*
    private void animateUserProfileOptions() {
        tlUserProfilTabs.setTranslationY(-tlUserProfilTabs.getHeight());
        tlUserProfilTabs.animate().translationY(0).setDuration(300).setStartDelay(USER_OPTIONS_ANIMATION_DELAY).setInterpolator(INTERPOLATOR);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode==103){
            Bundle bundle=data.getBundleExtra("ChangedUserInfo");
            String name=bundle.getString("ChangedName");
            byte buff[]=bundle.getByteArray("ChangedPhoto");
            Bitmap photo = BitmapFactory.decodeByteArray(buff, 0, buff.length);
            Log.i("Mine Activity",name+" "+photo);

            ivUserPhoto.setImageBitmap(photo);
            tvUserName.setText(name);
        }
    }
    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

}
