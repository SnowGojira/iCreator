package com.example.gululu.icreatorsdksampler.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.customizeview.MaterialRippleLayout;
import com.example.gululu.icreatorsdksampler.bean.ChannelBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Haku Hal on 2016/1/13.
 */
public class channelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * data的MapList
     */
    private LayoutInflater mLayoutInflater;
    private ChannelBean bean;

    private boolean animationsLocked = false;
    private int lastAnimatedPosition = -1;
    private boolean delayEnterAnimation = true;

    /**
     * 传入的context和map的列表
     */
    private Context mContext;
    private List<ChannelBean> mList;

    public channelAdapter(List list, Context context) {
        mList = list;
        mContext = context;
        mLayoutInflater= LayoutInflater.from(context);
    }

   //导入collect item的功能
    @Override
    public CollectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(mContext).inflate(R.layout.item_channel_info,parent,false);
        return new CollectHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        //载入动画
        runEnterAnimation(viewHolder.itemView, position);
        CollectHolder holder= (CollectHolder) viewHolder;
        /*数据的传入，之后肯定要做从网络数据得到的过程*/
        bean=mList.get(position);

        holder.txtChannelTitle.setText(bean.getTitle());
        holder.icon.setImageResource(bean.getResID());
        /*点击跳转*/
        holder.mMaterialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加点击跳转的功能

            }
        });


    }



    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class CollectHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.txt_channel_title)
        TextView txtChannelTitle;
        @Bind(R.id.material_Channel)
        MaterialRippleLayout mMaterialRippleLayout;
        @Bind(R.id.img_icon)
        ImageView icon;

        public CollectHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

    }

    /**
     *设置进入的动画
     * @param view
     * @param position
     */
    private void runEnterAnimation(View view, int position) {
        if (animationsLocked) return;

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(100);
            view.setAlpha(0.f);
            view.animate()
                    .translationY(0).alpha(1.f)
                    .setStartDelay(delayEnterAnimation ? 20 * (position) : 0)
                    .setInterpolator(new DecelerateInterpolator(2.f))
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animationsLocked = true;
                        }
                    })
                    .start();
        }
    }

    public void setAnimationsLocked(boolean animationsLocked) {
        this.animationsLocked = animationsLocked;
    }

    public void setDelayEnterAnimation(boolean delayEnterAnimation) {
        this.delayEnterAnimation = delayEnterAnimation;
    }


}
