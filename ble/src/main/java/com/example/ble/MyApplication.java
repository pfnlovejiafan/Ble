package com.example.ble;

import static com.example.ble.Utils.AES.toByteArray;

import android.app.Application;
import android.bluetooth.BluetoothGatt;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.example.ble.Utils.BleUtils;
import com.example.ble.bean.MSPProtocol;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 作者：  王静波
 * 日期：  2018/2/27
 * 注明：
 */

public class MyApplication extends Application {
    //二代蓝牙协议
    public static final String CL_SERVICE_UUID = "0000FFE0-0000-1000-8000-00805F9B34FB";
    public static final String CL_TAG_WRITE_UUID = "0000FFE2-0000-1000-8000-00805F9B34FB";
    public static final String CL_TAG_READ_UUID = "0000FFE1-0000-1000-8000-00805F9B34FB";
    private ArrayList<BleDevice> bleDevices;
    private static MyApplication instance;
    public static Handler handler;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        bleDevices = new ArrayList<>();
        handler = new Handler();
        BleManager.getInstance().init(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //蓝牙
                initBle();
            }
        }, 1000);
    }

    public static MyApplication getInstance() {
        if (instance == null) {
        }
        return instance;
    }

    public void Scan() {
        bleDevices.clear();
        if (!BleManager.getInstance().isBlueEnable()) {
            /**
             * 开启蓝牙
             * */
            BleManager.getInstance().enableBluetooth();
        }
        BleManager.getInstance().scan(new BleScanCallback() {
            @Override
            public void onScanStarted(boolean success) {
                Log.e("onScanStarted", "onScanStarted: "+success);
            }

            @Override
            public void onLeScan(BleDevice bleDevice) {
            }

            @Override
            public void onScanning(BleDevice bleDevice) {
                bleDevices.add(bleDevice);
                if (listener != null) {
                    listener.OnListener(bleDevices);
                }
            }

            @Override
            public void onScanFinished(List<BleDevice> scanResultList) {
            }
        });
    }

    //停止搜索
    public void stopScan() {
        BleManager.getInstance().cancelScan();
    }

    private void initBle() {
        //初始化蓝牙
        UUID[] serviceUuids = {UUID.fromString(CL_SERVICE_UUID)};
        BleManager.getInstance().init(this);
        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(5, 2000)//重连次数以及间隔时间
                .setConnectOverTime(10000)//连接超时时间
                .setSplitWriteNum(20)//拆分写入数
                .setOperateTimeout(5000);//操作超时时间

        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
                .setServiceUuids(serviceUuids) // 只扫描指定的服务的设备，可选
                .setScanTimeOut(20000) // 扫描超时时间，可选，默认10秒
                //.setDeviceName(true, names) // 设置扫描过滤的设备名称列表
                //.setDeviceMac(mac) // 设置扫描过滤的设备MAC地址
                .build();
        BleManager.getInstance().initScanRule(scanRuleConfig);
    }

    public void connect(BleDevice bleDevice) {
        BleManager.getInstance().connect(bleDevice, new BleGattCallback() {
            @Override
            public void onStartConnect() {
                Toast.makeText(MyApplication.this, "开始连接", Toast.LENGTH_SHORT).show();
                Log.e("连接", "连接中");
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {
                Toast.makeText(MyApplication.this, "连接失败", Toast.LENGTH_SHORT).show();
                Log.e("连接", "连接失败");
                handler.postDelayed(() -> {
                    connect(bleDevice);
                }, 1500);
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                Toast.makeText(MyApplication.this, "连接成功", Toast.LENGTH_SHORT).show();
                Log.e("连接", "连接成功，正在发现服务");
                MSPProtocol.getInstance().bleDevice = bleDevice;
                stopScan();
                write(toByteArray("0f0612" + String.format("%08x", System.currentTimeMillis() / 1000) + "0813" + "13798579241"));
                read();
            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice bleDevice, BluetoothGatt gatt, int status) {
                if (isActiveDisConnected) {
                    Log.v("onDisConnected","蓝牙连接手动断开");
                    Log.e("aris", "主动断开连接  " + bleDevice.getMac());
                } else {
                    Log.v("onDisConnected","蓝牙连接自动断开，尝试重新连接。。。");
                    handler.postDelayed(() -> {
                        connect(bleDevice);
                    }, 2000);
                }
            }
        });
    }
    public void read() {
        BleManager.getInstance().notify(
                MSPProtocol.getInstance().bleDevice,
                CL_SERVICE_UUID,
                CL_TAG_READ_UUID, new BleNotifyCallback() {

                    @Override
                    public void onNotifySuccess() {
                    }

                    @Override
                    public void onNotifyFailure(BleException exception) {
                    }

                    @Override
                    public void onCharacteristicChanged(byte[] data) {
                        MSPProtocol.getInstance().parseData(data);
                    }
                });
    }

    private int errorCode = 0;
    private int numberOfTimes = 5;


    public void write(byte[] data){
        byte[] encry = BleUtils.encry(data);//15ef8d0260061a4af020055c3583a111
        BleManager.getInstance().write(
                MSPProtocol.getInstance().bleDevice,
                CL_SERVICE_UUID,
                CL_TAG_WRITE_UUID,
                encry,
                new BleWriteCallback() {
                    @Override
                    public void onWriteSuccess(int current, int total, byte[] justWrite) {
                        //  justWrite 为指令内容
                        Log.e("连接", justWrite.toString());
                        errorCode = 1;
                        numberOfTimes = 0;
                    }

                    @Override
                    public void onWriteFailure(BleException exception) {
                        // 指令发送失败
                        Log.i("连接：ble发送命令", "失败：$exception" + "================" + numberOfTimes);
                        errorCode = 0;
                        numberOfTimes = numberOfTimes - 1;
                        //重新发送
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                write(data);
                            }
                        }, 1500);
                    }
                });
    }
    public void disconnect(){
        if (MSPProtocol.getInstance().bleDevice!=null){
            BleManager.getInstance().disconnect(MSPProtocol.getInstance().bleDevice);
        }else {
            BleManager.getInstance().disconnectAllDevice();
        }
    }

    public interface onListener {
        void OnListener(ArrayList<BleDevice> bleDevices);
    }

    private onListener listener;

    public void setListener(onListener listener) {
        this.listener = listener;
    }
}
