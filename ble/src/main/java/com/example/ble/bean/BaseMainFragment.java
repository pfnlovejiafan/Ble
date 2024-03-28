package com.example.ble.bean;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.example.ble.BluetoothData;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 覃微 on 2018/6/6.
 */

public abstract class BaseMainFragment extends SupportFragment implements View.OnClickListener , BluetoothData.BluetoothParsingData {
    protected abstract int getLayoutId();

    public abstract void onBindView(View view);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //更新UI数据
    protected void notifyBleDataChanged(MSPProtocol mspProtocol) {
    }

    protected void notifyDeviceDisconnected() {

    }

    @Override
    public void BluetoothParsingData(MSPProtocol mspProtocol) {
        notifyBleDataChanged(mspProtocol);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (getLayoutId() != 0) {
            view = inflater.inflate(getLayoutId(), container, false);
            onBindView(view);
        }
        MSPProtocol.getInstance().setOnBluetoothData(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // AppWatcher.INSTANCE.getObjectWatcher().watch(this);

    }

    @Override
    public void onClick(View v) {

    }
}
