package com.example.gululu.icreatorsdksampler.utils;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;


/**
 * Created by 1 on 2015/9/18.
 */
public class CountDownHelper {

    /*
    倒计时用的Timer
    和成员变量button用来承载传进的变量值
     */
    private CountDownTimer mTimer;
    private Button mButton;
    private String mString;
    /*
    倒计时结束后的回调接口
     */
    private OnFinishListener mListener;

    /*
    自定义构造函数
    displayString是buttontext中的显示的文字信息
    durationTime是倒计时的时长
    intervalTime是倒计时的间隔
     */


    public CountDownHelper(Button button, final String displayString, int durationTime, int intervalTime) {
        mButton = button;
        mString=displayString;
        /*
        关于算法的若干说明
        CountDownTimer并不是准确计时，在onTick方法调用的时候，time会有1-10ms左右的误差，这会导致最后一秒不会调用onTick()
        因此，设置间隔的时候，默认减去了10ms，从而减去误差。
        经过以上的微调，最后一秒的显示时间会由于10ms延迟的积累，导致显示时间比1s长max*10ms的时间，其他时间的显示正常,总时间正常
         */
        mTimer = new CountDownTimer(durationTime * 1000, intervalTime * 1000 - 10) {
            public void onTick(long millisUntilFinished) {
                // 第一次调用会有1-10ms的误差，因此需要+15ms，防止第一个数不显示，第二个数显示2s

                mButton.setText(mString + "(" + ((millisUntilFinished + 15) / 1000) + "秒)");
                Log.d("CountDownHelper", "time = " + (millisUntilFinished) + " text = "
                        + ((millisUntilFinished + 15) / 1000));
            }

            /*
            结束后
            btn的text恢复到原有的正常显示,可以点击
             */
            @Override
            public void onFinish() {
                mButton.setEnabled(true);
                mButton.setText(mString);
                if (mListener!=null){
                    mListener.FinishThisFunction();
                }

            }
        };


    }

    public void OnStart(){
        mButton.setEnabled(false);
        mTimer.start();

    }
     public void OnCancel(){
         mTimer.cancel();
         mButton.setEnabled(true);
        mButton.setText(mString);

     }

    public void setOnFinishListener(OnFinishListener listener){
        mListener=listener;
    }


    public interface OnFinishListener {
        public void FinishThisFunction();
    }

}

