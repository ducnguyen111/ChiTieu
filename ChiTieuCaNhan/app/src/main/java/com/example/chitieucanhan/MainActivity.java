package com.example.chitieucanhan;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;


import com.example.chitieucanhan.databinding.ActivityMainBinding;
import com.example.chitieucanhan.Activity.GhiChuActivity;
import com.example.chitieucanhan.Activity.QuanLyActivity;
import com.example.chitieucanhan.Activity.ThongKeActivity;
import com.example.chitieucanhan.Activity.ThuChiActivity;

public class MainActivity extends TabActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        String Pins = sharedPreferences.getString("pin", "1234");
        if (Pins.equals("1234")) {
            Toast.makeText(this, "Vui lòng thay đổi mã pin để bảo mật", Toast.LENGTH_SHORT).show();
        }
        final TabHost tabHost =getTabHost();
        TabHost.TabSpec thuchi=tabHost.newTabSpec("Thu Chi");
        thuchi.setIndicator("Thu Chi");
        Intent intent=new Intent(MainActivity.this, ThuChiActivity.class);
        thuchi.setContent(intent);

        TabHost.TabSpec thongke=tabHost.newTabSpec("Thống kê");
        thongke.setIndicator("Thống kê");
        Intent intent1=new Intent(MainActivity.this, ThongKeActivity.class);
        thongke.setContent(intent1);

        TabHost.TabSpec quanly=tabHost.newTabSpec("Quản lý");
        quanly.setIndicator("Quản lý");
        Intent intent2=new Intent(MainActivity.this, QuanLyActivity.class);
        quanly.setContent(intent2);

        TabHost.TabSpec ghichu=tabHost.newTabSpec("Ghi Chú");
        ghichu.setIndicator("Ghi Chú");
        Intent intent3=new Intent(MainActivity.this, GhiChuActivity.class);
        ghichu.setContent(intent3);
        tabHost.addTab(thuchi);
        tabHost.addTab(thongke);
        tabHost.addTab(quanly);
        tabHost.addTab(ghichu);
        tabHost.setCurrentTab(0);




    }

}