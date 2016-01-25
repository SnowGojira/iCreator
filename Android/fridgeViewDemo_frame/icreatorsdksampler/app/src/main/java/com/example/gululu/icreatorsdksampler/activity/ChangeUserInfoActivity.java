package com.example.gululu.icreatorsdksampler.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.customizeview.RoundedImageViewEx;
import com.example.gululu.icreatorsdksampler.utils.AppUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChangeUserInfoActivity extends Activity implements Handler.Callback{
    @Bind(R.id.iv_user_photo)
    RoundedImageViewEx ivUserPhoto;
    @Bind(R.id.edt_user_name)
    EditText edtUserName;
    @Bind(R.id.ib_finish)
    ImageButton ibBack;
    @Bind(R.id.btn_userinfo_confirm)
    Button btnConfirm;

    private ImageView ivImage;
    String[] arrayString = { "拍照", "相册" };
    String title = "上传照片";
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;//拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;//从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;//结果

    //创建一个以当前时间为名称的文件
    File tempFile = new File(Environment.getExternalStorageDirectory(),
            getPhotoFileName());
    final Handler upLoadhand = new Handler(this);
    private Bundle bundle=null;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        ButterKnife.bind(this);
        initLayout();

    }

   /* private Bundle submitResult() {
        bundle= new Bundle();
        ivImage = (ImageView) findViewById(R.id.iv_user_photo);
        BitmapDrawable mDrawable =  (BitmapDrawable) ivImage.getDrawable();
        Bitmap photo = mDrawable.getBitmap();
        String name=edtUserName.getText().toString();
        if (!name.equals("")){
            Log.i("Haku", name);
                    bundle.putParcelable("Photo", photo);
                    bundle.putString("Name", name);
            return bundle;
        }
        return  null;
    }*/

    private void initLayout() {
        intent=getIntent();
        if (intent!=null){
            Bundle bundle=intent.getExtras();
            String name= bundle.getString("Name");
            byte buff[]=intent.getByteArrayExtra("Photo");
            Bitmap photo = BitmapFactory.decodeByteArray(buff, 0, buff.length);
            //Bitmap photo=bundle.getParcelable("Photo");
            Log.i("ChangeUserInfo",name+" "+photo);
            ivUserPhoto.setImageBitmap(photo);
            edtUserName.setHint(name);
        }
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /**
         * 变更头像
         */
        ivUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivImage = (ImageView) findViewById(v.getId());
                AlertDialog.Builder dialog = getListDialogBuilder(
                        ChangeUserInfoActivity.this, arrayString, title,
                        onDialogClick);
                dialog.show();
            }
        });

        /**
         * 提交结果
         */
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name= edtUserName.getText().toString();
                    byte changedPhoto[]= AppUtils.drawable2Bytes(ivUserPhoto.getDrawable());

                    bundle= new Bundle();
                    bundle.putByteArray("ChangedPhoto", changedPhoto);
                    bundle.putString("ChangedName", name);
                    Log.i("ChangeUserInfo",name+" "+changedPhoto);
                    Intent intent=getIntent();
                    intent.putExtra("ChangedUserInfo", bundle);
                    setResult(RESULT_OK,intent);

                    finish();
                }
            });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    //返回一个list对话框
    private  AlertDialog.Builder getListDialogBuilder(Context c,String[] s,String t,
                                                            DialogInterface.OnClickListener o)
    {
        final String[] items =s;
        return  new AlertDialog.Builder(c)
                .setTitle(t)
//         .setView(v)
                .setItems(items,o);

    }


    //弹出对话框的监听
    DialogInterface.OnClickListener onDialogClick=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case 0:
                    //开启照相
                    startCameraPicCut(dialog);
                    break;
                case 1:
                    //开启图库
                    startImageCapture(dialog);
                    break;
                default:
                    break;
            }
        }

        private void startCameraPicCut(DialogInterface dialog) {
            // TODO Auto-generated method stub
            dialog.dismiss();
            //调用系统的拍照功能
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            intent.putExtra("camerasensortype", 2);//调用前置摄像头
            intent.putExtra("autofocus", true);//自动对焦
            intent.putExtra("fullScreen", false);//全屏
            intent.putExtra("showActionIcons", false);

            //制定调用相机拍照功能后的存储路径
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
            startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);

        }

        private void startImageCapture(DialogInterface dialog) {
            // TODO Auto-generated method stub
            dialog.dismiss();
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    "image/*");
            startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
        }
    };

    //回传信息
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO:
                startPhotoZoom(Uri.fromFile(tempFile), 150);
                break;

            case PHOTO_REQUEST_GALLERY:
                if (data != null) {
                    startPhotoZoom(data.getData(), 150);
                }
                break;

            case PHOTO_REQUEST_CUT:
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startPhotoZoom(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启intent中设置显示的View可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY为宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY为剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    //将剪裁后的图片显示在UI界面上
    private void setPicToView(Intent picdata){
        Bundle bundle=picdata.getExtras();
        if (bundle!=null){
            final Bitmap photo=bundle.getParcelable("data");
            Drawable drawable=new BitmapDrawable(photo);
            //iv_image.setBackgroundDrawable(drawable);
            ivImage.setImageDrawable(drawable);
        }
    }

    //使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.obj != null) {
            Drawable drawable = new BitmapDrawable((Bitmap) msg.obj);
            ivImage.setBackgroundDrawable(drawable);
            Toast.makeText(this, "获得图片并且头像上传成功", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this, "获得图片，但是头像上传失败，请注意配置uploaderUrl上传地址", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}
