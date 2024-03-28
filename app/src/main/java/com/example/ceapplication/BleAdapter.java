package com.example.ceapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.clj.fastble.data.BleDevice;

import java.util.ArrayList;

/**
 * 项目名称：MyBle
 * 创建人： PANZERS
 * 创建时间：2024/3/2514:49
 * 修改人：PANZERS
 * 修改时间：2024/3/2514:49
 * 修改备注：
 */
public class BleAdapter extends RecyclerView.Adapter<BleAdapter.ViewHolder>  {
    private ArrayList<BleDevice> bleDevices;
    private Context mContext;

    public BleAdapter(ArrayList<BleDevice> bleDevices, MainActivity mainActivity) {
        this.bleDevices = bleDevices;
        this.mContext = mainActivity;
    }

    @NonNull
    @Override
    public BleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ble, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.te_bleName.setText("蓝牙名称："+bleDevices.get(i).getName()+"");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    int position = viewHolder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) //点击的位置是否为空
                        listener.onItemClick(position);
                }
            }
        });
    }
    private onItemClickListener listener;

    //添加接口
    public interface onItemClickListener {
        void onItemClick(int position);
    }
    //创建构造方法
    public void setonItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
    @Override
    public int getItemCount() {
        return bleDevices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView te_bleName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            te_bleName = itemView.findViewById(R.id.te_bleName);
        }
    }
}
