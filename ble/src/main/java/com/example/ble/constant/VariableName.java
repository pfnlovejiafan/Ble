package com.example.ble.constant;
public class VariableName {

    public static boolean DEBUG = false;

    public final static String DATA = "DATA";
    public final static String DATA_TWO = "DATA_TWO";
    public final static String DATA_THREE = "DATA_THREE";
    public final static String DATA_FOUR = "DATA_FOUR";
    public final static String DATA_FIVE = "DATA_FIVE";

    public final static String TITLE = "TITLE";
    public final static String TYPE = "TYPE";


    public final static int DECIMAL_NUMBER = 4;

    public final static int REQUEST_CODE_ONE = 10086;
    public final static int REQUEST_CODE_TWO = 10087;
    public final static int REQUEST_CODE_THREE = 10088;

    public static double price = 0.0;


    public static final String DEVICE_LIST = "DEVICE_LIST";
    public static final String DEVICE_NAME = "DEVICE_NAME";
    public static final String MAC = "MAC";
    public static final String DEVICE_VERSION = "DEVICE_VERSION";
    //当前所连接的蓝牙
    public static final String CONNECTED_SERVICE_UUID = "CONNECTED_SERVICE_UUID";
    public static final String CONNECTED_WRITE_UUID = "CONNECTED_WRITE_UUID";
    public static final String CONNECTED_READ_UUID = "CONNECTED_READ_UUID";


    //二代蓝牙协议UUID
    public static final String CL_SERVICE_UUID = "0000FFE0-0000-1000-8000-00805F9B34FB";
    public static final String CL_TAG_WRITE_UUID = "0000FFE2-0000-1000-8000-00805F9B34FB";
    public static final String CL_TAG_READ_UUID = "0000FFE1-0000-1000-8000-00805F9B34FB";

    //一代蓝牙协议UUID
    public static final String YZM_SERVICE_UUID = "00003000-0606-0505-0404-030302020101";
    public static final String YZM_READ_UUID = "00003002-0606-0505-0404-030302020101";
    public static final String YZM_WRITE_UUID = "00003001-0606-0505-0404-030302020101";


    public static final String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";

    public final static String FIRST_LAUNCHER = "FIRST_LAUNCHER";
    public final static String FIRST_COUNTRY = "FIRST_COUNTRY";
    public final static String AlarmClock = "AlarmClock";


    public final static int pressureMIN = 15;
    public final static int pressureMAX = 100;

    public final static int temperatureMIN = 20;
    public final static int temperatureMAX = 55;

    //userInfo
    public final static String TOKEN = "TOKEN";
    public final static String userId = "userId";
    public final static String headImg = "headImg";
    public final static String phoneNumber = "phoneNumber";
    public final static String nickName = "nickName";
    //loginInfo
    public final static String ACCOUNT = "account";
    public final static String PASSWORD = "password";
    //是否同意用户协议与隐私政策
    public final static String isAgree = "agreement";
    //是否记住密码
    public final static String isRememberPassword = "isRememberPassword";
    public final static String isChildMode = "isChildMode";
    public final static String isPregnantMode = "isPregnantMode";


    public static String nowMac;
    public static String nowDeviceName;
    public static int deviceId;

    /*
     *蓝牙连接状态
     * 0-连接成功 1000-连接失败 1001-自动断开 1002-手动断开
     */
    public static int CONNECT_SUCCESS = 0;
    public static int CONNECT_FAILED = 1000;
    public static int CONNECT_AUTO_DISCONNECT = 1001;
    public static int CONNECT_MANUAL_DISCONNECT = 1002;

    //高德开放平台API KEY
    public static String GdKey = "0395f1a26432746cde48aeb5042e885e";



}
