package com.example.ble.Utils;

import static com.example.ble.Utils.AES.toByteArray;
import android.util.Log;
import java.util.Arrays;

/**
 * 项目名称：MyBle
 * 创建人： PANZERS
 * 创建时间：2024/3/2516:37
 * 修改人：PANZERS
 * 修改时间：2024/3/2516:37
 * 修改备注：
 */
public class BleUtils {

    public static byte[] encry(byte[] data){
        Log.v("数据", Arrays.toString(data));
        //050425c800
        String encrypt="";
        try {
            encrypt = AES.Encrypt(data,"yzm_blue_2020_01");
            Log.v("encrypt",encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] encryptB;
        if(encrypt.length()%2==0){
            encryptB = toByteArray(encrypt);
        }else {
            encryptB = toByteArray("0"+encrypt);
        }

        byte[] cmd = new byte[encryptB.length+5];
        cmd[0] = (byte) 0xaa;
        cmd[1] = (byte) 0x10;
        cmd[2] = toByteArray(String.format("%02x",encryptB.length+5))[0] ;
        for (int i=0;i<encryptB.length;i++){
            cmd[i+3] =  encryptB[i];
        }

        String hex = Integer.toHexString(CRC16Util.calcCrc16(cmd,0,encryptB.length+3)) ;
        if (hex.length() < 4) {
            hex = "0" + hex;
        }
        byte[] hexB = toByteArray(hex);
        cmd[encryptB.length+4] = hexB[0];
        if(hexB.length==2)
            cmd[encryptB.length+3] = hexB[1];

        return cmd;

    }
    /*动态数组插入*/
    public static byte[] insertData(byte[] original, int position, byte... data) {
        // 计算新数组的长度
        int newLength = original.length + data.length;
        // 创建新数组
        byte[] newArray = new byte[newLength];
        // 将原始数组从起始位置复制到新数组
        System.arraycopy(original, 0, newArray, 0, position);
        // 将要插入的数据复制到新数组
        System.arraycopy(data, 0, newArray, position, data.length);
        // 将原始数组中剩余的部分复制到新数组
        System.arraycopy(original, position, newArray, position + data.length, original.length - position);
        // 返回新数组
        return newArray;
    }
}
