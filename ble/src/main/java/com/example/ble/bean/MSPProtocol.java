package com.example.ble.bean;

import android.util.Log;

import com.clj.fastble.data.BleDevice;
import com.example.ble.Utils.AES;
import com.example.ble.Utils.BleUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//
// Created by mac(1004633425@qq.com) on 2018/10/20.
// Copyright (c) YunZhiMian All rights reserved.
//
public class MSPProtocol {

    private static final String TAG = "MSPProtocol";
    private static final MSPProtocol mSPProtocol = new MSPProtocol();
    public BleDevice bleDevice;
    /**
     * 获取命令返回结果
     */
    private byte okcall;//加热命令硬件返回结果
    private byte setTimeOk;//设置自动补气时间命令硬件返回结果

    private List<Byte> dataList = Collections.synchronizedList(new LinkedList<Byte>());
    private Thread handleDataTrd;
    private boolean isDataTrdRun;

    private byte heatLevel;//加热档位

    private byte lPresureSetVal;//左床气压设置值
    private byte rPresureSetVal;//右床气压设置值

    private byte lPresureMemVal;//左床气压记忆值1
    private byte rPresureMemVal;//右床气压记忆值

    private int lPresureCurVal;//左床气压实时值
    private int rPresureCurVal;//右床气压实时值

    private byte lWaistSetVal;//左腰气压设置值
    private byte rWaistSetVal;//右腰气压设置值

    private byte lWaistMemVal;//左腰气压记忆值
    private byte rWaistMemVal;//右腰气压记忆值

    private int lWaistCurVal;//左腰气压实时值
    private int rWaistCurVal;//右腰气压实时值

    private byte isWiFiConnSign;//WiFi联网标志
    private byte lSnoreSign_;//左打鼾标志
    private byte lHeartBeat;//左心率
    private byte lBreathFreq;//左呼吸频率
    //区分心率带，0-力感心率带，1-云传&量子心率带
    private int heatType;
    private byte lSnoreSign;//左打鼾标志
    private byte lBodyMoveByMinuteSign;//左分钟体动标识位
    private byte lBodyMoveBySecondSign;//左秒体动标识位
    private int lBodyMoveValH, lBodyMoveValL;//左体动值高，低位值

    private byte rHeartBeat;//右心率
    private byte rBreathFreq;//右呼吸频率
    private byte rSnoreSign_;//右打鼾标志体动标识位

    private byte rSnoreSign;//右打鼾标志
    private byte rBodyMoveByMinuteSign;//右分钟体动标识位
    private byte rBodyMoveBySecondSign;//右秒体动标识位
    private int rBodyMoveValH, rBodyMoveValL;//右体动值高，低位值

    private byte high1 = 0;//1路升降机高度
    private byte high2 = 0;//2路升降机高度
    private byte high3 = 0;//3路升降机高度
    private byte high4 = 0;//4路升降机高度

    private byte oneLevel = 0; //第一路电机档位
    private byte twoLevel = 0; //第二路电机档位
    private byte threeLevel = 0; //第三路电机档位

    private byte mode;//模式

    private byte tire_hour;//自动补气时
    private byte tire_minute;//自动补气分

    private byte weeks;//补气星期

    private byte awake_hour;//自动唤醒时
    private byte awake_minute;//自动唤醒分
    private byte awake_weeks;//自动唤醒星期
    private byte liftTime;//床高度倍数
    private byte l_snoreHeight;//止鼾高度
    private byte awakeHeight;//唤醒高度

    private byte curtainGear;//窗帘档位

    private byte setState;//设置状态


    /*------8路电机的气压数据------*/
    //设置值
    private int setPressValP1;
    private int setPressValP2;
    private int setPressValP3;
    private int setPressValP4;
    private int setPressValP5;
    private int setPressValP6;
    private int setPressValP7;
    private int setPressValP8;
    //实时值
    private int culPressValP1;
    private int culPressValP2;
    private int culPressValP3;
    private int culPressValP4;
    private int culPressValP5;
    private int culPressValP6;
    private int culPressValP7;
    private int culPressValP8;
    private int culPressFlay1;
    private int culPressFlay2;
    private int culPressFlay3;
    private int culPressFlay4;
    private int culPressFlay5;
    private int culPressFlay6;
    private int culPressFlay7;
    private int culPressFlay8;
    private int culPressFlay9;

    /*------8路电机的值------*/
    private int setLeftAuto;
    private int setValP2;
    private int setValP3;
    private int setValP4;
    private int setValP5;
    private int setValP6;
    private int setValP7;
    private int setValP8;

    private int setRightAuto;
    private int culValP2;
    private int culValP3;
    private int culValP4;
    private int culValP5;
    private int culValP6;
    private int culValP7;
    private int culValP8;
    /*------8路电机的气压数据------*/
    private byte[] data;
    private byte[] totalData;
    private byte l_sensitivity_selection;
    private int l_snore_stopping_time;
    private byte r_snoreHeight;
    private byte r_sensitivity_selection;
    private int r_snore_stopping_time;
    private int time_remaining_1;
    private int time_remaining_r;
    private int culPressStatus1;
    private int culPressStatus2;
    private int culPressStatus3;
    private int culPressStatus4;
    private int culPressStatus5;
    private int culPressStatus6;
    private int culPressStatus7;
    private int culPressStatus8;
    private int culPressStatus9;
    private int culPressStatus10;
    private int getMassage1;
    private int getMassage2;
    private int getMassage3;
    private int getMassage4;
    private int left_mode;
    private int right_mode;
    private int left_bedside_tou;
    private int left_bedside_wei;
    private int right_bedside_tou;
    private int right_bedside_wei;
    private String zhuban;
    private String bleBan;
    private int tiyan;
    private int getOverallCulPressValP1;
    private int getOverallCulPressValP2;
    private int getOverallCulPressValP3;
    private int getOverallCulPressValP4;
    private int getOverallCulPressValP5;
    private int getOverallCulPressValP6;
    private int getOverallCulPressValP7;
    private int getOverallCulPressValP8;
    public int acquisitionValP;
    public int acquisitionValP1;
    public int acquisitionValP2;
    public int acquisitionValP3;
    public int acquisitionValP4;
    public int acquisitionValP5;
    public int acquisitionValP6;
    public int acquisitionValP7;
    public int acquisitionValP8;
    private int setRenWen;
    private int awake_num_left;
    private int awake_num_right;
    private int dianZong;


    public static MSPProtocol getInstance() {
        return mSPProtocol;
    }

    private MSPProtocol() {
        //开启数据解析线程
        if (handleDataTrd == null || !handleDataTrd.isAlive()) {
            dataList.clear();
            isDataTrdRun = true;
            handleDataTrd = new Thread(mHandleDataRunnable);
            handleDataTrd.start();
        }
    }

    //接收数据
    public void setRawBytes(byte[] rawBytes) {
        data = rawBytes;
        //Log.i("接收到的数据", BleUtils.bytesToHexString(data));
        for (byte rawByte : rawBytes) {
            dataList.add(rawByte);
//            Log.i("百特",Integer.toHexString(Integer.parseInt(String.valueOf(rawByte))));
        }

    }
    public void parseData(byte[] data) {
        byte[] enen = Arrays.copyOfRange(data, 0, 2);
        Log.i("解密前截取", Arrays.toString(enen));
        if (Arrays.equals(enen,new byte[]{-86,16})) {
            if (totalData!=null) {
//                EventBus.getDefault().post(new BleConEvent(true));
                Log.i("解密前", Arrays.toString(totalData));
                int length = (totalData[2] & 0xff) - 5;
                if (totalData.length < length) {
                    length = totalData.length - 5;
                }
                byte[] target = new byte[length];
                System.arraycopy(totalData, 3, target, 0, target.length);
                try {
                    byte [] decrypt = AES.Decrypt(target, "yzm_blue_2020_01");
                    parse(decrypt);
                    Log.i("解密后", Arrays.toString(decrypt));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("解密报错", e.toString());
                }
                //EventBus.getDefault().post(new BleConEvent(true));
            }
            totalData = data;
        } else {
            if (totalData!=null){
                totalData= BleUtils.insertData(totalData,totalData.length,data);
            }
        }
    }

