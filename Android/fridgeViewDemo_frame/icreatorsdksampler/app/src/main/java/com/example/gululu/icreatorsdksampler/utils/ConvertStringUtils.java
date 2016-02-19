package com.example.gululu.icreatorsdksampler.utils;

import android.util.Log;

import com.example.gululu.icreatorsdksampler.bean.UuidBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Haku on 2015/12/25.
 * update 2016/01/05
 */
public class ConvertStringUtils {
    private static String SHADER_UUID="4c66854a6fc14587bc21044eb351cdf1";
    private static String DANDELION_UUID="63e8076d5bbd4025b5d70db9f1a7ac8e";
    private static String PLANTAIN_UUID="75292c26dfc34fee885be455e001b47b";
    private static String SUMSUNGTV_UUID="89a435a42208472cbb91ff4c5ae0ef28";
    private static String ELECTROX_UUID="d6da8e4327be488f8bd2e144454fac83";
    private static String TABLETYPE002_UUID="1cc73b0c6eb941cdbbc7934897c936ad";
    private static String CLOSESTOOLTYPE007_UUID="02cc2ed9e8a44090b5b73ca3a2d66487";
    private static String MIDEAWASHING01_UUID="318c10d1f8034909bf6909baca73fd3b";
    private static String TABLETYPE003_UUID="32849698ea304b5d97586a0d89a71030";
    private static String WATERFOURTAIN_UUID="5e8883cf8d734b709a60fe07aeb69bdb";
    private static String SUMSUNGWASHING01_UUID="5ebda24f3cc54dab92ef4e770ff0f602";
    private static String CLOSESTOOL02_UUID="7023d2808eb54445b41549298a67ab61";
    private static String TABLETYPE005_UUID="a177f8ec8b6b48c5b79afe4df1542abc";
    private static String TABLETYPE006_UUID="aa7510220cbb41c58f3bdc9668258905";
    private static String SONYTV001_UUID="b2c13b7f15ef402eac4234ebea1d9be6";
    private static String BATHTUB001_UUID="b633791c35a1448ba836c633c9859521";
    private static String TABLETYPE001_UUID="bde9c2b1e6f34fd6971c24ce3a50a795";
    private static String TABLETYPE007_UUID="bdff1f922ec044ce81921d8baa2ec159";
    private static String BATHTUB009_UUID="d793e6350507439085c7aedd5c019d40";
    private static String WASHINGMACHINE_UUID="e0c8c5d557084deebbd3de14642d58dd";
    private static String KITCHEN01_UUID="18da3b1a9e764d29b02a0a7af6040f41";
    private static String KITCHEN03_UUID="07333755732b4d79a630f1875feac633";
    private static String KITCHEN04_UUID="cebf0183ec7a4dcba834a877e11c6d97";
    private static String KITCHEN05_UUID="8a4ce7fa4b6149ea9c84f9924b244048";
    private static String KITCHEN06_UUID="2dedce80fb704c008c2680ba0f549ec4";
    private static String KITCHEN07_UUID="cc498fcc5b9f4478905d4bfe7f44c1da";
    private static String BATHTUB08_UUID="e2b083c894194deba5cd5b737f85f856";
    private static String TABLETYPE004_UUID="eeaadc66b54647b09c6f23981d6ac0a6";

    /*狗狗*/
    private static String DOG_UUID="6ae2a52465c641e2ab767b264b68a0d3";
    /*兔兔*/
    private static String BUNNY_UUID="f7f792d7772645058b9fa170877dd23c";
    private static String V1_UUID="e979aed3aee1435a856ce758f7df50e6";
    private static String V2_UUID="07b4d6b0c02b4894a98e16e011743e8f";
    private static String V5_UUID="2840540376c74be0ae6fd3d001753a18";
    private static String V8_UUID="fb88e4d7b23453cadb767df00e46ad7";

    private static String V9_UUID="33e160c35e994c05936271fca37d0d51";
    private static String V10_UUID="3c51742d440b4071bc1d1940265e3cba";
    private static String V12_UUID="a37d7e3948b84408839e112bb5362125";
    private static String V17_UUID="087192f60fd3446d938c754379a6cefe";
    private static String V18_UUID="3d2f040f584c438b88e7a3ccc5d492f2";
    private static String V19_UUID="0d68e53c30f74ab2a4fb9dfb5ada390a";
    private static String V20_UUID="7e592c3614c94a8996358ed54694da65";
    private static String V21_UUID="af9a6ee1298a4d998daf063908bd776b";
    private static String V22_UUID="6eb37f5c22c04e30a95965c1145325b4";
    private static String V23_UUID="2810b8d53d7241e6a1410dca53692f0a";

    private static String ROBOTO_UUID="64d587ed251c44069e395289a4714c79";
    private static String ORANGE_UUID="0b7f1347f2564ac98db065a00fdc5c0a";
    private static String EVIL_UUID="c2da991aa299486c90c5c9434759074a";
    private static String BREAD_UUID="5debe1440a0a4986b1f5f5e35150191e";
    private static String BANANA_UUID="baba30582638401fa01ebc5aff58c15e";
    private static String DIAOSU13_UUID="ded84770652b4f589e9b9c1cf942c990";
    private static String DIAOSU14_UUID="90c8727daee742ce923412b38f625276";




