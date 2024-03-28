package com.example.ceapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.clj.fastble.data.BleDevice;
import com.example.ble.BluetoothData;
import com.example.ble.CynTest;
import com.example.ble.MyApplication;
import com.example.ble.Utils.Permission;
import com.example.ble.bean.BaseActivity;
import com.example.ble.bean.MSPProtocol;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements BluetoothData.BluetoothName {
    private RecyclerView recycle;
    private ArrayList<BleDevice> bleDevices;
    private BleAdapter bleAdapter;
    private MSPProtocol mspProtocol;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
    }

    @Override
    public void bindView() {
        MyApplication.getInstance().setOnBluetoothData(this);
        int calculation = new CynTest().calculation(10);
        Permission permission = new Permission();
        permission.checkPermissions(this);
        findViewById(R.id.tv_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.getInstance().disconnect();
                bleDevices.clear();
                bleAdapter.notifyDataSetChanged();
                MyApplication.getInstance().Scan();
            }
        });
        bleDevices = new ArrayList<>();
        recycle = findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        bleAdapter = new BleAdapter(bleDevices,this);
        recycle.setAdapter(bleAdapter);
        bleAdapter.setonItemClickListener(new BleAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MyApplication.getInstance().connect(bleDevices.get(position));
            }
        });
    }

    @Override
    public void BluetoothParsingData(MSPProtocol mspProtocol) {
        this.mspProtocol = mspProtocol;
        int setPressValP1 = mspProtocol.getSetPressValP1();
        Log.d("BluetoothParsingData", "setValue: 设置值1：" + mspProtocol.getSetPressValP1()
                +"setValue: 设置值2：" + mspProtocol.getSetPressValP2()
                +"setValue: 设置值3：" + mspProtocol.getSetPressValP3()
                +"setValue: 设置值4：" + mspProtocol.getSetPressValP4()
                +"setValue: 设置值5：" + mspProtocol.getSetPressValP5()
                +"setValue: 设置值6：" + mspProtocol.getSetPressValP6()
                +"setValue: 设置值7：" + mspProtocol.getSetPressValP7()
                +"setValue: 设置值8：" + mspProtocol.getSetPressValP8()
        );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Permission.RequestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {

                }
            }
        }
    }

    @Override
    public void BluetoothName(ArrayList<BleDevice> bleDevice) {
        Log.e("onScanStarted"+111111, "onScanStarted: "+bleDevice);
        for (int i = 0; i < bleDevice.size(); i++) {
            bleDevices.add(bleDevice.get(i));
        }
        bleAdapter.notifyDataSetChanged();
    }
}