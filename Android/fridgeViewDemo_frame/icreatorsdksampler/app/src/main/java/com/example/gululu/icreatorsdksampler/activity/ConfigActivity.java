package com.example.gululu.icreatorsdksampler.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.utils.DataCleanManager;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ConfigActivity extends Activity implements View.OnClickListener{
    @Bind(R.id.title_back)
    ImageButton titleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_config_clear)
    LinearLayout llConfigClear;
    @Bind(R.id.ll_config_feedback)
    LinearLayout llConfigFeedback;
    @Bind(R.id.ll_config_contract)
            LinearLayout llConfigContact;
    @Bind(R.id.txt_config_contact)
            TextView tvConfigContact;
    Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        ButterKnife.bind(this);

        initBraBra();

    }

    private void initBraBra() {

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("设置页面");
        llConfigFeedback.setOnClickListener(this);
        llConfigClear.setOnClickListener(this);
        llConfigContact.setOnClickListener(this);
    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_config_feedback:
                mIntent=new Intent(ConfigActivity.this,FeedBackActivity.class);
                startActivity(mIntent);
                break;
            case R.id.ll_config_clear:
                ClearCacheDialog();
                break;
            case R.id.ll_config_contract:
                String mString= (String) tvConfigContact.getText();
                Uri uri=Uri.parse("tel:"+mString);
                mIntent=new Intent(Intent.ACTION_DIAL,uri);
                startActivity(mIntent);
        }

    }

    /**
     * 清空缓存
     */
    private void ClearCacheDialog() {

        File file = new File("/data/data/com.example.gululu.icreatorsdksampler/cache");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("是否清空缓存");
        try {
            builder.setMessage("缓存:" + DataCleanManager.getCacheSize(file) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(ConfigActivity.this, "取消" + which, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                DataCleanManager.cleanInternalCache(ConfigActivity.this);
            }
        });

        //参数都设置完成了，创建并显示出来
        builder.create().show();

    }
}
