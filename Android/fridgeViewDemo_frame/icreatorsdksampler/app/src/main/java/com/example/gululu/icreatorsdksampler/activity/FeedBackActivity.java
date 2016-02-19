package com.example.gululu.icreatorsdksampler.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gululu.icreatorsdksampler.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedBackActivity extends Activity implements View.OnClickListener,TextWatcher{
    @Bind(R.id.title_back)
    ImageButton titleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.edt_feedback_content)
    EditText edtFeedBack;
    @Bind(R.id.tv_feedback)
    TextView tvFeedback;
    @Bind(R.id.btn_feedback)
    Button btnFeedBack;

    /**
     * 改变TextView 的handler
     */

    private Message message;
    private Handler ChangeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            tvFeedback.setText(msg.arg1 + "");

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);

        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

        initLayout();

    }

    private void initLayout() {
        btnFeedBack.setOnClickListener(this);
        titleBack.setOnClickListener(this);
        tvTitle.setText("意见反馈");
        edtFeedBack.addTextChangedListener(this);

        message=new Message();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_feedback:
                Toast.makeText(this, "感谢您的宝贵意见！", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * 这个方法是在输入时会被调用的方法，那么在输入的时候获取输入的字符的个数就可以算出
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int last ;
        last = 200- edtFeedBack.getText().toString().length();

        tvFeedback.setText(last+"");
        if (last==0){

        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
