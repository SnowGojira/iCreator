package com.example.gululu.icreatorsdksampler.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gululu.icreatorsdksampler.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForgetPWActivity extends Activity implements View.OnClickListener{
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.title_back)
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        ButterKnife.bind(this);

        initLayout();

    }

    private void initLayout() {
        tvTitle.setText(getResources().getText(R.string.login_forget));
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                finish();
                break;
        }

    }
}
