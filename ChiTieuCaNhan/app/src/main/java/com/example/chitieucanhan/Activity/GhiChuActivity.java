package com.example.chitieucanhan.Activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;

import com.example.chitieucanhan.Adapter.GhiChuAdapter;
import com.example.chitieucanhan.Database.DatabaseHandler;
import com.example.chitieucanhan.Dto.GhiChu;
import com.example.chitieucanhan.Dto.LichSu;
import com.example.chitieucanhan.MainActivity;
import com.example.chitieucanhan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SuppressLint("NewApi")
public class GhiChuActivity extends Activity {
    FloatingActionButton floatingActionButton;
    DatabaseHandler db;
    EditText tieudee, timee, noidungg;
    Button add, cancle;
    private ListView mListView;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private ArrayList<GhiChu> list = new ArrayList<GhiChu>();
    GhiChu ghiChu;
    GhiChuAdapter adapter;
    ImageView imageView, xoa;
    int mYear, mMonth, mDay;
    Toast mToast;
    SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghi_chu);
        mListView = findViewById(R.id.list_view_note);
        floatingActionButton = findViewById(R.id.btn_add);
        db = new DatabaseHandler(this);
        db.open();
        list = (ArrayList<GhiChu>) db.getALLGhiChu();
        adapter = new GhiChuAdapter(GhiChuActivity.this, R.layout.item_ghichu, list);
        mListView.setAdapter(adapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                delcaidat(position);

                return false;
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(GhiChuActivity.this);
            }
        });
    }


    public void delcaidat(int position) {
        {
            new AlertDialog.Builder(GhiChuActivity.this)
                    .setTitle("Chú ý")
                    .setMessage("Bạn có chắc xóa không")
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @TargetApi(11)
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            LayoutInflater inflater = getLayoutInflater();
                            View mToastView = inflater.inflate(R.layout.xoa_custom,
                                    null);
                            mToast = new Toast(GhiChuActivity.this);
                            mToast.setView(mToastView);
                            mToast.show();
                            db.Deleteghichu(

                                    db.getALLGhiChu().get(position).getTieude(), db
                                            .getALLGhiChu().get(position)
                                            .getTime(), db.getALLGhiChu()
                                            .get(position).getNoidung());
                            Intent intent = new Intent(getApplicationContext(),
                                    MainActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Không",
                            new DialogInterface.OnClickListener() {
                                @TargetApi(11)
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).show();

        }
    }


    private void opendialog(GhiChuActivity ghiChuActivity) {
        Dialog dialog = new Dialog(ghiChuActivity);
        dialog.setContentView(R.layout.ghichu_dialong);
        tieudee = dialog.findViewById(R.id.ed_tieude);
        timee = dialog.findViewById(R.id.ed_ngay);
        noidungg = dialog.findViewById(R.id.ed_noidung);
        add = dialog.findViewById(R.id.btnSaveLS);
        cancle = dialog.findViewById(R.id.btnCacelLS);
        imageView = dialog.findViewById(R.id.btnngay);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(GhiChuActivity.this, 0, ss, mYear, mMonth, mDay);
                d.show();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ghiChu = new GhiChu();
                ghiChu.setTieude(tieudee.getText().toString());
                ghiChu.setTime(timee.getText().toString());
                ghiChu.setNoidung(noidungg.getText().toString());
                if (validate() > 0) {

                        if (db.themghichu(tieudee.getText().toString(), timee.getText().toString(), noidungg.getText().toString()) > 0) {
                            Toast.makeText(GhiChuActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(GhiChuActivity.this, "Thêm thất bại", Toast.LENGTH_LONG).show();
                        }
                        capnhat();
                        dialog.dismiss();


                }


            }
        });

        dialog.show();
    }

    DatePickerDialog.OnDateSetListener ss = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            timee.setText(sdf.format(c.getTime()));
        }
    };

    public void capnhat() {
        list = (ArrayList<GhiChu>) db.getALLGhiChu();
        adapter = new GhiChuAdapter(GhiChuActivity.this, R.layout.item_ghichu, list);
        mListView.setAdapter(adapter);
    }

    public int validate() {
        int check = 1;
        if (tieudee.getText().length() == 0 || timee.getText().length() == 0 || noidungg.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
            check = -1;
        }
        return check;
    }

}