package com.example.gululu.icreatorsdksampler.activity;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.database.HistoryDBConfig;
import com.example.gululu.icreatorsdksampler.database.HistroryDBHelper;
import com.example.gululu.icreatorsdksampler.fragment.NaviFragment;
import com.example.gululu.icreatorsdksampler.fragment.NotFoundFragment;
import com.example.gululu.icreatorsdksampler.fragment.ShowViewFragment;
import com.example.gululu.icreatorsdksampler.QrModule.activity.CaptureActivity;
import com.example.gululu.icreatorsdksampler.utils.ConvertStringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;



/**
 * @author HakuHak
 * @Function main function activity
 * @date 2015-12-15
 * @version 0.1
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener{
    @Bind(R.id.scan)
    ImageButton scanButton;
    @Bind(R.id.history)
    ImageButton hisButton;
    @Bind(R.id.tv_qr_code)
    TextView scanText;
    private Intent mIntent;
    private String strResult=null;

    private FragmentManager fm;
    private FragmentTransaction ft;
    private ShowViewFragment fragment;
    private NotFoundFragment fragment404;
    private NaviFragment fragmentnavi;

    private HistroryDBHelper hisDBHelper;
    private final int VERSION=1;
    private StringBuilder strBuild;

    /**
     * 有framelayout来实现界面的更新
     * 可能办法损点
     * 但是先实现出来咩
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ButterKnife.bind(this);
        init();

    }

    private void init(){

        /*create a history DB */
        hisDBHelper=new HistroryDBHelper(MainActivity.this, HistoryDBConfig.dbConfig.DATABASE_NAME,null,VERSION);
        /*init Listener*/
        scanButton.setOnClickListener(this);
        hisButton.setOnClickListener(this);

        /*初始化制定的Fragment*/
        LoadNaviFragment();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            strResult=data.getExtras().getString("result");
            //设置text，用于监测SQLite的传值
            scanText.setText(strResult);
            Toast.makeText(MainActivity.this,strResult,Toast.LENGTH_SHORT).show();

            if (!scanText.equals("")){
                /**
                 *只要是scanText非空，就给我去执行写入DB的过程
                 */
                /*增加数据的过程*/
                ContentValues cv=hisDBHelper.getContentValue(scanText.getText().toString());
                hisDBHelper.insert(cv);
                /**
                 * 写入之后再去判断是该load哪种fragment
                 */
                if (ConvertStringUtils.isUuid(strResult))
                {
                    LoadGLViewFragment(getApplicationContext(),strResult);
                } else{
                    Load404Fragment();
                }

            }else{
                Load404Fragment();
                Toast.makeText(MainActivity.this,"空啦",Toast.LENGTH_SHORT).show();
            }
        }
        else if (resultCode==RESULT_CANCELED)
        {
            Toast.makeText(MainActivity.this,"重新开始了哦~",Toast.LENGTH_SHORT).show();
            LoadNaviFragment();
        }


    }

    private void Load404Fragment(){
        //naviImage.setVisibility(View.GONE);
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        fragment404=new NotFoundFragment();
        ft.replace(R.id.replace_content,fragment404);
        ft.commit();
    }
    private void LoadNaviFragment(){
        //naviImage.setVisibility(View.GONE);
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        fragmentnavi=new NaviFragment();
        ft.replace(R.id.replace_content,fragmentnavi);
        ft.commit();
    }

    private void LoadGLViewFragment(Context context,String uid){
       // naviImage.setVisibility(View.GONE);
        Log.i("Haku+testActi",uid);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        //fragment = new ShowViewFragment(context,uid);
        fragment = new ShowViewFragment();
        fragment.setUid(uid);
        transaction.replace(R.id.replace_content, fragment);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        /*create a database to storage the Uid
        * 只有开始点击就表示db要打开了*/
        /*try{hisDB=hisDBHelper.getWritableDatabase();}
        catch (Exception e){
            Log.i("Haku",e+"");
        }*/

        switch (v.getId()){
            case R.id.scan:
                mIntent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(mIntent, 0);
                break;
            case R.id.history:
                /*查询显示数据的过程，这是一步数据认证*/
                strBuild=new StringBuilder();
                Cursor cursor =hisDBHelper.query();
                if (cursor.moveToFirst()) {
                    do {
                        int pid = cursor.getInt(cursor.getColumnIndex(HistoryDBConfig.dbConfig.COLUMN_NAME_ID));
                        String name = cursor.getString(cursor.getColumnIndex(HistoryDBConfig.dbConfig.COLUMN_NAME_UID));
                        int state =cursor.getInt(cursor.getColumnIndex(HistoryDBConfig.dbConfig.COLUMN_NAME_STATE));
                        strBuild.append("id：" + pid + "：" + name + " "+state+"\n");
                        strBuild.append(name + "\n");
                    } while (cursor.moveToNext());
                }
                cursor.close();
                //Toast.makeText(MainActivity.this, strBuild.toString(), Toast.LENGTH_SHORT).show();

                mIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivityForResult(mIntent, 0);

                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }
}


