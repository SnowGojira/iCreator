package com.example.gululu.icreatorsdksampler.bean;

/**
 * Created by Haku Hal on 2016/1/15.
 */
public class UuidBean {
    //private boolean misUuid;
    private String mNameCN;
    private String mNameENG;

    public UuidBean(String nameCN, String nameENG) {
       // this.misUuid = misUuid;
        mNameCN = nameCN;
        mNameENG = nameENG;
    }

    public boolean isUuid() {
        return true;
    }

   /* public void setisUuid(boolean misUuid) {
        this.misUuid = misUuid;
    }*/

    public String getNameCN() {
        return mNameCN;
    }

    public void setNameCN(String nameCN) {
        mNameCN = nameCN;
    }

    public String getNameENG() {
        return mNameENG;
    }

    public void setNameENG(String nameENG) {
        mNameENG = nameENG;
    }
}
