package com.example.gululu.icreatorsdksampler.testdemo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ActionMenuView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gululu.icreatorsdksampler.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 2016/1/7.
 */
public class HandlerDemo extends Activity implements AbsListView.OnScrollListener{
    private ListView mListView;
    LinearLayout loadingLayout;
    private Thread mThread;
    private ListViewAdapter adapter;

    //从第1条开始
    private  int startIndex = 1;
    //每次下载十条
    private int size = 10;
    private List<News> newsList;
    List<Map<String,String>> data;

    /**
     *设置布局的显示
     */
    private LinearLayout.LayoutParams mLayoutParams=new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
    );

    private LinearLayout.LayoutParams ffLayoutParams=new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.FILL_PARENT,
            LinearLayout.LayoutParams.FILL_PARENT
    );

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_item);
        data=new ArrayList<Map<String, String>>();
        addView();
    }

    /**
     * @param list
     * @return
     */
    private List<Map<String,String>> getdata(List<News> list){
        if (list==null)
            return null;
        for (News news : list){
            Map<String,String> map=new HashMap<String,String>();
            map.put("title",news.getTitle());
            data.add(map);
        }
        return data;
    }
    /*
* 获取网络数据 注：我是访问本机的一个新闻服务，使用asp.net技术来实现的
* 这个是项目是一个基于android的资讯播报软件
*/
private List<News> getNewsList() {
 String path = "http://10.0.2.2/getNewsList.aspx";
 String xmlStr = "<?xml version='1.0' encoding='utf-8'?><source><categoryIds>1,3,7</categoryIds><startIndex>"
                         + startIndex
                        + "</startIndex><detail>2</detail><count>"
                       + size
                       + "</count></source>";
                //NewsConnector newsConnector = new NewsConnector();
                List<News> list = new ArrayList<News>();
                //list = newsConnector.getNewsList(path, xmlStr);
                return list;
            }

    private void addView(){
        if (startIndex==1){
            newsList = new ArrayList<News>();
        }

    }




    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    class ListViewAdapter extends BaseAdapter{
        int count = 10;

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.news_item,null);
            TextView textView= (TextView) convertView.findViewById(R.id.textNewsTitle);

            textView.setText(data.get(position).get("title"));
            return convertView;
        }
    }
}


