package com.example.gululu.icreatorsdksampler.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gululu.icreatorsdksampler.QrModule.encoding.EncodingHandler;
import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.customizeview.like.LikeButton;
import com.example.gululu.icreatorsdksampler.customizeview.like.OnLikeListener;
import com.example.gululu.icreatorsdksampler.database.HistoryDBConfig;
import com.example.gululu.icreatorsdksampler.database.HistroryDBHelper;
import com.example.gululu.icreatorsdksampler.fragment.NotFoundFragment;
import com.example.gululu.icreatorsdksampler.fragment.ShowViewFragment;
import com.example.gululu.icreatorsdksampler.utils.ConvertStringUtils;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.encoder.QRCode;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QRresultActivity extends FragmentActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private ShowViewFragment fragment;
    private NotFoundFragment fragment404;

    @Bind(R.id.title_back)
    FrameLayout btn_back;
    @Bind(R.id.iv_encode)
    ImageView ivEncode;
    @Bind(R.id.tv_history)
    TextView tvHistory;
    @Bind(R.id.tv_history_eng)
    TextView tvHistoryEng;
    @Bind(R.id.ll_collection)
    LinearLayout llCollection;
    @Bind(R.id.like_button)
    LikeButton likeButton;
    @Bind(R.id.ll_QR_title)
    LinearLayout llQRtitle;

    /*设置数据库*/
    private HistroryDBHelper hisDBHelper;
    private final int VERSION=1;
    private StringBuilder strBuild;


    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        /*切进来的动效*/
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        ButterKnife.bind(this);

        initBraBra();
        getQRresult();


    }

    private void initBraBra(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*create a history DB */
        hisDBHelper=new HistroryDBHelper(QRresultActivity.this, HistoryDBConfig.dbConfig.DATABASE_NAME,null,VERSION);
    }

    /*得到QR的结果*/
    private void getQRresult(){
        mIntent=getIntent();
        Bundle bundle=mIntent.getExtras();

        if (bundle!=null){
            final String strUID=bundle.getString("result");
            Log.i("Haku",getLocalClassName()+" "+strUID);

            if (!strUID.equals("")) {
                try {
                    Bitmap bitQR = EncodingHandler.createQRCode(strUID, 400);
                    ivEncode.setImageBitmap(bitQR);
                    tvHistory.setText(ConvertStringUtils.convertToName(strUID));
                    tvHistoryEng.setText(ConvertStringUtils.translateName(strUID));
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                /**
                 *只要是scanText非空，就给我去执行写入DB的过程
                 */

                /**
                 * 写入之后再去判断是该load哪种fragment
                 */
                if (ConvertStringUtils.isUuid(strUID)){

                    LoadGLViewFragment(QRresultActivity.this,strUID);
                    likeButton.setOnLikeListener(new OnLikeListener() {
                        @Override
                        public void liked(LikeButton likeButton) {
                            /*增加数据的过程*/
                            ContentValues cv = hisDBHelper.getContentValue(strUID.toString());
                            hisDBHelper.insert(cv);
                            Toast.makeText(QRresultActivity.this, "已经收藏啦", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        @Override
                        public void unLiked(LikeButton likeButton) {
                            /**/

                        }
                    });


                }else{
                    Load404Fragment();
                }
            }

        }

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
         * 不显示收藏区域和标题
         * update 2016-1-11
         */
        llQRtitle.setVisibility(View.GONE);
        llCollection.setVisibility(View.GONE);
    }

    @Override
    public void finish() {
        super.finish();
        hisDBHelper.close();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

}
