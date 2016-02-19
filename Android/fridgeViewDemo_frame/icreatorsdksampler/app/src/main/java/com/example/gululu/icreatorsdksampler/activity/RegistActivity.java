package com.example.gululu.icreatorsdksampler.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gululu.icreatorsdksampler.R;
import com.example.gululu.icreatorsdksampler.net.NetSingleton;
import com.example.gululu.icreatorsdksampler.utils.AppUtils;
import com.example.gululu.icreatorsdksampler.utils.CountDownHelper;
import com.example.gululu.icreatorsdksampler.net.HttpUrlUtil;
import com.example.gululu.icreatorsdksampler.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistActivity extends Activity implements View.OnClickListener{
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.title_back)
    ImageButton btnBack;

    @Bind(R.id.btn_regist_sendcode)
    Button btnRegistSendCode;
    @Bind(R.id.edt_regist_mobile)
    EditText edtRegistMobile;
    @Bind(R.id.edt_regist_pw)
    EditText edtRegistPw;
    @Bind(R.id.edt_regist_verifycode)
    EditText edtRegistVerifycode;
    @Bind(R.id.btn_regist_submit)
    Button btnSubmit;

    private CountDownHelper mCountDownHelper;

    private String mobile;

    private final static String TAG="RegistAcitivity";
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        ButterKnife.bind(this);

        initLayout();

       
    }

    private void initLayout() {
        tvTitle.setText(getResources().getText(R.string.login_regist));
        btnBack.setOnClickListener(this);
        btnRegistSendCode.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        edtRegistVerifycode.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                finish();
                break;
            /**
             * 获取验证码
             */
            case R.id.btn_regist_sendcode:
                mobile= edtRegistMobile.getText().toString();
                Log.i("aaa", mobile);
                if (StringUtils.isCorrectMobileFormat(mobile,RegistActivity.this)){
                    setCountDownHelper();
                    String URL=HttpUrlUtil.GetAuthCodeUrl(mobile,"0");
                    //Log.i("aaa", URL);
                    mRequestQueue= NetSingleton.getInstance(RegistActivity.this).getRequestQueue();
                    JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, URL,null,
                            new Response.Listener<JSONObject>(){
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    //{"error_no":"0","error_desc":"成功"}
                                    try {
                                        String jsonStateNo= jsonObject.getString("error_no");
                                        String jsonState= jsonObject.getString("error_desc");
                                        Log.i("aaa",jsonState+" "+jsonStateNo);

                                            AppUtils.showToast("验证码发送"+jsonState,RegistActivity.this);
                                    } catch (JSONException e) {
                                        AppUtils.showToast("网络连接失败"+e,RegistActivity.this);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                }
                            }){ //更换了报头，让所有类型的格式都能条通过
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String,String> headers = new HashMap<String,String>();
                            //接收所有类型
                            headers.put("Accept","*/*");
                            headers.put("Content-Type","application/json");
                            return headers;
                        }
                    };
                    mRequestQueue.add(jsonObjectRequest);
                }

                break;

            case R.id.edt_regist_pw:
                if (mCountDownHelper!=null){
                    mCountDownHelper.OnCancel();
                    Log.i("aaa","CountDown is canceled");
                }

            case R.id.btn_regist_submit:
                String URL=HttpUrlUtil.GetRegistUrl("15108333006","123456","123456");
                //Log.i("aaa", URL);
                //String MD=MD5.getMD5ofStr("e10adc3949ba59abbe56e057f20f883e");
                //Log.i("aaa", MD);

                break;
        }

    }

    /**
     * 触发倒计时功能
     */
    private void setCountDownHelper(){

        mCountDownHelper= new CountDownHelper(btnRegistSendCode,getResources().getString(R.string.verify_code_send),60,1);
        mCountDownHelper.setOnFinishListener(new CountDownHelper.OnFinishListener() {
            @Override
            public void FinishThisFunction() {
                Toast.makeText(getApplicationContext(), "倒计时结束",
                        Toast.LENGTH_SHORT).show();
            }
        });
        mCountDownHelper.OnStart();
    }

    private void setVolleyGET(String url){
        /**
         * 实现json字符传的监听
         */
        final RequestQueue mRequestQueue=Volley.newRequestQueue(RegistActivity.this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.i("aaa", jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("aaa", "网络有问题：" + volleyError.toString());
            }
        }){ //更换了报头，让所有类型的格式都能条通过
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<String,String>();
                //接收所有类型
                headers.put("Accept","*/*");
                headers.put("Content-Type","application/json");
                return headers;
            }
        };
        jsonObjectRequest.setTag(TAG);
        mRequestQueue.add(jsonObjectRequest);

    }

    @Override
    protected void onStop() {
        super.onStop();
        /*
         * 取消所有队列
         */

        if (mRequestQueue!=null){
            mRequestQueue.cancelAll(TAG);
            Log.i("aaa","request queue is canceled");
        }
    }

}