    private Runnable mHandleDataRunnable = new Runnable() {
        @Override
        public void run() {
            while (isDataTrdRun) {

                if (dataList.size() >= 4) {
                    if ((dataList.get(0) & 0xff) == 0xbb) {//包头
                        int dataLen = dataList.get(1) & 0xff;//数据长度，整个数据包长
                        int commandType = dataList.get(2) & 0xff;//命令
//                        Log.i("解析", "数据解析进行中......");
//                        EventBus.getDefault().post(new BleConEvent(true));
                        if (dataList.size() >= dataLen) {
                            int checkSumVal = dataList.get(dataLen - 1) & 0xff;
                            //if (checkSumVal == checkSum(dataList, dataLen)) {//校验
                            if (commandType == 0x31) {//设备的状态
                                if (dataList.size() >= 10) {
                                    onDeviceStateInfo();
                                }
                            }
                            if (commandType == 0x21) {//WiFi SSID发送情况
                                int dataPackOrder = dataList.get(3) & 0xff;//包序
                                int res = dataList.get(4) & 0xff;
//                                EventBus.getDefault().post(new ConfigureResEvent(commandType, dataPackOrder, res));
                            }
                            if (commandType == 0x22) {//WiFi 密码发送情况
                                int dataPackOrder = dataList.get(3) & 0xff;//包序
                                int res = dataList.get(4) & 0xff;
//                                EventBus.getDefault().post(new ConfigureResEvent(commandType, dataPackOrder, res));
                            }
                            if (commandType == 0x24) {
                                test();
                            }
                            if (commandType == 0x28) {
                                test1();
                            }
                            //}
                            for (int i = 0; i < dataLen; i++) {
                                dataList.remove(0);
                            }
                        }
                    } else {
                        dataList.remove(0);
                    }
                }


                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void test1() {
        setTimeOk = dataList.get(3);
    }

    //获取发送加热命令之后硬件的返回命令
    private void test() {
        okcall = dataList.get(3);
    }

    //处理设备上报信息
    private void onDeviceStateInfo() {
        int dataPackOrder = dataList.get(3) & 0xff;//包序
        switch (dataPackOrder) {
            case 0x1://第1包
//                Log.i("Bounds", String.valueOf(dataList.size()));
                if (dataList.size() < 17) {
                    return;
                }

                heatLevel = dataList.get(4);
                lPresureSetVal = dataList.get(5);
                rPresureSetVal = dataList.get(6);
                lPresureMemVal = dataList.get(7);
                rPresureMemVal = dataList.get(8);
                lPresureCurVal = dataList.get(9);
                rPresureCurVal = dataList.get(10);
                isWiFiConnSign = dataList.get(11);
                lHeartBeat = dataList.get(12);
                lBreathFreq = dataList.get(13);
                lSnoreSign = dataList.get(14);
                lBodyMoveByMinuteSign = dataList.get(15);
                lBodyMoveBySecondSign = dataList.get(16);
               /* if (isWiFiConnSign == 1) {
                    EventBus.getDefault().post(new WifiConEvent(true));
                } else {
                    EventBus.getDefault().post(new WifiConEvent(false));
                }*/
                break;
            case 0x2: { //第2包
                if (dataList.size() < 19) {
                    return;
                }
                List<Byte> byteList = new ArrayList<>();
                //从第5个字节开始，高位左体动值占15个字节
                for (int i = 0; i < 15; i++) {
                    byteList.add(dataList.get(i + 4));
                }
                lBodyMoveValH = bytesToInt(byteList);
                break;
            }
            case 0x3: {//第3包

                if (dataList.size() < 19) {
                    return;
                }
                List<Byte> lbyteList = new ArrayList<>();
                //从第5个字节开始，低位左体动值占5个字节
                for (int i = 0; i < 5; i++) {
                    lbyteList.add(dataList.get(i + 4));
                }
                lBodyMoveValL = bytesToInt(lbyteList);

                rHeartBeat = dataList.get(9);
                rBreathFreq = dataList.get(10);
                rSnoreSign = dataList.get(11);
                rBodyMoveByMinuteSign = dataList.get(12);
                rBodyMoveBySecondSign = dataList.get(13);

                List<Byte> rbyteList = new ArrayList<>();
                //从15个字节开始，高位右体动值占5个字节
                for (int i = 0; i < 5; i++) {
                    rbyteList.add(dataList.get(i + 14));
                }
                rBodyMoveValH = bytesToInt(rbyteList);

                break;
            }
            case 0x4: {//第4包
                if (dataList.size() < 19) {
                    return;
                }
                List<Byte> byteList = new ArrayList<>();
                //从第5个字节开始，低位右体动值占15个字节
                for (int i = 0; i < 15; i++) {
                    byteList.add(dataList.get(i + 4));
                }
                rBodyMoveValL = bytesToInt(byteList);
                break;
            }
            case 0x5://第5包
                if (dataList.size() < 17) {
                    return;
                }
                high1 = dataList.get(4);
                high2 = dataList.get(5);
                high3 = dataList.get(6);
                high4 = dataList.get(7);
                mode = dataList.get(8);
                tire_hour = dataList.get(9);
                tire_minute = dataList.get(10);
                weeks = dataList.get(11);
                liftTime = dataList.get(12);
                awake_hour = dataList.get(14);
                awake_minute = dataList.get(15);
                awake_weeks = dataList.get(16);
                break;
            case 0x6://第6包
                if (dataList.size() < 10) {
                    return;
                }
                lWaistSetVal = dataList.get(4);
                rWaistSetVal = dataList.get(5);
                lWaistMemVal = dataList.get(6);
                rWaistMemVal = dataList.get(7);
                lWaistCurVal = dataList.get(8);
                rWaistCurVal = dataList.get(9);
                if (dataList.size() > 11) {
                    l_snoreHeight = dataList.get(10);
                    awakeHeight = dataList.get(11);
                }
//                Log.i("一看", "dataList.get(8) is " + dataList.get(8) + " dataList.get(9) is " + dataList.get(9));
                break;
        }
    }

    private void parse(byte[] data) {
        if (data==null) {
            return;
        }
        int index = 1;
        while (index < data.length) {
            int num = (data[index] & 0xff) - 1;
            if (num < 0) {
                return;
            }
            byte[] two = new byte[num];
            if (index + num >= data.length) {
                return;
            }
            if (num == 0) {
                return;
            }
            System.arraycopy(data, index + 1, two, 0, num);
            setValue(two);
            index = index + num + 1;
        }
    }
    private String ceshi;
    private void setValue(byte[] value) {
        if (value.length == 0) {
            return;
        }
        switch (value[0]) {
            case 0x05:
                //力感心率数据包
                lHeartBeat = value[1];//左心率
                rHeartBeat = value[1];//右心率
                lBreathFreq = value[2];//左呼吸率
                rBreathFreq = value[2];//右呼吸率
                break;
            case 0x06:
                oneLevel = value[1];//第一路电机档位
                //Log.d("电视架", "1路电机:" + oneLevel);
                break;
            case 0x08:
                twoLevel = value[1];//第二路电机档位
                //Log.d("电视架", "2路电机:" + twoLevel);
                break;
            case 0x0a:
                threeLevel = value[1];//第三路点击档位
                //Log.d("电视架", "3路电机:" + threeLevel);
                break;
            case 0x0f:
                ceshi="";
                for (int i = 0; i < value.length; i++) {
                    ceshi=ceshi+value[i];
                }
                Log.i("测试设置状态=============",ceshi+"==============设置状态2"+ setState);
                setState = value[1];
                Log.i("设置状态2", "" + setState);
                break;
            case 0x16:
                heatLevel = value[1];//加热档位
                break;
            case 0x17:
                lPresureSetVal = value[1];//左床气压设置值
                rPresureSetVal = value[2];//右床气压设置值
                lWaistSetVal = value[3];//左腰气压设置值
                rWaistSetVal = value[4];//右腰气压设置值
                break;
            case 0x18:
                high1 = value[1];//左床头升降高度
                high2 = value[2];//左床尾升降高度
                high3 = value[3];//右床头升降高度
                high4 = value[4];//右床尾升降高度
                break;
            case 0x19:
                mode = value[1];//设置模式
                Log.e("sjdhjsdhjsd",  (mode & 0xff)+"");
                break;
            case 0x2B:
                heatType = 0;
                //力感心率数据包
                lHeartBeat = value[1];//左心率
                lBreathFreq = value[2];//左呼吸率
                lSnoreSign = value[3];//左状态位
                rHeartBeat = value[4];//右心率
                rBreathFreq = value[5];//右呼吸率
                rSnoreSign = value[6];//右状态位
                Log.d("力感", "左心率：" + lSnoreSign);
                break;
            case 0x4D:
                heatType = 2;
                //力感心率数据包
                lHeartBeat = value[1];//左心率
                lBreathFreq = value[2];//左呼吸率
                lSnoreSign = value[3];//左状态位
                rHeartBeat = value[4];//右心率
                rBreathFreq = value[5];//右呼吸率
                rSnoreSign = value[6];//右状态位
                Log.d("无心率带", "左心率：" + lSnoreSign+"================"+rSnoreSign+"============="+heatType);
                break;
            case 0x20:
                //云传&量子心率数据包
                heatType = 1;
                lHeartBeat = value[1];//左心率
                lBreathFreq = value[2];//左呼吸率
                lSnoreSign = value[3];//左打鼾标志位
                lSnoreSign_ = value[5];//左床状态位
                rHeartBeat = value[26];//右心率
                rBreathFreq = value[27];//右呼吸率r
                rSnoreSign = value[28];//右打鼾标志位
                rSnoreSign_ = value[30];//右打鼾标志位
                Log.d("云传", "左心率：" + lSnoreSign_);
                break;
            case 0x1a:
                tire_hour = value[1];//自动补气时
                tire_minute = value[2];//自动补气分
                weeks = value[3];//补气星期
                break;
            case 0x1b:
                break;
            case 0x1c:
                /*闹钟的个数*//*
                awake_hour = value[1];//唤醒时
                awake_minute = value[2];//唤醒分
                awake_weeks = value[3];//唤醒星期
                awakeHeight = value[4];//唤醒高度挡位*/
                awake_num_left = value[1];
                awake_num_right = value[2];
                Log.e("星期", "setValue: 星期"+ awake_weeks);
                break;
            case 0x1e:
                l_snoreHeight = value[1];//左床止鼾高度选择
                //左床止鼾灵敏度选择
                l_sensitivity_selection = value[2];
                int num;
                int num1;
                int num2;
                int num3;
                if (value[3] >= 0) {
                    num1 = value[3];
                } else {
                    num1 = value[3] + 256;
                }
                if (value[4] >= 0) {
                    num2 = value[4];
                } else {
                    num2 = value[4] + 256;
                }
                //左床止鼾时间
                l_snore_stopping_time = (num1 * 256 + num2);
//                l_snore_stopping_time = BleUtils.HexToInt(BleUtils.convertDecimalToBinary(value[3] + "") + BleUtils.convertDecimalToBinary(value[4] + ""));
                //右床止鼾高度选择
                r_snoreHeight = value[5];
                //右床止鼾灵敏度选择
                r_sensitivity_selection = value[6];
                if (value[7] >= 0) {
                    num3 = value[7];
                } else {
                    num3 = value[7] + 256;
                }
                if (value[8] >= 0) {
                    num = value[8];
                } else {
                    num = value[8] + 256;
                }
                //右床止鼾时间
                r_snore_stopping_time = (num3 * 256 + num);
//                r_snore_stopping_time = BleUtils.HexToInt(BleUtils.convertDecimalToBinary(value[7] + "") + BleUtils.convertDecimalToBinary(value[8] + ""));
                for (int i = 0; i < value.length; i++) {
                    Log.d("sendSnoreHeight2111", value[i] + "");
                }
                Log.d("sendSnoreHeight2", l_snoreHeight + "============" + l_sensitivity_selection + "============" + l_snore_stopping_time + "============" + r_snoreHeight + "============" + r_sensitivity_selection + "============" + r_snore_stopping_time);
                break;
            case 0x22:
                isWiFiConnSign = value[1];//wifi联网状态
                Log.e("wifiConEvent123321", MSPProtocol.getInstance().getIsWiFiConnSign()+"========");
                if (isWiFiConnSign == 1) {
//                    EventBus.getDefault().post(new WifiConEvent(true));
                } else {
//                    EventBus.getDefault().post(new WifiConEvent(false));
                }
                break;
            case 0x23:
                lPresureCurVal = value[1];//左床气压实时值
                rPresureCurVal = value[2];//右床气压实时值
                lWaistCurVal = value[3];//左腰气压实时值
                rWaistCurVal = value[4];//右腰气压实时值
                Log.d("四路112", "左床气压实时值:" + lPresureCurVal + "右床气压实时值:" + rPresureCurVal + "左腰气压实时值:" + lWaistCurVal + "右腰气压实时值:" + rWaistCurVal);
                break;
            case 0x25:
                curtainGear = value[1];
                Log.d("电视架", "窗帘档位:" + curtainGear);
                break;
            case 0x2d:
                Log.d("设备类型", "" + value[1]);
                break;
            case 0x2e:
                parsePartsValue(value);
                /*devicePartType0 = value[1];//0号从机设备类型
                devicePartLineState0 = value[2];//0号从机在线状态
                //value[13]//1号。。。
                byte[] byteList = new byte[12];
                String hexString = "";
                for (int i = 0; i < 12; i++) {
                    hexString += value[i + 3];
                    byteList[i] = value[i + 3];
                }
                Log.d("8路从机状态", hexString);
                devicePartName0 = formatHexString(byteList, false);*/
                //Log.d("8路从机状态", "devicePartType0=" + devicePartType0 + ",devicePartLineState0=" + devicePartLineState0 + ",devicePartName0=" +decode(devicePartName0));
                /*Log.d("8路从机状态", "devicePartType1=" + value[15] + ",devicePartLineState0=" + value[16]);
                Log.d("8路从机状态", "devicePartType2=" + value[29] + ",devicePartLineState0=" + value[30]);
                Log.d("8路从机状态", "devicePartType3=" + value[43] + ",devicePartLineState0=" + value[44]);
                Log.d("8路从机状态", "devicePartType4=" + value[57] + ",devicePartLineState0=" + value[58]);
                Log.d("8路从机状态", "devicePartType5=" + value[71] + ",devicePartLineState0=" + value[72]);
                Log.d("8路从机状态", "devicePartType6=" + value[85] + ",devicePartLineState0=" + value[86]);
                Log.d("8路从机状态", "devicePartType7=" + value[99] + ",devicePartLineState0=" + value[100]);
                Log.d("8路从机状态", "devicePartType8=" + value[113] + ",devicePartLineState0=" + value[114]);*/
                break;
            case 0x48:
                setPressValP1 = byteArrayToInt(value, 1, true);
                setPressValP2 = byteArrayToInt(value, 3, true);
                setPressValP3 = byteArrayToInt(value, 5, true);
                setPressValP4 = byteArrayToInt(value, 7, true);
                setPressValP5 = byteArrayToInt(value, 9, true);
                setPressValP6 = byteArrayToInt(value, 11, true);
                setPressValP7 = byteArrayToInt(value, 13, true);
                setPressValP8 = byteArrayToInt(value, 15, true);
                Log.d(TAG, "setValue: 设置值1：" + setPressValP1);
                Log.d(TAG, "setValue: 设置值2：" + setPressValP2);
                Log.d(TAG, "setValue: 设置值3：" + setPressValP3);
                Log.d(TAG, "setValue: 设置值4：" + setPressValP4);
                Log.d(TAG, "setValue: 设置值5：" + setPressValP5);
                Log.d(TAG, "setValue: 设置值6：" + setPressValP6);
                Log.d(TAG, "setValue: 设置值7：" + setPressValP7);
                Log.d(TAG, "setValue: 设置值8：" + setPressValP8);

                break;
            case 0x49:
                culPressValP1 = byteArrayToInt(value, 1, true);
                culPressValP2 = byteArrayToInt(value, 3, true);
                culPressValP3 = byteArrayToInt(value, 5, true);
                culPressValP4 = byteArrayToInt(value, 7, true);
                culPressValP5 = byteArrayToInt(value, 9, true);
                culPressValP6 = byteArrayToInt(value, 11, true);
                culPressValP7 = byteArrayToInt(value, 13, true);
                culPressValP8 = byteArrayToInt(value, 15, true);
                lPresureCurVal = byteArrayToInt(value, 1, true); //左床气压实时值
                lWaistCurVal = byteArrayToInt(value, 3, true); //左腰气压实时值
                rPresureCurVal = byteArrayToInt(value, 5, true);//右床气压实时值
                rWaistCurVal = byteArrayToInt(value, 7, true); //右腰气压实时值
                Log.d(TAG, "setValue: 实时值1：" + culPressValP1);
                Log.d(TAG, "setValue: 实时值2：" + culPressValP2);
                Log.d(TAG, "setValue: 实时值3：" + culPressValP3);
                Log.d(TAG, "setValue: 实时值4：" + culPressValP4);
                Log.d(TAG, "setValue: 实时值5：" + culPressValP5);
                Log.d(TAG, "setValue: 实时值6：" + culPressValP6);
                Log.d(TAG, "setValue: 实时值7：" + culPressValP7);
                Log.d(TAG, "setValue: 实时值8：" + culPressValP8);
                culPressFlay1 = byteArrayToInt(value, 32, true);
                culPressFlay2 = byteArrayToInt(value, 33, true);
                culPressFlay3 = byteArrayToInt(value, 34, true);
                culPressFlay4 = byteArrayToInt(value, 35, true);
                culPressFlay5 = byteArrayToInt(value, 36, true);
                culPressFlay6 = byteArrayToInt(value, 37, true);
                culPressFlay7 = byteArrayToInt(value, 38, true);
                culPressFlay8 = byteArrayToInt(value, 39, true);
                culPressFlay9 = byteArrayToInt(value, 40, true);
                Log.d(TAG, "setValue: culPressFlay1：" + culPressFlay1);
                Log.d(TAG, "setValue: culPressFlay2：" + culPressFlay2);
                Log.d(TAG, "setValue: culPressFlay3：" + culPressFlay3);
                Log.d(TAG, "setValue: culPressFlay4：" + culPressFlay4);
                Log.d(TAG, "setValue: culPressFlay5：" + culPressFlay5);
                Log.d(TAG, "setValue: culPressFlay6：" + culPressFlay6);
                Log.d(TAG, "setValue: culPressFlay7：" + culPressFlay7);
                Log.d(TAG, "setValue: culPressFlay8：" + culPressFlay8);
                Log.d(TAG, "setValue: culPressFlay9：" + culPressFlay9);
                acquisitionValP1 = byteArrayToInt(value, 17, true);;
                acquisitionValP2 = byteArrayToInt(value, 19, true);;
                acquisitionValP3 = byteArrayToInt(value, 21, true);;
                acquisitionValP4 = byteArrayToInt(value, 23, true);;
                acquisitionValP5 = byteArrayToInt(value, 25, true);;
                acquisitionValP6 = byteArrayToInt(value, 27, true);;
                acquisitionValP7 = byteArrayToInt(value, 29, true);;
                acquisitionValP8 = byteArrayToInt(value, 31, true);;

                Log.d(TAG, "setValue: acquisitionValP1：" + acquisitionValP1+"================="+setPressValP1);
                break;
            case 0x4A:
//                setLeftAuto = value[5];
                break;
            case 0x4B:
//                setRightAuto = value[5];
                break;
            case 0x4C:
                break;
            case 0x54:
                int num11;
                int num22;
                int num33;
                int num44;
                if (value[3] >= 0) {
                    num11 = value[3];
                } else {
                    num11 = value[3] + 256;
                }
                if (value[4] >= 0) {
                    num22 = value[4];
                } else {
                    num22 = value[4] + 256;
                }
                time_remaining_1 = num11 * 256 + num22;
                Log.d("八路（3.0）充气强度带气泵功率控制状态", 2 + "剩余时间：" + num11 * 256 + num22);
                if (value[7] >= 0) {
                    num33 = value[7];
                } else {
                    num33 = value[7] + 256;
                }
                if (value[8] >= 0) {
                    num44 = value[8];
                } else {
                    num44 = value[8] + 256;
                }
                time_remaining_r = num33 * 256 + num44;
                Log.d("八路（3.0）充气强度带气泵功率控制状态", 4 + "剩余时间：" + time_remaining_r);
                break;
            case 0x5B:
                int i = value[1];
                if (i == 1) {
//                    ToastUtil.showMessage("时间同步成功");
                } else {
//                    ToastUtil.showMessage("时间同步失败");
                }
                Log.d("时间同步的状态",i+"    "+value[1]);
                break;
            case 0x5C:
                culPressStatus1 = byteArrayToInt(value, 1, true);
                culPressStatus2 = byteArrayToInt(value, 2, true);
                culPressStatus3 = byteArrayToInt(value, 3, true);
                culPressStatus4 = byteArrayToInt(value, 4, true);
                culPressStatus5 = byteArrayToInt(value, 5, true);
                culPressStatus6 = byteArrayToInt(value, 6, true);
                culPressStatus7 = byteArrayToInt(value, 7, true);
                culPressStatus8 = byteArrayToInt(value, 8, true);
                culPressStatus9 = value[9];
                culPressStatus10 =value[10];
                Log.d("气泵的状态数据", "setValue: culPressStatus1：" + culPressStatus1);
                Log.d("气泵的状态数据", "setValue: culPressStatus2：" + culPressStatus2);
                Log.d("气泵的状态数据", "setValue: culPressStatus3：" + culPressStatus3);
                Log.d("气泵的状态数据", "setValue: culPressStatus4：" + culPressStatus4);
                Log.d("气泵的状态数据", "setValue: culPressStatus5：" + culPressStatus5);
                Log.d("气泵的状态数据", "setValue: culPressStatus6：" + culPressStatus6);
                Log.d("气泵的状态数据", "setValue: culPressStatus7：" + culPressStatus7);
                Log.d("气泵的状态数据", "setValue: culPressStatus8：" + culPressStatus8);
                Log.d("提示语句数据12313",setLeftAuto+"====="+culPressStatus9);
                getOverallCulPressValP1 = value[1];
                getOverallCulPressValP2 = value[2];
                getOverallCulPressValP3 = value[3];
                getOverallCulPressValP4 = value[4];
                getOverallCulPressValP5 = value[5];
                getOverallCulPressValP6 = value[6];
                getOverallCulPressValP7 = value[7];
                getOverallCulPressValP8 = value[8];

                Log.d("sdhsdsd1",getOverallCulPressValP1+"");
                Log.d("sdhsdsd2",getOverallCulPressValP2+"");
                Log.d("sdhsdsd3",getOverallCulPressValP3+"");
                Log.d("sdhsdsd4",getOverallCulPressValP4+"");
                Log.d("sdhsdsd5",getOverallCulPressValP5+"");
                Log.d("sdhsdsd6",getOverallCulPressValP6+"");
                Log.d("sdhsdsd7",getOverallCulPressValP7+"");
                Log.d("sdhsdsd8",getOverallCulPressValP8+"");
                break;
            case 0x52:
                for (int j = 0; j < value.length; j++) {
                    Log.d("52返回数据"+j,value[j]+"");
                }
                getMassage1 = value[3];
                getMassage2 = value[4];
                getMassage3 = value[7];
                getMassage4 = value[8];
                break;
            case 0x55:
                //左边模式
                left_mode = value[1];
                //右边模式
                right_mode = value[2];
                //左床头设置挡位
                left_bedside_tou = value[7];
                //左床尾设置挡位
                left_bedside_wei = value[8];
                //右床头设置挡位
                right_bedside_tou = value[9];
                //右床尾设置挡位
                right_bedside_wei = value[10];
                Log.d("55的模式", "左边模式"+ left_mode +"右边模式"+ right_mode +"左床头设置挡位"+ left_bedside_tou
                        +"左床尾设置挡位"+ left_bedside_wei +"右床头设置挡位"+ right_bedside_tou +"右床尾设置挡位"+ right_bedside_wei);
                break;
            case 0x14:
                zhuban="";
                for (int j = 0; j < value.length; j++) {
                    zhuban=zhuban+value[j]+"";
                    Log.d("主控版本号"+j,value[j]+""+zhuban);
                }
                break;
            case 0x15:
                bleBan = "";
                for (int j = 0; j < value.length; j++) {
                    bleBan=bleBan+value[j]+"";
                    Log.d("蓝牙版本号"+j,value[j]+""+bleBan);
                }
                break;
            case 0x5E:
                tiyan = value[1];
                break;
            case 0x61:
                setLeftAuto=value[1];
                setRightAuto=value[2];
                Log.e("当前设置的模式", "setValue: 左:"+value[1]+"=============右："+value[2]);
                break;
            case 0x62:
                setRenWen = value[1];
                dianZong = value[2];
                Log.e("人文观怀", "setValue: "+value[1]);
                break;
        }

    }

    /**
     * bytes数组的头两位转为 int
     *
     * @param bytes 数组
     * @param start 起始位
     * @param isBig true:大端模式  false:小端模式
     * @return 短整型
     */
    public int byteArrayToInt(byte[] bytes, int start, boolean isBig) {

        int value = 0;
        if (isBig) {
            value += (bytes[start] & 0x000000FF) << 8;
            value += (bytes[start + 1] & 0x000000FF);
        } else {
            value += (bytes[start] & 0x000000FF);
            value += (bytes[start + 1] & 0x000000FF) << 8;
        }
        if (value > 30000) {
            value = 0;
        }
        return value;
    }

    private int devicePartType0, devicePartType1, devicePartType2, devicePartType3, devicePartType4, devicePartType5, devicePartType6, devicePartType7;
    private int devicePartLineState0, devicePartLineState1, devicePartLineState2, devicePartLineState3, devicePartLineState4, devicePartLineState5, devicePartLineState6, devicePartLineState7;
    private String devicePartName0, devicePartName1, devicePartName2, devicePartName3, devicePartName4, devicePartName5, devicePartName6, devicePartName7;

    private void parsePartsValue(byte[] value) {
        devicePartType0 = value[1];//0号从机设备类型
        devicePartLineState0 = value[2];//0号从机在线状态
        byte[] byteList0 = new byte[12];
        for (int i = 0; i < 12; i++) {
            byteList0[i] = value[i + 3];
        }
        devicePartName0 = decode(formatHexString(byteList0, false));//0号从机名称

        devicePartType1 = value[15];//1号从机设备类型
        devicePartLineState1 = value[16];//1号从机在线状态
        byte[] byteList1 = new byte[12];
        for (int i = 0; i < 12; i++) {
            byteList1[i] = value[i + 17];
        }
        devicePartName1 = decode(formatHexString(byteList1, false));

        devicePartType2 = value[29];//2号从机设备类型
        devicePartLineState2 = value[30];//2号从机在线状态
        byte[] byteList2 = new byte[12];
        for (int i = 0; i < 12; i++) {
            byteList2[i] = value[i + 31];
        }
        devicePartName2 = decode(formatHexString(byteList2, false));

        devicePartType3 = value[43];//3号从机设备类型
        devicePartLineState3 = value[44];//3号从机在线状态
        byte[] byteList3 = new byte[12];
        for (int i = 0; i < 12; i++) {
            byteList3[i] = value[i + 45];
        }
        devicePartName3 = decode(formatHexString(byteList3, false));

        devicePartType4 = value[57];//4号从机设备类型
        devicePartLineState4 = value[58];//4号从机在线状态
        byte[] byteList4 = new byte[12];
        for (int i = 0; i < 12; i++) {
            byteList4[i] = value[i + 59];
        }
        devicePartName4 = decode(formatHexString(byteList4, false));

        devicePartType5 = value[71];//5号从机设备类型
        devicePartLineState5 = value[72];//5号从机在线状态
        byte[] byteList5 = new byte[12];
        for (int i = 0; i < 12; i++) {
            byteList5[i] = value[i + 73];
        }
        devicePartName5 = decode(formatHexString(byteList5, false));

        devicePartType6 = value[85];//6号从机设备类型
        devicePartLineState6 = value[86];//6号从机在线状态
        byte[] byteList6 = new byte[12];
        for (int i = 0; i < 12; i++) {
            byteList6[i] = value[i + 87];
        }
        devicePartName6 = decode(formatHexString(byteList6, false));

        devicePartType7 = value[99];//7号从机设备类型
        devicePartLineState7 = value[100];//7号从机在线状态
        byte[] byteList7 = new byte[12];
        for (int i = 0; i < 12; i++) {
            byteList7[i] = value[i + 101];
        }
        devicePartName7 = decode(formatHexString(byteList7, false));
    }

    public static String formatHexString(byte[] data, boolean addSpace) {
        if (data == null || data.length < 1)
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            String hex = Integer.toHexString(data[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
            if (addSpace)
                sb.append(" ");
        }
        return sb.toString().trim();
    }
    /*
     * 将16进制数字解码成字符串,适用于所有字符（包括中文）
     */
    public static String decode(String bytes) {
        String hexString = "0123456789abcdef";
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        //将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray());
    }

    //高位-->低位
    private int bytesToInt(List<Byte> byteList) {
        int value = 0;
        for (int i = 0; i < byteList.size(); i++) {
            byte val = byteList.get(byteList.size() - i - 1);
            value = (val & 0xff) << (8 * i);
        }
        return value;
    }

    //异或较验值：从第2个字节开始至最后倒数第2个字节，进行异或。如：C5，03，04，05，XX。中XX=03^04^05
    private int checkSum(List<Byte> data, int lenth) {
        byte checksum = 0;
        for (int i = 1; i < lenth - 2; i++) {
            checksum ^= data.get(i);
        }
        return checksum & 0xff;
    }

    //-----------------------------------测试-----------------------
    public byte getSetTimeOk() {
        return setTimeOk;
    }

    public void setSetTimeOk(byte setTimeOk) {
        this.setTimeOk = setTimeOk;
    }

    public byte getOkcall() {
        return okcall;
    }

    public void setOkcall(byte okcall) {
        this.okcall = okcall;
    }


    //---------------------------------测试---------------------


    public int getTime_remaining_r() {
        return time_remaining_r;
    }

    public int getTime_remaining_1() {
        return time_remaining_1;
    }

    public byte getlWaistSetVal() {
        return lWaistSetVal;
    }

    public void setlWaistSetVal(byte lWaistSetVal) {
        this.lWaistSetVal = lWaistSetVal;
    }

    public byte getrWaistSetVal() {
        return rWaistSetVal;
    }

    public void setrWaistSetVal(byte rWaistSetVal) {
        this.rWaistSetVal = rWaistSetVal;
    }

    public byte getlWaistMemVal() {
        return lWaistMemVal;
    }

    public void setlWaistMemVal(byte lWaistMemVal) {
        this.lWaistMemVal = lWaistMemVal;
    }

    public byte getrWaistMemVal() {
        return rWaistMemVal;
    }

    public void setrWaistMemVal(byte rWaistMemVal) {
        this.rWaistMemVal = rWaistMemVal;
    }

    public int getlWaistCurVal() {
        return lWaistCurVal;
    }

    public void setlWaistCurVal(byte lWaistCurVal) {
        this.lWaistCurVal = lWaistCurVal;
    }

    public int getrWaistCurVal() {
        return rWaistCurVal;
    }

    public void setrWaistCurVal(byte rWaistCurVal) {
        this.rWaistCurVal = rWaistCurVal;
    }

    public byte getAwake_hour() {
        return awake_hour;
    }

    public void setAwake_hour(byte awake_hour) {
        this.awake_hour = awake_hour;
    }

    public byte getAwake_minute() {
        return awake_minute;
    }

    public void setAwake_minute(byte awake_minute) {
        this.awake_minute = awake_minute;
    }

    public byte getAwake_weeks() {
        return awake_weeks;
    }

    public void setAwake_weeks(byte awake_weeks) {
        this.awake_weeks = awake_weeks;
    }

    public boolean isDataTrdRun() {
        return isDataTrdRun;
    }

    public void setDataTrdRun(boolean dataTrdRun) {
        isDataTrdRun = dataTrdRun;
    }

    public byte getHeatLevel() {
        return heatLevel;
    }

    public byte getlPresureSetVal() {
        return lPresureSetVal;
    }

    public byte getrPresureSetVal() {
        return rPresureSetVal;
    }

    public byte getlPresureMemVal() {
        return lPresureMemVal;
    }

    public byte getrPresureMemVal() {
        return rPresureMemVal;
    }

    public int getlPresureCurVal() {
        return lPresureCurVal;
    }

    public int getrPresureCurVal() {
        return rPresureCurVal;
    }

    public byte getIsWiFiConnSign() {
        return isWiFiConnSign;
    }

    public byte getlHeartBeat() {
        return lHeartBeat;
    }

    public byte getlBreathFreq() {
        return lBreathFreq;
    }

    public byte getlSnoreSign() {
        return lSnoreSign;
    }

    public byte getlBodyMoveByMinuteSign() {
        return lBodyMoveByMinuteSign;
    }

    public byte getlBodyMoveBySecondSign() {
        return lBodyMoveBySecondSign;
    }

    //左体动值，共20个，按高低位顺序拼接,低位占5个字节。
    public float getlBodyMoveVal() {
        return (lBodyMoveValH << 5) | lBodyMoveValL;
    }

    public byte getrHeartBeat() {
        return rHeartBeat;
    }

    public byte getrBreathFreq() {
        return rBreathFreq;
    }

    public byte getrSnoreSign() {
        return rSnoreSign;
    }

    public byte getrBodyMoveByMinuteSign() {
        return rBodyMoveByMinuteSign;
    }

    public byte getrBodyMoveBySecondSign() {
        return rBodyMoveBySecondSign;
    }

    //右体动值，共20个，按高低位顺序拼接,低位占15个字节。
    public float getrBodyMoveVal() {
        return (rBodyMoveValH << 15) | rBodyMoveValL;
    }

    public byte getHigh1() {
        return high1;
    }

    public byte getHigh2() {
        return high2;
    }

    public byte getHigh3() {
        return high3;
    }

    public byte getHigh4() {
        return high4;
    }

    public byte getOneLevel() {
        return oneLevel;
    }

    public byte getTwoLevel() {
        return twoLevel;
    }

    public byte getThreeLevel() {
        return threeLevel;
    }

    public byte getMode() {
        return mode;
    }

    public byte getTire_hour() {
        return tire_hour;
    }

    public byte getTire_minute() {
        return tire_minute;
    }

    public byte getWeeks() {
        return weeks;
    }

    public byte getLiftTime() {
        return liftTime;
    }

    public byte getL_snoreHeight() {
        return l_snoreHeight;
    }

    public void setL_snoreHeight(byte l_snoreHeight) {
        this.l_snoreHeight = l_snoreHeight;
    }

    public byte getL_sensitivity_selection() {
        return l_sensitivity_selection;
    }

    public void setL_sensitivity_selection(byte l_sensitivity_selection) {
        this.l_sensitivity_selection = l_sensitivity_selection;
    }

    public int getL_snore_stopping_time() {
        return l_snore_stopping_time;
    }

    public void setL_snore_stopping_time(int l_snore_stopping_time) {
        this.l_snore_stopping_time = l_snore_stopping_time;
    }

    public byte getR_snoreHeight() {
        return r_snoreHeight;
    }

    public void setR_snoreHeight(byte r_snoreHeight) {
        this.r_snoreHeight = r_snoreHeight;
    }

    public byte getR_sensitivity_selection() {
        return r_sensitivity_selection;
    }

    public void setR_sensitivity_selection(byte r_sensitivity_selection) {
        this.r_sensitivity_selection = r_sensitivity_selection;
    }

    public int getR_snore_stopping_time() {
        return r_snore_stopping_time;
    }

    public void setR_snore_stopping_time(int r_snore_stopping_time) {
        this.r_snore_stopping_time = r_snore_stopping_time;
    }

    public byte getAwakeHeight() {
        return awakeHeight;
    }

    public void setAwakeHeight(byte awakeHeight) {
        this.awakeHeight = awakeHeight;
    }

    public void setSetState(byte setState) {
        this.setState = setState;
    }

    public byte getSetState() {
        return setState;
    }

    public byte getCurtainGear() {
        return curtainGear;
    }

    public int getDevicePartType0() {
        return devicePartType0;
    }

    public void setDevicePartType0(int devicePartType0) {
        this.devicePartType0 = devicePartType0;
    }

    public int getDevicePartLineState0() {
        return devicePartLineState0;
    }

    public void setDevicePartLineState0(int devicePartLineState0) {
        this.devicePartLineState0 = devicePartLineState0;
    }

    public String getDevicePartName0() {
        return devicePartName0;
    }

    public void setDevicePartName0(String devicePartName0) {
        this.devicePartName0 = devicePartName0;
    }

    public int getDevicePartType1() {
        return devicePartType1;
    }

    public void setDevicePartType1(int devicePartType1) {
        this.devicePartType1 = devicePartType1;
    }

    public int getDevicePartType2() {
        return devicePartType2;
    }

    public void setDevicePartType2(int devicePartType2) {
        this.devicePartType2 = devicePartType2;
    }

    public int getDevicePartType3() {
        return devicePartType3;
    }

    public void setDevicePartType3(int devicePartType3) {
        this.devicePartType3 = devicePartType3;
    }

    public int getDevicePartType4() {
        return devicePartType4;
    }

    public void setDevicePartType4(int devicePartType4) {
        this.devicePartType4 = devicePartType4;
    }

    public int getDevicePartType5() {
        return devicePartType5;
    }

    public void setDevicePartType5(int devicePartType5) {
        this.devicePartType5 = devicePartType5;
    }

    public int getDevicePartType6() {
        return devicePartType6;
    }

    public void setDevicePartType6(int devicePartType6) {
        this.devicePartType6 = devicePartType6;
    }

    public int getDevicePartType7() {
        return devicePartType7;
    }

    public void setDevicePartType7(int devicePartType7) {
        this.devicePartType7 = devicePartType7;
    }

    public int getDevicePartLineState1() {
        return devicePartLineState1;
    }

    public void setDevicePartLineState1(int devicePartLineState1) {
        this.devicePartLineState1 = devicePartLineState1;
    }

    public int getDevicePartLineState2() {
        return devicePartLineState2;
    }

    public void setDevicePartLineState2(int devicePartLineState2) {
        this.devicePartLineState2 = devicePartLineState2;
    }

    public int getDevicePartLineState3() {
        return devicePartLineState3;
    }

    public void setDevicePartLineState3(int devicePartLineState3) {
        this.devicePartLineState3 = devicePartLineState3;
    }

    public int getDevicePartLineState4() {
        return devicePartLineState4;
    }

    public void setDevicePartLineState4(int devicePartLineState4) {
        this.devicePartLineState4 = devicePartLineState4;
    }

    public int getDevicePartLineState5() {
        return devicePartLineState5;
    }

    public void setDevicePartLineState5(int devicePartLineState5) {
        this.devicePartLineState5 = devicePartLineState5;
    }

    public int getDevicePartLineState6() {
        return devicePartLineState6;
    }

    public void setDevicePartLineState6(int devicePartLineState6) {
        this.devicePartLineState6 = devicePartLineState6;
    }

    public int getDevicePartLineState7() {
        return devicePartLineState7;
    }

    public void setDevicePartLineState7(int devicePartLineState7) {
        this.devicePartLineState7 = devicePartLineState7;
    }

    public String getDevicePartName1() {
        return devicePartName1;
    }

    public void setDevicePartName1(String devicePartName1) {
        this.devicePartName1 = devicePartName1;
    }

    public String getDevicePartName2() {
        return devicePartName2;
    }

    public void setDevicePartName2(String devicePartName2) {
        this.devicePartName2 = devicePartName2;
    }

    public String getDevicePartName3() {
        return devicePartName3;
    }

    public void setDevicePartName3(String devicePartName3) {
        this.devicePartName3 = devicePartName3;
    }

    public String getDevicePartName4() {
        return devicePartName4;
    }

    public void setDevicePartName4(String devicePartName4) {
        this.devicePartName4 = devicePartName4;
    }

    public String getDevicePartName5() {
        return devicePartName5;
    }

    public void setDevicePartName5(String devicePartName5) {
        this.devicePartName5 = devicePartName5;
    }

    public String getDevicePartName6() {
        return devicePartName6;
    }

    public void setDevicePartName6(String devicePartName6) {
        this.devicePartName6 = devicePartName6;
    }

    public String getDevicePartName7() {
        return devicePartName7;
    }

    public void setDevicePartName7(String devicePartName7) {
        this.devicePartName7 = devicePartName7;
    }

    public int getSetPressValP1() {
        return setPressValP1;
    }

    public int getSetPressValP2() {
        return setPressValP2;
    }

    public int getSetPressValP3() {
        return setPressValP3;
    }

    public int getSetPressValP4() {
        return setPressValP4;
    }

    public int getSetPressValP5() {
        return setPressValP5;
    }

    public int getSetPressValP6() {
        return setPressValP6;
    }

    public int getSetPressValP7() {
        return setPressValP7;
    }

    public int getSetPressValP8() {
        return setPressValP8;
    }

    public int getCulPressValP1() {
        return culPressValP1;
    }

    public int getCulPressValP2() {
        return culPressValP2;
    }

    public int getCulPressValP3() {
        return culPressValP3;
    }

    public int getCulPressValP4() {
        return culPressValP4;
    }

    public int getCulPressValP5() {
        return culPressValP5;
    }

    public int getCulPressValP6() {
        return culPressValP6;
    }

    public int getCulPressValP7() {
        return culPressValP7;
    }

    public int getCulPressValP8() {
        return culPressValP8;
    }

    public int getSetLeftAuto() {
        return setLeftAuto;
    }

    public int getSetRightAuto() {
        return setRightAuto;
    }

    public int getCulValP2() {
        return culValP2;
    }

    public int getCulValP3() {
        return culValP3;
    }

    public int getCulValP4() {
        return culValP4;
    }

    public int getCulValP5() {
        return culValP5;
    }

    public int getCulValP6() {
        return culValP6;
    }

    public int getCulValP7() {
        return culValP7;
    }

    public int getCulValP8() {
        return culValP8;
    }

    public int getSetValP2() {
        return setValP2;
    }

    public int getSetValP3() {
        return setValP3;
    }

    public int getSetValP4() {
        return setValP4;
    }

    public int getSetValP5() {
        return setValP5;
    }

    public int getSetValP6() {
        return setValP6;
    }

    public int getSetValP7() {
        return setValP7;
    }

    public int getSetValP8() {
        return setValP8;
    }

    public int getCulPressFlay1() {
        return culPressFlay1;
    }

    public void setCulPressFlay1(int culPressFlay1) {
        this.culPressFlay1 = culPressFlay1;
    }

    public int getCulPressFlay2() {
        return culPressFlay2;
    }

    public void setCulPressFlay2(int culPressFlay2) {
        this.culPressFlay2 = culPressFlay2;
    }

    public int getCulPressFlay3() {
        return culPressFlay3;
    }

    public void setCulPressFlay3(int culPressFlay3) {
        this.culPressFlay3 = culPressFlay3;
    }

    public int getCulPressFlay4() {
        return culPressFlay4;
    }

    public void setCulPressFlay4(int culPressFlay4) {
        this.culPressFlay4 = culPressFlay4;
    }

    public int getCulPressFlay5() {
        return culPressFlay5;
    }

    public void setCulPressFlay5(int culPressFlay5) {
        this.culPressFlay5 = culPressFlay5;
    }

    public int getCulPressFlay6() {
        return culPressFlay6;
    }

    public void setCulPressFlay6(int culPressFlay6) {
        this.culPressFlay6 = culPressFlay6;
    }

    public int getCulPressFlay7() {
        return culPressFlay7;
    }

    public void setCulPressFlay7(int culPressFlay7) {
        this.culPressFlay7 = culPressFlay7;
    }

    public int getCulPressFlay8() {
        return culPressFlay8;
    }

    public void setCulPressFlay8(int culPressFlay8) {
        this.culPressFlay8 = culPressFlay8;
    }

    public int getCulPressFlay9() {
        return culPressFlay9;
    }

    public void setCulPressFlay9(int culPressFlay9) {
        this.culPressFlay9 = culPressFlay9;
    }

    public int getCulPressStatus1() {
        return culPressStatus1;
    }

    public void setCulPressStatus1(int culPressStatus1) {
        this.culPressStatus1 = culPressStatus1;
    }

    public int getCulPressStatus2() {
        return culPressStatus2;
    }

    public void setCulPressStatus2(int culPressStatus2) {
        this.culPressStatus2 = culPressStatus2;
    }

    public int getCulPressStatus3() {
        return culPressStatus3;
    }

    public void setCulPressStatus3(int culPressStatus3) {
        this.culPressStatus3 = culPressStatus3;
    }

    public int getCulPressStatus4() {
        return culPressStatus4;
    }

    public void setCulPressStatus4(int culPressStatus4) {
        this.culPressStatus4 = culPressStatus4;
    }

    public int getCulPressStatus5() {
        return culPressStatus5;
    }

    public void setCulPressStatus5(int culPressStatus5) {
        this.culPressStatus5 = culPressStatus5;
    }

    public int getCulPressStatus6() {
        return culPressStatus6;
    }

    public void setCulPressStatus6(int culPressStatus6) {
        this.culPressStatus6 = culPressStatus6;
    }

    public int getCulPressStatus7() {
        return culPressStatus7;
    }

    public void setCulPressStatus7(int culPressStatus7) {
        this.culPressStatus7 = culPressStatus7;
    }

    public int getCulPressStatus8() {
        return culPressStatus8;
    }

    public void setCulPressStatus8(int culPressStatus8) {
        this.culPressStatus8 = culPressStatus8;
    }

    public int getGetMassage1() {
        return getMassage1;
    }

    public void setGetMassage1(int getMassage1) {
        this.getMassage1 = getMassage1;
    }

    public int getGetMassage2() {
        return getMassage2;
    }

    public void setGetMassage2(int getMassage2) {
        this.getMassage2 = getMassage2;
    }

    public int getGetMassage3() {
        return getMassage3;
    }

    public void setGetMassage3(int getMassage3) {
        this.getMassage3 = getMassage3;
    }

    public int getGetMassage4() {
        return getMassage4;
    }

    public void setGetMassage4(int getMassage4) {
        this.getMassage4 = getMassage4;
    }

    public int getCulPressStatus9() {
        return culPressStatus9;
    }

    public void setCulPressStatus9(int culPressStatus9) {
        this.culPressStatus9 = culPressStatus9;
    }

    public int getCulPressStatus10() {
        return culPressStatus10;
    }

    public void setCulPressStatus10(int culPressStatus10) {
        this.culPressStatus10 = culPressStatus10;
    }

    public int getLeft_mode() {
        return left_mode;
    }

    public void setLeft_mode(int left_mode) {
        this.left_mode = left_mode;
    }

    public int getRight_mode() {
        return right_mode;
    }

    public void setRight_mode(int right_mode) {
        this.right_mode = right_mode;
    }

    public int getLeft_bedside_tou() {
        return left_bedside_tou;
    }

    public void setLeft_bedside_tou(int left_bedside_tou) {
        this.left_bedside_tou = left_bedside_tou;
    }

    public int getLeft_bedside_wei() {
        return left_bedside_wei;
    }

    public void setLeft_bedside_wei(int left_bedside_wei) {
        this.left_bedside_wei = left_bedside_wei;
    }

    public int getRight_bedside_tou() {
        return right_bedside_tou;
    }

    public void setRight_bedside_tou(int right_bedside_tou) {
        this.right_bedside_tou = right_bedside_tou;
    }

    public int getRight_bedside_wei() {
        return right_bedside_wei;
    }

    public void setRight_bedside_wei(int right_bedside_wei) {
        this.right_bedside_wei = right_bedside_wei;
    }

    public String getZhuban() {
        return zhuban;
    }

    public void setZhuban(String zhuban) {
        this.zhuban = zhuban;
    }

    public String getBleBan() {
        return bleBan;
    }

    public void setBleBan(String bleBan) {
        this.bleBan = bleBan;
    }

    public int getTiyan() {
        return tiyan;
    }

    public void setTiyan(int tiyan) {
        this.tiyan = tiyan;
    }

    public int getGetOverallCulPressValP1() {
        return getOverallCulPressValP1;
    }

    public void setGetOverallCulPressValP1(int getOverallCulPressValP1) {
        this.getOverallCulPressValP1 = getOverallCulPressValP1;
    }

    public int getGetOverallCulPressValP2() {
        return getOverallCulPressValP2;
    }

    public void setGetOverallCulPressValP2(int getOverallCulPressValP2) {
        this.getOverallCulPressValP2 = getOverallCulPressValP2;
    }

    public int getGetOverallCulPressValP3() {
        return getOverallCulPressValP3;
    }

    public void setGetOverallCulPressValP3(int getOverallCulPressValP3) {
        this.getOverallCulPressValP3 = getOverallCulPressValP3;
    }

    public int getGetOverallCulPressValP4() {
        return getOverallCulPressValP4;
    }

    public void setGetOverallCulPressValP4(int getOverallCulPressValP4) {
        this.getOverallCulPressValP4 = getOverallCulPressValP4;
    }

    public int getGetOverallCulPressValP5() {
        return getOverallCulPressValP5;
    }

    public void setGetOverallCulPressValP5(int getOverallCulPressValP5) {
        this.getOverallCulPressValP5 = getOverallCulPressValP5;
    }

    public int getGetOverallCulPressValP6() {
        return getOverallCulPressValP6;
    }

    public void setGetOverallCulPressValP6(int getOverallCulPressValP6) {
        this.getOverallCulPressValP6 = getOverallCulPressValP6;
    }

    public int getGetOverallCulPressValP7() {
        return getOverallCulPressValP7;
    }

    public void setGetOverallCulPressValP7(int getOverallCulPressValP7) {
        this.getOverallCulPressValP7 = getOverallCulPressValP7;
    }

    public int getGetOverallCulPressValP8() {
        return getOverallCulPressValP8;
    }

    public void setGetOverallCulPressValP8(int getOverallCulPressValP8) {
        this.getOverallCulPressValP8 = getOverallCulPressValP8;
    }

    public static String getTAG() {
        return TAG;
    }

    public static MSPProtocol getmSPProtocol() {
        return mSPProtocol;
    }

    public List<Byte> getDataList() {
        return dataList;
    }

    public void setDataList(List<Byte> dataList) {
        this.dataList = dataList;
    }

    public Thread getHandleDataTrd() {
        return handleDataTrd;
    }

    public void setHandleDataTrd(Thread handleDataTrd) {
        this.handleDataTrd = handleDataTrd;
    }

    public void setHeatLevel(byte heatLevel) {
        this.heatLevel = heatLevel;
    }

    public void setlPresureSetVal(byte lPresureSetVal) {
        this.lPresureSetVal = lPresureSetVal;
    }

    public void setrPresureSetVal(byte rPresureSetVal) {
        this.rPresureSetVal = rPresureSetVal;
    }

    public void setlPresureMemVal(byte lPresureMemVal) {
        this.lPresureMemVal = lPresureMemVal;
    }

    public void setrPresureMemVal(byte rPresureMemVal) {
        this.rPresureMemVal = rPresureMemVal;
    }

    public void setlPresureCurVal(int lPresureCurVal) {
        this.lPresureCurVal = lPresureCurVal;
    }

    public void setrPresureCurVal(int rPresureCurVal) {
        this.rPresureCurVal = rPresureCurVal;
    }

    public void setlWaistCurVal(int lWaistCurVal) {
        this.lWaistCurVal = lWaistCurVal;
    }

    public void setrWaistCurVal(int rWaistCurVal) {
        this.rWaistCurVal = rWaistCurVal;
    }

    public void setIsWiFiConnSign(byte isWiFiConnSign) {
        this.isWiFiConnSign = isWiFiConnSign;
    }

    public byte getlSnoreSign_() {
        return lSnoreSign_;
    }

    public void setlSnoreSign_(byte lSnoreSign_) {
        this.lSnoreSign_ = lSnoreSign_;
    }

    public void setlHeartBeat(byte lHeartBeat) {
        this.lHeartBeat = lHeartBeat;
    }

    public void setlBreathFreq(byte lBreathFreq) {
        this.lBreathFreq = lBreathFreq;
    }

    public int getHeatType() {
        return heatType;
    }

    public void setHeatType(int heatType) {
        this.heatType = heatType;
    }

    public void setlSnoreSign(byte lSnoreSign) {
        this.lSnoreSign = lSnoreSign;
    }

    public void setlBodyMoveByMinuteSign(byte lBodyMoveByMinuteSign) {
        this.lBodyMoveByMinuteSign = lBodyMoveByMinuteSign;
    }

    public void setlBodyMoveBySecondSign(byte lBodyMoveBySecondSign) {
        this.lBodyMoveBySecondSign = lBodyMoveBySecondSign;
    }

    public int getlBodyMoveValH() {
        return lBodyMoveValH;
    }

    public void setlBodyMoveValH(int lBodyMoveValH) {
        this.lBodyMoveValH = lBodyMoveValH;
    }

    public int getlBodyMoveValL() {
        return lBodyMoveValL;
    }

    public void setlBodyMoveValL(int lBodyMoveValL) {
        this.lBodyMoveValL = lBodyMoveValL;
    }

    public void setrHeartBeat(byte rHeartBeat) {
        this.rHeartBeat = rHeartBeat;
    }

    public void setrBreathFreq(byte rBreathFreq) {
        this.rBreathFreq = rBreathFreq;
    }

    public byte getrSnoreSign_() {
        return rSnoreSign_;
    }

    public void setrSnoreSign_(byte rSnoreSign_) {
        this.rSnoreSign_ = rSnoreSign_;
    }

    public void setrSnoreSign(byte rSnoreSign) {
        this.rSnoreSign = rSnoreSign;
    }

    public void setrBodyMoveByMinuteSign(byte rBodyMoveByMinuteSign) {
        this.rBodyMoveByMinuteSign = rBodyMoveByMinuteSign;
    }

    public void setrBodyMoveBySecondSign(byte rBodyMoveBySecondSign) {
        this.rBodyMoveBySecondSign = rBodyMoveBySecondSign;
    }

    public int getrBodyMoveValH() {
        return rBodyMoveValH;
    }

    public void setrBodyMoveValH(int rBodyMoveValH) {
        this.rBodyMoveValH = rBodyMoveValH;
    }

    public int getrBodyMoveValL() {
        return rBodyMoveValL;
    }

    public void setrBodyMoveValL(int rBodyMoveValL) {
        this.rBodyMoveValL = rBodyMoveValL;
    }

    public void setHigh1(byte high1) {
        this.high1 = high1;
    }

    public void setHigh2(byte high2) {
        this.high2 = high2;
    }

    public void setHigh3(byte high3) {
        this.high3 = high3;
    }

    public void setHigh4(byte high4) {
        this.high4 = high4;
    }

    public void setOneLevel(byte oneLevel) {
        this.oneLevel = oneLevel;
    }

    public void setTwoLevel(byte twoLevel) {
        this.twoLevel = twoLevel;
    }

    public void setThreeLevel(byte threeLevel) {
        this.threeLevel = threeLevel;
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }

    public void setTire_hour(byte tire_hour) {
        this.tire_hour = tire_hour;
    }

    public void setTire_minute(byte tire_minute) {
        this.tire_minute = tire_minute;
    }

    public void setWeeks(byte weeks) {
        this.weeks = weeks;
    }

    public void setLiftTime(byte liftTime) {
        this.liftTime = liftTime;
    }

    public void setCurtainGear(byte curtainGear) {
        this.curtainGear = curtainGear;
    }

    public void setSetPressValP1(int setPressValP1) {
        this.setPressValP1 = setPressValP1;
    }

    public void setSetPressValP2(int setPressValP2) {
        this.setPressValP2 = setPressValP2;
    }

    public void setSetPressValP3(int setPressValP3) {
        this.setPressValP3 = setPressValP3;
    }

    public void setSetPressValP4(int setPressValP4) {
        this.setPressValP4 = setPressValP4;
    }

    public void setSetPressValP5(int setPressValP5) {
        this.setPressValP5 = setPressValP5;
    }

    public void setSetPressValP6(int setPressValP6) {
        this.setPressValP6 = setPressValP6;
    }

    public void setSetPressValP7(int setPressValP7) {
        this.setPressValP7 = setPressValP7;
    }

    public void setSetPressValP8(int setPressValP8) {
        this.setPressValP8 = setPressValP8;
    }

    public void setCulPressValP1(int culPressValP1) {
        this.culPressValP1 = culPressValP1;
    }

    public void setCulPressValP2(int culPressValP2) {
        this.culPressValP2 = culPressValP2;
    }

    public void setCulPressValP3(int culPressValP3) {
        this.culPressValP3 = culPressValP3;
    }

    public void setCulPressValP4(int culPressValP4) {
        this.culPressValP4 = culPressValP4;
    }

    public void setCulPressValP5(int culPressValP5) {
        this.culPressValP5 = culPressValP5;
    }

    public void setCulPressValP6(int culPressValP6) {
        this.culPressValP6 = culPressValP6;
    }

    public void setCulPressValP7(int culPressValP7) {
        this.culPressValP7 = culPressValP7;
    }

    public void setCulPressValP8(int culPressValP8) {
        this.culPressValP8 = culPressValP8;
    }

    public void setSetLeftAuto(int setLeftAuto) {
        this.setLeftAuto = setLeftAuto;
    }

    public void setSetValP2(int setValP2) {
        this.setValP2 = setValP2;
    }

    public void setSetValP3(int setValP3) {
        this.setValP3 = setValP3;
    }

    public void setSetValP4(int setValP4) {
        this.setValP4 = setValP4;
    }

    public void setSetValP5(int setValP5) {
        this.setValP5 = setValP5;
    }

    public void setSetValP6(int setValP6) {
        this.setValP6 = setValP6;
    }

    public void setSetValP7(int setValP7) {
        this.setValP7 = setValP7;
    }

    public void setSetValP8(int setValP8) {
        this.setValP8 = setValP8;
    }

    public void setSetRightAuto(int setRightAuto) {
        this.setRightAuto = setRightAuto;
    }

    public void setCulValP2(int culValP2) {
        this.culValP2 = culValP2;
    }

    public void setCulValP3(int culValP3) {
        this.culValP3 = culValP3;
    }

    public void setCulValP4(int culValP4) {
        this.culValP4 = culValP4;
    }

    public void setCulValP5(int culValP5) {
        this.culValP5 = culValP5;
    }

    public void setCulValP6(int culValP6) {
        this.culValP6 = culValP6;
    }

    public void setCulValP7(int culValP7) {
        this.culValP7 = culValP7;
    }

    public void setCulValP8(int culValP8) {
        this.culValP8 = culValP8;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    public void setTime_remaining_1(int time_remaining_1) {
        this.time_remaining_1 = time_remaining_1;
    }

    public void setTime_remaining_r(int time_remaining_r) {
        this.time_remaining_r = time_remaining_r;
    }

    public Runnable getmHandleDataRunnable() {
        return mHandleDataRunnable;
    }

    public void setmHandleDataRunnable(Runnable mHandleDataRunnable) {
        this.mHandleDataRunnable = mHandleDataRunnable;
    }

    public int getAcquisitionValP() {
        return acquisitionValP;
    }

    public void setAcquisitionValP(int acquisitionValP) {
        this.acquisitionValP = acquisitionValP;
    }

    public int getAcquisitionValP1() {
        return acquisitionValP1;
    }

    public void setAcquisitionValP1(int acquisitionValP1) {
        this.acquisitionValP1 = acquisitionValP1;
    }

    public int getAcquisitionValP2() {
        return acquisitionValP2;
    }

    public void setAcquisitionValP2(int acquisitionValP2) {
        this.acquisitionValP2 = acquisitionValP2;
    }

    public int getAcquisitionValP3() {
        return acquisitionValP3;
    }

    public void setAcquisitionValP3(int acquisitionValP3) {
        this.acquisitionValP3 = acquisitionValP3;
    }

    public int getAcquisitionValP4() {
        return acquisitionValP4;
    }

    public void setAcquisitionValP4(int acquisitionValP4) {
        this.acquisitionValP4 = acquisitionValP4;
    }

    public int getAcquisitionValP5() {
        return acquisitionValP5;
    }

    public void setAcquisitionValP5(int acquisitionValP5) {
        this.acquisitionValP5 = acquisitionValP5;
    }

    public int getAcquisitionValP6() {
        return acquisitionValP6;
    }

    public void setAcquisitionValP6(int acquisitionValP6) {
        this.acquisitionValP6 = acquisitionValP6;
    }

    public int getAcquisitionValP7() {
        return acquisitionValP7;
    }

    public void setAcquisitionValP7(int acquisitionValP7) {
        this.acquisitionValP7 = acquisitionValP7;
    }

    public int getAcquisitionValP8() {
        return acquisitionValP8;
    }

    public void setAcquisitionValP8(int acquisitionValP8) {
        this.acquisitionValP8 = acquisitionValP8;
    }

    public String getCeshi() {
        return ceshi;
    }

    public void setCeshi(String ceshi) {
        this.ceshi = ceshi;
    }

    public int getSetRenWen() {
        return setRenWen;
    }

    public void setSetRenWen(int setRenWen) {
        this.setRenWen = setRenWen;
    }

    public int getAwake_num_left() {
        return awake_num_left;
    }

    public void setAwake_num_left(int awake_num_left) {
        this.awake_num_left = awake_num_left;
    }

    public int getAwake_num_right() {
        return awake_num_right;
    }

    public void setAwake_num_right(int awake_num_right) {
        this.awake_num_right = awake_num_right;
    }

    public int getDianZong() {
        return dianZong;
    }

    public void setDianZong(int dianZong) {
        this.dianZong = dianZong;
    }

    public BleDevice getBleDevice() {
        return bleDevice;
    }

    public void setBleDevice(BleDevice bleDevice) {
        this.bleDevice = bleDevice;
    }

    public byte[] getTotalData() {
        return totalData;
    }

    public void setTotalData(byte[] totalData) {
        this.totalData = totalData;
    }
}
