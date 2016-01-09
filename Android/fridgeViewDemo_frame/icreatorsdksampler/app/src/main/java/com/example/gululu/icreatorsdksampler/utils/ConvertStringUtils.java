package com.example.gululu.icreatorsdksampler.utils;

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




    /**
     * 将uuid转化成文件名
     */
    public static String convertToName(String uid){
        if (uid.equals(SHADER_UUID)){
            return "shader";
        }
        else if (uid.equals(DANDELION_UUID)){
            return "蒲公英";
        }
        else if(uid.equals(PLANTAIN_UUID)){
            return "车前草";
        }
        else if(uid.equals(SUMSUNGTV_UUID)){
            return "电视机";
        }
        else if(uid.equals(ELECTROX_UUID)){
            return "冰箱";
        }
        else if(uid.equals(TABLETYPE002_UUID)){
            return "餐桌002";
        }
        else if(uid.equals(CLOSESTOOLTYPE007_UUID)){
            return "马桶007";
        }
        else if(uid.equals(MIDEAWASHING01_UUID)){
            return "美的洗衣机01";
        }
        else if(uid.equals(TABLETYPE003_UUID)){
            return "餐桌003";
        } else if(uid.equals(WATERFOURTAIN_UUID)){
            return "饮水机";
        }else if(uid.equals(SUMSUNGWASHING01_UUID)){
            return "三星洗衣机01";
        }else if(uid.equals(CLOSESTOOL02_UUID)){
            return "马桶02";
        }else if(uid.equals(TABLETYPE005_UUID)){
            return "餐桌005";
        }else if(uid.equals(TABLETYPE006_UUID)){
            return "餐桌006";
        }else if(uid.equals(SONYTV001_UUID)){
            return "索尼电视机01";
        }else if(uid.equals(BATHTUB001_UUID)){
            return "浴缸001";
        }else if(uid.equals(TABLETYPE001_UUID)){
            return "餐桌001";
        }else if(uid.equals(TABLETYPE007_UUID)){
            return "餐桌007";
        }else if(uid.equals(BATHTUB009_UUID)){
            return "浴缸009";
        }else if(uid.equals(WASHINGMACHINE_UUID)){
            return "洗衣机";
        }else if(uid.equals(KITCHEN01_UUID)){
            return "厨房001";
        }else if(uid.equals(KITCHEN03_UUID)){
            return "厨房003";
        }else if(uid.equals(KITCHEN05_UUID)){
            return "厨房005";
        }else if(uid.equals(KITCHEN04_UUID)){
            return "厨房004";
        }else if(uid.equals(KITCHEN06_UUID)){
            return "厨房006";
        }else if(uid.equals(KITCHEN07_UUID)){
            return "厨房007";
        }else if(uid.equals(BATHTUB08_UUID)){
            return "浴缸008";
        }else if(uid.equals(TABLETYPE004_UUID)){
            return "餐桌004";
        }

        return "测试号";
    }

    /**
     判断uuid是否在库内部
     */

    public static boolean isUuid(String uid){

        if (uid.equals(SHADER_UUID)){
            return true;
        }
        else if (uid.equals(DANDELION_UUID)){
            return true;
        }
        else if(uid.equals(PLANTAIN_UUID)){
            return true;
        }
        else if(uid.equals(SUMSUNGTV_UUID)){
            return true;
        }
        else if(uid.equals(ELECTROX_UUID)){
            return true;
        }
        else if(uid.equals(TABLETYPE002_UUID)){
            return true;
        }
        else if(uid.equals(CLOSESTOOLTYPE007_UUID)){
            return true;
        }
        else if(uid.equals(MIDEAWASHING01_UUID)){
            return true;
        }
        else if(uid.equals(TABLETYPE003_UUID)){
            return true;
        } else if(uid.equals(WATERFOURTAIN_UUID)){
            return true;
        }else if(uid.equals(SUMSUNGWASHING01_UUID)){
            return true;
        }else if(uid.equals(CLOSESTOOL02_UUID)){
            return true;
        }else if(uid.equals(TABLETYPE005_UUID)){
            return true;
        }else if(uid.equals(TABLETYPE006_UUID)){
            return true;
        }else if(uid.equals(SONYTV001_UUID)){

            return true;
        }else if(uid.equals(BATHTUB001_UUID)){
            return true;
        }else if(uid.equals(TABLETYPE001_UUID)){
            return true;
        }else if(uid.equals(TABLETYPE007_UUID)){
            return true;
        }else if(uid.equals(BATHTUB009_UUID)){
            return true;
        }else if(uid.equals(WASHINGMACHINE_UUID)){
            return true;
        }else if(uid.equals(KITCHEN01_UUID)){
            return true;
        }else if(uid.equals(KITCHEN03_UUID)){
            return true;
        }else if(uid.equals(KITCHEN05_UUID)){
            return true;
        }else if(uid.equals(KITCHEN04_UUID)){
            return true;
        }else if(uid.equals(KITCHEN06_UUID)){
            return true;
        }else if(uid.equals(KITCHEN07_UUID)){
            return true;
        }else if(uid.equals(BATHTUB08_UUID)){
            return true;
        }else if(uid.equals(TABLETYPE004_UUID)){
            return true;
        }

        return false;

    }

    /**
     * 为了漂漂转换成英文字符
     * 之后有可能删掉*/

    public static String translateName(String uid){
        if(isUuid(uid)){
            if (uid.equals(SHADER_UUID)){
                return "Test Shader(dev)";
            }
            else if (uid.equals(DANDELION_UUID)){
                return "Dandelion Flower";
            }
            else if(uid.equals(PLANTAIN_UUID)){
                return "Plantain Grass";
            }
            else if(uid.equals(SUMSUNGTV_UUID)){
                return "Sumsung Television";
            }
            else if(uid.equals(ELECTROX_UUID)){
                return "Elextron refrigerator";
            }
            else if(uid.equals(TABLETYPE002_UUID)){
                return "Table type-002";
            }
            else if(uid.equals(CLOSESTOOLTYPE007_UUID)){
                return "close stool type-007";
            }
            else if(uid.equals(MIDEAWASHING01_UUID)){
                return "Midea Washing Machine01";
            }
            else if(uid.equals(TABLETYPE003_UUID)){
                return "Table type-003";
            } else if(uid.equals(WATERFOURTAIN_UUID)){
                return "Water fountains";
            }else if(uid.equals(SUMSUNGWASHING01_UUID)){
                return "SumSung washing machine-01";
            }else if(uid.equals(CLOSESTOOL02_UUID)){
                return "Close stool-02";
            }else if(uid.equals(TABLETYPE005_UUID)){
                return "Table type-005";
            }else if(uid.equals(TABLETYPE006_UUID)){
                return "Table type-006";
            }else if(uid.equals(SONYTV001_UUID)){
                return "Sony television-001";
            }else if(uid.equals(BATHTUB001_UUID)){
                return "Bathtub-001";
            }else if(uid.equals(TABLETYPE001_UUID)){
                return "Table type-001";
            }else if(uid.equals(TABLETYPE007_UUID)){
                return "Table type-007";
            }else if(uid.equals(BATHTUB009_UUID)){
                return "Bathtub-009";
            }else if(uid.equals(WASHINGMACHINE_UUID)){
                return "washing machine";
            }else if(uid.equals(KITCHEN01_UUID)){
                return "kitchen-001";
            }else if(uid.equals(KITCHEN03_UUID)){
                return "kitchen-003";
            }else if(uid.equals(KITCHEN05_UUID)){
                return "kitchen-005";
            }else if(uid.equals(KITCHEN04_UUID)){
                return "kitchen-004";
            }
            else if(uid.equals(KITCHEN06_UUID)){
                return "kitchen-006";
            }else if(uid.equals(KITCHEN07_UUID)){
                return "kitchen-007";
            }else if(uid.equals(BATHTUB08_UUID)){
                return "Bathtub-008";
            }else if(uid.equals(TABLETYPE004_UUID)){
                return "Table type-004";
            }
        }

        return uid;

    }

}
