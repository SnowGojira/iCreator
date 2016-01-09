package com.example.gululu.icreatorsdksampler.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.speech.RecognitionService;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Haku Hak on 2015/12/16.
 * 持久化数据将，浏览过的uid号，存在database中
 */
public class HistroryDBHelper extends SQLiteOpenHelper{

    private SQLiteDatabase db;
    private final int IS_FLAG=1;
    private final int IS_NOT_FLAG=0;


    private final static String COLUMN_ACCOUNT="account";




    public HistroryDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建SQlite库
        this.db=db;
        db.execSQL("CREATE TABLE " + HistoryDBConfig.dbConfig.TABLE_NAME + "(" +
                HistoryDBConfig.dbConfig.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                "NOT NULL," +
                HistoryDBConfig.dbConfig.COLUMN_NAME_UID + " VARCHAR (50)," +
                HistoryDBConfig.dbConfig.COLUMN_NAME_STATE + " INTEGER NOT NULL" +
                ")");
    }

    /**
     *版本号变化之后调用onUpdate的方法
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE " + HistoryDBConfig.dbConfig.TABLE_NAME + " ADD " +
                COLUMN_ACCOUNT + " VARCHAR(50) NULL");
    }

    /*重构getContentValue*/
    public ContentValues getContentValue(String uid){
        ContentValues cv=new ContentValues();
        cv.put(HistoryDBConfig.dbConfig.COLUMN_NAME_STATE,IS_FLAG);
        cv.put(HistoryDBConfig.dbConfig.COLUMN_NAME_UID, uid);

        return cv;
    }

    /*重构插入函数*/
    public void insert(ContentValues cv){
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        //插入数据库中
        db.insert(HistoryDBConfig.dbConfig.TABLE_NAME, null, cv);
        db.close();
    }


    /*重构查询方法获取指针*/
    public Cursor query(){
        SQLiteDatabase db = getReadableDatabase();
        //获取Cursor
        Cursor c = db.query(HistoryDBConfig.dbConfig.TABLE_NAME, null, null, null, null, null, null, null);
        return c;

    }

    /*逻辑上的删除*/
    public void logicalDelete(int id){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE "+
                HistoryDBConfig.dbConfig.TABLE_NAME+" SET "+
                HistoryDBConfig.dbConfig.COLUMN_NAME_STATE+" = ? WHERE "+
                HistoryDBConfig.dbConfig.COLUMN_NAME_ID+" = ?",
                new String[]{String.valueOf(IS_NOT_FLAG),String.valueOf(id)});

    }
    /*根据唯一标识_id  来删除数据*/
    public boolean delete(int id){
        try{
        SQLiteDatabase db = getWritableDatabase();
        db.delete(HistoryDBConfig.dbConfig.TABLE_NAME, HistoryDBConfig.dbConfig.COLUMN_NAME_ID + "=?", new String[]{String.valueOf(id)});}
        catch (Exception e){
            Log.i("Haku","删除失败:"+e);
            return false;
        }
        return true;
    }


    /*关闭数据库函数*/
    public void close(){
        if(db != null){
            db.close();
        }
    }



}
