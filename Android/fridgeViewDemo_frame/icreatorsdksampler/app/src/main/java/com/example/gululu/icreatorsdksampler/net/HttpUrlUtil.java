package com.example.gululu.icreatorsdksampler.net;

import android.util.Log;

/**
 *
 * 工具类
 * 提供各个模块所需要的Url
 * Created by Haku Hal on 2015/10/8.
 */
public class HttpUrlUtil {


    private final static String PORT="http://182.254.159.214:8080/jq";
    //private final static String PORT="http://10.10.10.128:8080/jq";
    private final static String FROM_TAG="&from=ANDROID";
    /**
     * 变化的字段
     */
    private static final String SEND_AUTHCODE="/user/sendAuthCode?";
    private static final String REGIST="/user/register?";
    private static final String LOGIN="/user/login?";
    private static final String REST_PW="/user/resetPassword?";
    private static final String UP_LOAD="/fileupload/upload?";
    private static final String SHOW_HEAD="/fileupload/showhead?";

    private static final String COLLECT_MODEL="/favorite/favoriteModelFiles?";
    private static final String CANCEL_MODEL="/favorite/cancelFavoriteModelFiles?";
    private static final String GAIN_MODEL="/favorite/getFavoriteModels?";
    private static final String API_VERSION="/api/checkCurrentVersion?";

    private static final String CHANNEL_HUB="/modelChannel/get?";
    private static final String MODEL_HUB="/modelFile/getUserModeFilelList?";

    private static final String KEY="Http json String";


    /**
     * 发送验证码
     * 例子：http://182.254.159.214:8080/jq/user/sendAuthCode?mobile=18565741596&type=0
     */

    public static String GetAuthCodeUrl(String mobile,String type){
        String URL=PORT+SEND_AUTHCODE+"mobile="+mobile+"&type="+type+FROM_TAG;
        Log.i(KEY,URL);
        return URL;
    }


    /**
     * 注册模块
     * 例子：http://182.254.159.214:8080/jq/user/register?
     * userName=18565741596&password=123456&mobile=18565741596&authCode=123456
     * @return
     */
    public static String GetRegistUrl(String mobile,String pw,String authCode){
        String userName=mobile;
        String URL=PORT+REGIST+"userName="+userName+"&password="+pw+"&mobile="+mobile+"&authCode="+authCode+FROM_TAG;
        Log.i(KEY,URL);
        return URL;
    }

    /**
     * 登陆模块
     * http://182.254.159.214:8080/jq/user/login?
     * userName=18565741596&password=123456&from=ANDROID
     * @return
     */
    public static String GetLoginUrl(String mobile,String pw){
        String URL=PORT+LOGIN+"userName="+mobile+"&password="+pw+FROM_TAG;
        Log.i(KEY,URL);
        return URL;
    }

    /**
     * 重置密码模块
     * @param json
     * @return
     */
    public static String GetRestPwUrl(String json){
        String URL=PORT+REST_PW+json+FROM_TAG;
        Log.i(KEY,URL);
        return URL;
    }

    /**
     * 上传头像
     * @param json
     * @return
     */
    public static String getUpLoadUrl(String json){
        String URL=PORT+UP_LOAD+json+FROM_TAG;
        Log.i(KEY,URL);
        return URL;
    }

    /**
     * 显示头像
     * @param json
     * @return
     */
    public static String getShowHeadUrl(String json){
        String URL=PORT+SHOW_HEAD+json+FROM_TAG;
        Log.i(KEY,URL);
        return URL;
    }

    /**
     * 得到收藏记录
     * @param json
     * @return
     */
    public static String getCollectModelUrl(String json){
        String URL=PORT+COLLECT_MODEL+json+FROM_TAG;
        Log.i(KEY,URL);
        return URL;
    }

    /**
     * 得到模型
     * @param json
     * @return
     */
    public static String getGainModelUrl(String json){
        String URL=PORT+GAIN_MODEL+json+FROM_TAG;
        Log.i(KEY,URL);
        return URL;
    }

    /**
     * 得到版本号
     * @param json
     * @return
     */
    public static String getApiVersionUrl(String json){
        String URL=PORT+API_VERSION+json+FROM_TAG;
        Log.i(KEY,URL);
        return URL;
    }

    /**
     * 取消模型接口
     * @param json
     * @return
     */
    public static String getCancelModelUrl(String json){
        String URL=PORT+CANCEL_MODEL+json+FROM_TAG;
        Log.i(KEY,URL);
        return URL;
    }

    /**
     * 得到频道接口
     * @param json
     * @return
     */
    public static String getChannelHubUrl(String json){
        String URL=PORT+CHANNEL_HUB+json+FROM_TAG;
        Log.i(KEY,URL);
        return URL;
    }

    /**
     * 得到我的世界
     * @param json
     * @return
     */
    public static String getModelHubUrl(String json){
        String URL=PORT+MODEL_HUB+json+FROM_TAG;
        Log.i(KEY,URL);
        return URL;
    }

}
