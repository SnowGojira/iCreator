package com.example.gululu.icreatorsdksampler.fragment;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gululu.icreatorsdksampler.PullRefreshModule.PullToRefreshView;
import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.adapter.CollectAdapter;
import com.example.gululu.icreatorsdksampler.database.HistoryDBConfig;
import com.example.gululu.icreatorsdksampler.database.HistroryDBHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class collectFragment extends Fragment {
    @Bind(R.id.rl_collect)
    RecyclerView rlCollect;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefreshView;

    private List mList;
    private CollectAdapter mAdapter;

    private HistroryDBHelper hisDBHelper;
    private SQLiteDatabase hisDB;
    private HashMap<String,Object> queryMap;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_collect,container,false);
        ButterKnife.bind(this,v);
        loadCollection();
        return v;
    }

    private void initData() {
        mList=new ArrayList<HashMap<String,Object>>();

        hisDBHelper=new HistroryDBHelper(getActivity(), HistoryDBConfig.dbConfig.DATABASE_NAME,null,1);
         /*create a database to storage the Uid*/
        hisDB=hisDBHelper.getReadableDatabase();

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

    private void loadCollection(){
        try{initData();}
        catch (Exception e){ Log.i("collectFragment", e + "");}
        mAdapter=new CollectAdapter(mList,getActivity(),hisDBHelper);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rlCollect.setLayoutManager(layoutManager);
        rlCollect.setAdapter(mAdapter);

        pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshView.setRefreshing(false);
                        //更新数据的地方
                        initData();
                        mAdapter.notifyDataSetChanged();
                        mAdapter = new CollectAdapter(mList, getActivity(), hisDBHelper);
                        rlCollect.setAdapter(mAdapter);
                    }
                },1500);
            }
        });

        ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT) {
            /**
             * @param recyclerView
             * @param viewHolder 拖动的ViewHolder
             * @param target 目标位置的ViewHolder
             * @return
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
                int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
                if (fromPosition < toPosition) {
                    //分别把中间所有的item的位置重新交换
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(mList, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(mList, i, i - 1);
                    }
                }
                mAdapter.notifyItemMoved(fromPosition, toPosition);
                //返回true表示执行拖动
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mList.remove(position);
                mAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    //左右滑动时改变Item的透明度
                    final float alpha = 1 - Math.abs(dX) / (float)viewHolder.itemView.getWidth();
                    viewHolder.itemView.setAlpha(alpha);
                    viewHolder.itemView.setTranslationX(dX);
                }
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                //当选中Item时候会调用该方法，重写此方法可以实现选中时候的一些动画逻辑
                Log.v("zxy", "onSelectedChanged");
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                //当动画已经结束的时候调用该方法，重写此方法可以实现恢复Item的初始状态
                Log.v("zxy", "clearView");
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(rlCollect);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hisDB.close();
    }
}


