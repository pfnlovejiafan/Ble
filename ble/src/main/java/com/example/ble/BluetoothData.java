package com.example.ble;

import com.clj.fastble.data.BleDevice;
import com.example.ble.bean.MSPProtocol;

import java.util.ArrayList;

/**
 * 项目名称：CeApplication
 * 创建人： PANZERS
 * 创建时间：2024/3/289:30
 * 修改人：PANZERS
 * 修改时间：2024/3/289:30
 * 修改备注：
 */
public interface BluetoothData {
    interface BluetoothName {
        void BluetoothName(ArrayList<BleDevice> bleDevices);
    }
    interface BluetoothParsingData {
        void BluetoothParsingData(MSPProtocol mspProtocol);
    }
}
