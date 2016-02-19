package com.example.gululu.icreatorsdksampler.utils;

import android.content.Context;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 字符判断工具
 * Created by Haku Hal on 2015/9/18.
 */
public class StringUtils {

    /**
     * Judge String isEmpty
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if (str==null||str.length()==0){
            return true;
        }
        return false;
    }


    /**
     *判断输入的账号和密码是否正确
     * @param mStrPhone
     * @param mStrPwd
     * @param context
     * @return
     */
    public static boolean isCorrectUserFormat(String mStrPhone, String mStrPwd, Context context){
        if(!StringUtils.isEmpty(mStrPhone)){
                //不为空，执行
                if(mStrPhone.length()==11){
                    /**
                     * Account verification is successful
                     */
                    if ( StringUtils.isCorrectPassword(mStrPwd, context))
                    {

                        return true;
                    }
                }else{
                    Toast.makeText(context, "请输入11位账号", Toast.LENGTH_LONG).show();
                    return false;
                }
            }else {
            Toast.makeText(context, "账号不能为空", Toast.LENGTH_LONG).show();
            return false;
        }

        return false;
    }

    /**
     * 判断号码是否正确
     * @param mStrPhone
     * @param context
     * @return
     */
    public static boolean isCorrectMobileFormat(String mStrPhone,Context context){
        if(!StringUtils.isEmpty(mStrPhone)){
            //不为空，执行
            if(mStrPhone.length()==11){
                /**
                 * 判断账号是否正确
                 */
             return true;
            }else{
                Toast.makeText(context, "请输入11位账号", Toast.LENGTH_LONG).show();
                return false;
            }
        }else {
            Toast.makeText(context, "账号不能为空", Toast.LENGTH_LONG).show();
            return false;
        }
    }


    /**
     *判断密码输入格式是否正确
     * @param str
     * @param context
     * @return
     */
    public static boolean isCorrectPassword(String str,Context context){
           if (isEmpty(str)){
               Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
               return false;
           }else if(str.length()<=6){
               Toast.makeText(context,"密码不能少于6位",Toast.LENGTH_SHORT).show();
               return  false;
           }else {
               String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$";
               boolean result = str.matches(regex);
               if (result){
                   return true;
               }else
               {
                   Toast.makeText(context, "密码输入错误", Toast.LENGTH_SHORT).show();
                   return false;
               }
           }
    }

    /**
     * 字符串工具方法
     * 将传入的字符转换为UTF-9格式
     * 目的是防止传给服务器的JSON出错
     * @param str
     * @return
     */
    public static String StrToUTF8(String str){
        try {
            URLEncoder.encode(str, "GBK");
            return  URLEncoder.encode(str, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static String StrFromUTF8(String str){
        try {

            return  URLEncoder.encode(str, "GBK");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        return null;
    }
}
