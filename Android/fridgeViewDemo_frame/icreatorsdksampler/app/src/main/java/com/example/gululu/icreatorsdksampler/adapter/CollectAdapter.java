package com.example.gululu.icreatorsdksampler.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.activity.ShowActivity;
import com.example.gululu.icreatorsdksampler.customizeview.MaterialRippleLayout;
import com.example.gululu.icreatorsdksampler.database.HistoryDBConfig;
import com.example.gululu.icreatorsdksampler.database.HistroryDBHelper;
import com.example.gululu.icreatorsdksampler.utils.ConvertStringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Haku Hal on 2016/1/13.
 */
public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.CollectHolder> {
    /**
     * data的MapList
     */
    private HashMap<String,Object> mMap;
    private LayoutInflater mLayoutInflater;
    private HistroryDBHelper mHelper;

    /**
     * 传入的context和map的列表
     */
    private Context mContext;
    private List<HashMap<String,Object>> mList;

    private boolean animationsLocked = false;
    private int lastAnimatedPosition = -1;
    private boolean delayEnterAnimation = true;

    public CollectAdapter(List<HashMap<String, Object>> list, Context context,HistroryDBHelper helper) {
        mList = list;
        mContext = context;
        mHelper=helper;
        mLayoutInflater= LayoutInflater.from(context);
    }

   //导入collect item的功能
    @Override
    public CollectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectHolder(mLayoutInflater.inflate(R.layout.item_collection_info,null));
    }

    @Override
    public void onBindViewHolder(CollectHolder holder, final int position) {
        /*数据的传入，之后肯定要做从网络数据得到的过程*/
        mMap=mList.get(position);
        String uid=mMap.get(HistoryDBConfig.dbConfig.COLUMN_NAME_UID).toString();
        String dbId= mMap.get(HistoryDBConfig.dbConfig.COLUMN_NAME_ID).toString();
        Log.i("Haku", uid + "/" + dbId);

        holder.txtCollectName.setText(ConvertStringUtils.convertToName(uid));
        holder.txtCollectSub.setText(ConvertStringUtils.translateName(uid));
        /*点击跳转*/
        holder.mMaterialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加点击跳转的功能
                mMap = mList.get(position);
                String uid = mMap.get(HistoryDBConfig.dbConfig.COLUMN_NAME_UID).toString();
                Log.i("Haku", getClass().getName() + " " + position + " " + uid);
                Intent intent = new Intent(mContext, ShowActivity.class);
                intent.putExtra("UID", uid);
                mContext.startActivity(intent);
            }
        });
        /**
         * 重改为滑动删除
         */
        /*长按删除*/
       /* holder.mMaterialRippleLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mMap = mList.get(position);
                int dbId = Integer.valueOf(mMap.get(HistoryDBConfig.dbConfig.COLUMN_NAME_ID).toString());
                Log.i("Haku", getClass().getName() + " " + position + " " + dbId);
                if (mHelper.delete(dbId)) {
                    new AlertDialog.Builder(mContext).setTitle("我的提示").setMessage("确定要删除吗？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mList.remove(position);
                                    // 通过程序我们知道删除了，但是怎么刷新ListView呢？
                                    // 只需要重新设置一下adapter
                                    notifyDataSetChanged();
                                }
                            }).show();
                }
                return false;
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class CollectHolder extends RecyclerView.ViewHolder{
       /* @Bind(R.id.rl_collection)
        RelativeLayout relateCollection;*/
        @Bind(R.id.txt_collect_name)
        TextView txtCollectName;
        @Bind(R.id.txt_collect_sub)
        TextView txtCollectSub;
        @Bind(R.id.material_collection)
        MaterialRippleLayout mMaterialRippleLayout;

        public CollectHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

    }
}