    final static String[] Uuids=new String[]{
            SHADER_UUID,V5_UUID,DANDELION_UUID,PLANTAIN_UUID,SUMSUNGTV_UUID,ELECTROX_UUID,
            TABLETYPE002_UUID,CLOSESTOOLTYPE007_UUID,MIDEAWASHING01_UUID,TABLETYPE003_UUID,
            WATERFOURTAIN_UUID,SUMSUNGWASHING01_UUID,CLOSESTOOL02_UUID,TABLETYPE005_UUID,
            TABLETYPE006_UUID,SONYTV001_UUID,BATHTUB001_UUID,TABLETYPE001_UUID,V9_UUID,
            V10_UUID,V12_UUID,V17_UUID,V18_UUID,V19_UUID,V20_UUID,V21_UUID,V22_UUID,V23_UUID,
            TABLETYPE007_UUID,BATHTUB009_UUID,WASHINGMACHINE_UUID,KITCHEN01_UUID,KITCHEN03_UUID,
            KITCHEN05_UUID,KITCHEN04_UUID,KITCHEN06_UUID,KITCHEN07_UUID,BATHTUB08_UUID,
            TABLETYPE004_UUID,DOG_UUID,BUNNY_UUID,V1_UUID,V2_UUID,ROBOTO_UUID,ORANGE_UUID,EVIL_UUID,
            BREAD_UUID,BANANA_UUID,DIAOSU13_UUID,DIAOSU14_UUID

    };




    private static Map storeMap(){
        Map<String,UuidBean> mMap=new HashMap<>();

        UuidBean[] Beans=new UuidBean[]{
          new UuidBean("shader","shader"),
          new UuidBean("虎皮草","Tiger Grass"),
                new UuidBean("蒲公英","Dandelion Flower"),
                new UuidBean("车前草","Plantain Grass"),
                new UuidBean("电视机","Sumsung Television"),
                new UuidBean("冰箱","Elextron refrigerator"),
                new UuidBean("餐桌002","Table type-002"),
                new UuidBean("马桶007","close stool type-007"),
                new UuidBean("美的洗衣机01","Midea Washing Machine01"),
                new UuidBean("餐桌003","Table type-003"),
                new UuidBean("饮水机","Water fountains"),
                new UuidBean("三星洗衣机01","SumSung washing machine-01"),
                new UuidBean("马桶02","Close stool-02"),
                new UuidBean("餐桌005","Table type-005"),
                new UuidBean("餐桌006","Table type-006"),
                new UuidBean("索尼电视机01","Sony television-001"),
                new UuidBean("浴缸001","Bathtub-001"),
                new UuidBean("餐桌001","Table type-001"),
                /**/
                new UuidBean("竹笋","Banboo"),
                new UuidBean("月季","Banboo"),
                new UuidBean("虎尾兰","Banboo"),
                new UuidBean("虎耳草","Banboo"),
                new UuidBean("发财树","Banboo"),
                new UuidBean("龙舌兰","Banboo"),
                new UuidBean("万年青","Banboo"),
                new UuidBean("梅花","Banboo"),
                new UuidBean("水仙","Banboo"),
                new UuidBean("紫罗兰","Banboo"),
                /**/
                new UuidBean("餐桌007","Table type-007"),
                new UuidBean("浴缸009","Bathtub-009"),
                new UuidBean("洗衣机","washing machine"),
                new UuidBean("厨房001","kitchen-001"),
                new UuidBean("厨房003","kitchen-003"),
                new UuidBean("厨房005",""),
                new UuidBean("厨房004",""),
                new UuidBean("厨房006",""),
                new UuidBean("厨房007",""),
                new UuidBean("浴缸008",""),
                new UuidBean("餐桌004",""),
                new UuidBean("狗狗",""),
                new UuidBean("兔兔",""),
                new UuidBean("棕榈",""),
                new UuidBean("芦荟",""),

                new UuidBean("机器人",""),
                new UuidBean("橘子",""),
                new UuidBean("小恶魔",""),
                new UuidBean("面包",""),
                new UuidBean("香蕉",""),
                new UuidBean("埃及雕像",""),
                new UuidBean("14",""),

        };


        for (int i=0;i<Beans.length;i++){
            mMap.put(Uuids[i],Beans[i]);

            Log.i("Haku",Beans.length+" "+Uuids.length);
        }

        return mMap;
    }

    /**
     * 将uuid转化成文件名
     */
    public static String convertToName(String uid){
        UuidBean bean= (UuidBean) storeMap().get(uid);
        if (bean!=null){
            return bean.getNameCN();
        }

        return "测试号";

    }

    /**
     判断uuid是否在库内部
     */

    public static boolean isUuid(String uid){
        UuidBean bean= (UuidBean) storeMap().get(uid);
        if (bean!=null){
            return true;
        }
        return false;
    }

    /**
     * 为了漂漂转换成英文字符
     * 之后有可能删掉*/

    public static String translateName(String uid){
        UuidBean bean= (UuidBean) storeMap().get(uid);
        if (bean!=null){
            return bean.getNameENG();
        }
        return uid;
    }

}
