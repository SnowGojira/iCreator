package com.example.gululu.icreatorsdksampler.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.adapter.HistoryAdapter;
import com.example.gululu.icreatorsdksampler.database.HistoryDBConfig;
import com.example.gululu.icreatorsdksampler.database.HistroryDBHelper;
import com.example.gululu.icreatorsdksampler.PullRefreshModule.PullToRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @author HakuHak
 * @Function to record the scan history of a customer
 * @date 2015-12-15
 * @version 0.1
 */

public class HistoryActivity extends Activity {
    @Bind(R.id.lv_history)
    ListView mListView;
    @Bind(R.id.title_back)
    ImageButton btn;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView mPullToRefreshView;


    private List mList;
    private HistoryAdapter mAdapter;
    private StringBuilder strBuild;



    private HistroryDBHelper hisDBHelper;
    private SQLiteDatabase hisDB;

    private HashMap<String,Object> queryMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);


       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hisDB.close();
                finish();
            }
        });

        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                        //更新数据的地方
                    }
                },2000);
            }
        });


        initListView();




    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    private void initData() {
        mList=new ArrayList<HashMap<String,Object>>();

        hisDBHelper=new HistroryDBHelper(HistoryActivity.this, HistoryDBConfig.dbConfig.DATABASE_NAME,null,1);
         /*create a database to storage the Uid*/
        hisDB=hisDBHelper.getReadableDatabase();


        strBuild=new StringBuilder();
        Cursor cursor = hisDB.query(HistoryDBConfig.dbConfig.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {/*将数据全部查找出来传入queryMap中以供查找*/
                String uid = cursor.getString(cursor.getColumnIndex(HistoryDBConfig.dbConfig.COLUMN_NAME_UID));

                queryMap = new HashMap<String,Object>();
                for (int i=0;i<cursor.getColumnCount();i++){
                    queryMap.put(HistoryDBConfig.dbConfig.COLUMN_NAME_ID, cursor.getString(0));
                    queryMap.put(HistoryDBConfig.dbConfig.COLUMN_NAME_UID, cursor.getString(1));
                    queryMap.put(HistoryDBConfig.dbConfig.COLUMN_NAME_STATE, cursor.getString(2));
                }

                /*添加数据元*/
                mList.add(queryMap);
            } while (cursor.moveToNext());
        }
        cursor.close();

    }

    private void initListView() {
        try{initData();}
        catch (Exception e){ Log.i("Haku",e+"");}
        mAdapter=new HistoryAdapter(HistoryActivity.this,mList,hisDBHelper);
        mListView.setAdapter(mAdapter);

        hisDB.close();
        Log.i("Haku", "HistoryActivity的db已关闭");

    }


}
