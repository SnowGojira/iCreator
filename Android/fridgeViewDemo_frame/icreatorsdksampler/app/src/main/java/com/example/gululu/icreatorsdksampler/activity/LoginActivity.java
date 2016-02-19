package com.example.gululu.icreatorsdksampler.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gululu.icreatorsdksampler.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LoginActivity extends Activity implements View.OnClickListener{
    @Bind(R.id.btn_register)
    Button btnRegist;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.edt_login_phone)
    EditText edtLoginPhone;
    @Bind(R.id.edt_login_password)
    EditText edtLoginPassword;
    @Bind(R.id.tv_login_forget)
    TextView tvLoginForget;
    @Bind(R.id.tv_login_glance)
    TextView tvLoginGlance;

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inject the ButtonKnife
       ButterKnife.bind(this);
        initListener();


    }

    private void initListener(){
        btnLogin.setOnClickListener(this);
        btnRegist.setOnClickListener(this);
        tvLoginForget.setOnClickListener(this);
        tvLoginGlance.setOnClickListener(this);
        /*收拾一下密码edt将其输入屏蔽 */
        edtLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    /**
     * 各种view的跳转
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_login:
                mIntent = new Intent(LoginActivity.this, ChannelActivity.class);
                //mIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_register:
                mIntent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_login_glance:
                mIntent = new Intent(LoginActivity.this, MineActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_login_forget:
                mIntent = new Intent(LoginActivity.this, ForgetPWActivity.class);
                startActivity(mIntent);
                break;


        }
    }
}
