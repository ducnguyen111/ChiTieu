package com.example.chitieucanhan.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.chitieucanhan.R;
import com.example.chitieucanhan.thongke.homnayActivity;
import com.example.chitieucanhan.thongke.namActivity;
import com.example.chitieucanhan.thongke.thangActivity;

public class ThongKeActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitviy_thongke);
        final TabHost tabHost = getTabHost();

        TabHost.TabSpec homnay = tabHost.newTabSpec("Hôm Nay");
        homnay.setIndicator("Hôm Nay", getResources().getDrawable(R.drawable.background));
        Intent hn = new Intent(ThongKeActivity.this, homnayActivity.class);
        homnay.setContent(hn);

        TabHost.TabSpec thangnay = tabHost.newTabSpec("Tháng Này");
        thangnay.setIndicator("Tháng Này", getResources().getDrawable(R.drawable.backgroundbutton));
        Intent tn = new Intent(this, thangActivity.class);
        thangnay.setContent(tn);

        TabHost.TabSpec namnay = tabHost.newTabSpec("Năm Này");
        namnay.setIndicator("Năm Này", getResources().getDrawable(R.drawable.backgroundbutton));
        Intent nn = new Intent(this, namActivity.class);
        namnay.setContent(nn);


        tabHost.addTab(homnay);
        tabHost.addTab(thangnay);
        tabHost.addTab(namnay);
        tabHost.setCurrentTab(0);

    }
}