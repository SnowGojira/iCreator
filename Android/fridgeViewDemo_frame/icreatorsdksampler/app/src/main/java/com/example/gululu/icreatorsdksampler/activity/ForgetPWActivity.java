package com.example.gululu.icreatorsdksampler.activity;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.utils.CountDownHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForgetPWActivity extends Activity implements View.OnClickListener{
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.title_back)
    ImageButton btnBack;
    @Bind(R.id.btn_regist_sendcode)
    Button btnRegistSendCode;

    CountDownHelper mCountDownHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpw);

        ButterKnife.bind(this);

        initLayout();

    }

    private void initLayout() {
        tvTitle.setText(getResources().getText(R.string.login_forget));
        btnBack.setOnClickListener(this);
        btnRegistSendCode.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_regist_sendcode:
                mCountDownHelper= new CountDownHelper(btnRegistSendCode,getResources().getString(R.string.verify_code_send),60,1);
                mCountDownHelper.setOnFinishListener(new CountDownHelper.OnFinishListener() {
                    @Override
                    public void FinishThisFunction() {
                        Toast.makeText(getApplicationContext(), "倒计时结束",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                mCountDownHelper.OnStart();
                break;
        }

    }
}
