package dev.wanwu.util;

import dev.wanwu.controller.AdminUserController;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class RegistActivity{

	private static final Logger LOG = Logger.getLogger(RegistActivity.class);
	
    public static String requestByPost(String path) throws Throwable {
    	 BufferedReader in = null;
    	 DataOutputStream dos = null;
    	 String result = "";
        // 请求的参数转换为byte数组
        String params = "id=" + URLEncoder.encode("helloworld", "UTF-8")
                + "&pwd=" + URLEncoder.encode("android", "UTF-8");
        try{
        byte[] postData = params.getBytes();
        // 新建一个URL对象
        URL url = new URL(path);
        // 打开一个HttpURLConnection连接
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        // 设置连接超时时间
        urlConn.setConnectTimeout(5 * 1000);
        // Post请求必须设置允许输出
        urlConn.setDoOutput(true);
        // Post请求不能使用缓存
        urlConn.setUseCaches(false);
        // 设置为Post请求
        urlConn.setRequestMethod("POST");
        urlConn.setInstanceFollowRedirects(true);
        // 配置请求Content-Type
        urlConn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencode");
        // 开始连接
        urlConn.connect();
        LOG.debug("aaa:正在连接");
        // 发送请求参数
         dos = new DataOutputStream(urlConn.getOutputStream());
        dos.write(postData);
        dos.flush();
        
        in = new BufferedReader(
                new InputStreamReader(urlConn.getInputStream(),"utf-8"));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        }catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(dos!=null){
                	dos.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public static void main(String[] args) throws Throwable {
    	RegistActivity  rac = new RegistActivity();
    	String result = rac.requestByPost("http://10.10.10.128:8080/jq/api/testModel");
    	System.out.println(result);
	}
}
