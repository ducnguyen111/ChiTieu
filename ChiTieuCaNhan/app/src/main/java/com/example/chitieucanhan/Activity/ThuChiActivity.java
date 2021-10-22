package com.example.chitieucanhan.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chitieucanhan.Database.DatabaseHandler;
import com.example.chitieucanhan.R;

public class ThuChiActivity extends Activity {
    Button themGd, history, exit,fgt;
    TextView soDu, tinDung, tietKiem, tienMat;
    DatabaseHandler db;
    int sotienmat;
    int tientindung;
    int tientietkiem;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_chi);
        db = new DatabaseHandler(this);
        db.open();
        themGd = (Button) findViewById(R.id.imgadd);
        history = (Button) findViewById(R.id.imgweek);
        soDu = (TextView) findViewById(R.id.tvSoDu);
        tietKiem = (TextView) findViewById(R.id.TvTietKiem);
        tinDung = (TextView) findViewById(R.id.TvTinDung);
        tienMat = (TextView) findViewById(R.id.TvTienMat);
        exit = (Button) findViewById(R.id.quit);
        fgt=findViewById(R.id.gt);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (db.taikhoan("Tín Dụng").get(0) == null) {
            tientindung = 0;

        } else {
            tientindung = Integer.parseInt(db.taikhoan("Tín Dụng").get(0));
        }
        if (db.taikhoan("Tiền Mặt").get(0) == null) {
            sotienmat = 0;

        } else {
            sotienmat = Integer.parseInt(db.taikhoan("Tiền Mặt").get(0));
        }
        if (db.taikhoan("Tiết Kiệm").get(0) == null) {
            tientietkiem = 0;

        } else {
            tientietkiem = Integer.parseInt(db.taikhoan("Tiết Kiệm").get(0));
        }

        tietKiem.setText(tientietkiem + "");

        tinDung.setText(tientindung + "");

        tienMat.setText(sotienmat + "");

        soDu.setText( tientietkiem+tientindung+sotienmat+ "");

        themGd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(ThuChiActivity.this,themgiaodichActivity.class);
                startActivity(i);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(ThuChiActivity.this,
                        lichsuActivity.class);
                startActivity(i);
            }
        });
        fgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ThuChiActivity.this,GioithieuActivity.class);
                startActivity(intent);
            }
        });

    }

}