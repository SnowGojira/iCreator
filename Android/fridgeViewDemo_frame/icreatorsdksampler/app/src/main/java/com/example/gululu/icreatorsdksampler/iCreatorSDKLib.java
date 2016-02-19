package com.example.gululu.icreatorsdksampler;

/**
 * Created by gululu on 2015/10/25.
 */
public class iCreatorSDKLib {
    /**
     * load the C++ libiCreatorSDK.so
     * to get the method
     */
     {
        System.loadLibrary("iCreatorSDK");
    }

    public native void setLogPath(String logPath);
    /**
     * @param width the current view width
     * @param height the current view height
     */
    /**
     * 作为自定义控件这个可能需要抽离一下了
     * @param width
     * @param height
     */
    public native void init(int ltx, int lty, int width, int height);
    public native void setServerInfo(String strCAccout, String strCPwd, String strCUUID);
    public native void update();
    public native void sendEvent(int nCID, int nCEvt, int nCX, int nCY);
    public native void clearEvent();
    public native void resetCameraAndAllMesh();
    public native void clearAllResources();


}
