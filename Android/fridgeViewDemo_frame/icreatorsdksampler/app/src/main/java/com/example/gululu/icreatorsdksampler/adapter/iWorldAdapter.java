package com.example.gululu.icreatorsdksampler.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

import butterknife.Bind;
import butterknife.ButterKnife;

/*import butterknife.ButterKnife;
import butterknife.InjectView;*/

/**
 * Created by HakuHak on 2015/12/15.
 * view my space adapter
 */
public class iWorldAdapter extends BaseAdapter{
    private HashMap<String,Object> mMap;
    private Context mContext;
    private List<HashMap<String,Object>> mList;
    private LayoutInflater mLayoutInflater;
    private HistroryDBHelper mHelper;

    //private String uid;
    //private String dbId;
    private int listPosition;


    public iWorldAdapter(Context context, List<HashMap<String, Object>> list, HistroryDBHelper helper) {
        mContext=context;
        mList=list;
        mHelper=helper;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        mLayoutInflater=LayoutInflater.from(mContext);
        if (convertView==null){

            //convertView=mLayoutInflater.inflate(R.layout.item_history,null);
            convertView=mLayoutInflater.inflate(R.layout.item_collection_info,null);
            holder=new Holder(convertView);

            convertView.setTag(holder);
        }else{
            holder= (Holder) convertView.getTag();
        }

        listPosition=position;
        mMap=mList.get(position);
        String uid=mMap.get(HistoryDBConfig.dbConfig.COLUMN_NAME_UID).toString();
        String dbId= mMap.get(HistoryDBConfig.dbConfig.COLUMN_NAME_ID).toString();

        Log.i("Haku", uid + "/" + dbId);
        /*做一步转码*/
        holder.txtCollectName.setText(ConvertStringUtils.convertToName(uid));
        holder.txtCollectSub.setText(ConvertStringUtils.translateName(uid));

        /**
         * 点击删除
         */


        holder.mMaterialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap = mList.get(position);
                String uid = mMap.get(HistoryDBConfig.dbConfig.COLUMN_NAME_UID).toString();
                Log.i("Haku", getClass().getName() + " " + position + " " + uid);
                Intent intent = new Intent(mContext, ShowActivity.class);
                intent.putExtra("UID", uid);
                mContext.startActivity(intent);
            }
        });
        /**
         * 点击跳转
         */
        holder.mMaterialRippleLayout.setOnLongClickListener(new View.OnLongClickListener() {
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
        });

        return convertView;
    }

    class Holder{
        /*@Bind(R.id.ll_history)
        LinearLayout mLinearLayout;
        @Bind(R.id.tv_history)
        TextView tvHistory;
        @Bind(R.id.tv_history_eng)
        TextView tvHistoryEng;*/
        @Bind(R.id.rl_collection)
        RelativeLayout relateCollection;
        @Bind(R.id.txt_collect_name)
        TextView txtCollectName;
        @Bind(R.id.txt_collect_sub)
        TextView txtCollectSub;
        @Bind(R.id.material_collection)
        MaterialRippleLayout mMaterialRippleLayout;

        public Holder(View view){
            ButterKnife.bind(this, view);
        }
    }

}
