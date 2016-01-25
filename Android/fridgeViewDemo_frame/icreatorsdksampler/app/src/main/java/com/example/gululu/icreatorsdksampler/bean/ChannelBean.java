package com.example.gululu.icreatorsdksampler.bean;

import com.example.gululu.icreatorsdksampler.R;

import java.util.Random;

/**
 * Created by Haku Hal on 2016/1/15.
 */
public class ChannelBean {
    private String title;
    private int resID;

    public ChannelBean(String title) {
        this.title = title;
    }

    public int getResID() {
        return loadRandomResource();
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

  private int loadRandomResource(){
        int mResource = 0;
        Random mRand = new Random();
        int x = mRand.nextInt(5);
        switch (x) {
            case 0:
                mResource = R.drawable.ic_channel1;
                break;
            case 1:
                mResource = R.drawable.ic_channel2;
                break;
            case 2:
                mResource = R.drawable.ic_channel3;
                break;
            case 3:
                mResource = R.drawable.ic_channel4;
                break;
            case 4:
                mResource = R.drawable.ic_channel5;
                break;
            case 5:
                mResource = R.drawable.ic_channel6;
                break;
        }

        return mResource;
    }

}
