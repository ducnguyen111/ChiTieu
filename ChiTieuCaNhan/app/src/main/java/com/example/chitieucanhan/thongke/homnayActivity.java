package com.example.chitieucanhan.thongke;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chitieucanhan.Adapter.ThongKeAdapter;
import com.example.chitieucanhan.Database.DatabaseHandler;
import com.example.chitieucanhan.Dto.ThongKe;
import com.example.chitieucanhan.R;

import java.util.ArrayList;

public class homnayActivity extends Activity {
    private ThongKeAdapter thongKeAdapter;
    private ArrayList<ThongKe> listchi;
    private ArrayList<ThongKe> listthu;
    TextView tientong;
    DatabaseHandler db;
    TextView tvTitle;
    ListView listView;
    Button btthu,btchi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongke);
        tvTitle=(TextView)findViewById(R.id.tvTitle);
        listView=(ListView)findViewById(R.id.listViewhienthithongke);
        btthu=(Button)findViewById(R.id.btthu);
        btchi=(Button)findViewById(R.id.btchi);
        tientong=findViewById(R.id.show_tong);

        db=new DatabaseHandler(getApplicationContext());
        db.open();
        listthu=db.getloggiaodichThongkehomnay("Khoản Thu");
        final ThongKeAdapter tkThu1=new ThongKeAdapter(getParent(),R.layout.custom_listview,listthu);
        listView.setAdapter(tkThu1);
        listchi=db.getloggiaodichThongkehomnay("Khoản Chi");
        final ThongKeAdapter tkChi = new ThongKeAdapter(getParent(),R.layout.custom_listview,listchi);
        tvTitle.setText("THỐNG KÊ HÔM NAY");

        btchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(tkChi);
                tientong.setText(""+db.tongtienhomnay("Khoản Chi")+"VND");
            }
        });
        btthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(tkThu1);
                tientong.setText(""+db.tongtienhomnay("Khoản Thu")+"VND");
            }
        });

    }
}