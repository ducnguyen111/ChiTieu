package com.example.chitieucanhan.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chitieucanhan.Adapter.CustomAdapter;
import com.example.chitieucanhan.Database.DatabaseHandler;
import com.example.chitieucanhan.Dto.LichSu;
import com.example.chitieucanhan.R;

import java.util.ArrayList;

@SuppressLint("NewApi")
public class lichsuActivity extends AppCompatActivity {
    static final int DATE_DIALOG_ID = 0;
    private ListView mListView;
    private CustomAdapter mCustomAdapter;
    Toast mToast;
    private ArrayList<LichSu> _Contacts = new ArrayList<LichSu>();

    DatabaseHandler db;
    LichSu contacts;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_giao_dich_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        searchView=findViewById(R.id.seach);
        db = new DatabaseHandler(this);
        db.open();
        for (int i = 0; i < db.lichsugiaodich().size(); i++) {
            contacts = new LichSu();
            contacts.setTime(db.lichsugiaodich().get(i).getTime());
            contacts.setPhanloai(db.lichsugiaodich().get(i).getPhanloai());
            contacts.setSotien(db.lichsugiaodich().get(i).getSotien());
            contacts.setTaikhoan(db.lichsugiaodich().get(i).getTaikhoan());
            _Contacts.add(contacts);

        }

        mListView = (ListView) findViewById(R.id.listView1);

        mCustomAdapter = new CustomAdapter(lichsuActivity.this,
                _Contacts);
        mListView.setAdapter(mCustomAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               mCustomAdapter.getFilter().filter(query);

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mCustomAdapter.getFilter().filter(newText);
                return false;
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                delcaidat(position);

                return false;
            }
        });
    }

    public void delcaidat(final int position) {
        new AlertDialog.Builder(lichsuActivity.this)
                .setTitle("Chú ý")
                .setMessage("Bạn có chắc xóa không")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        LayoutInflater inflater = getLayoutInflater();
                        View mToastView = inflater.inflate(R.layout.xoa_custom,
                                null);
                        mToast = new Toast(lichsuActivity.this);
                        mToast.setView(mToastView);
                        mToast.show();
                        db.Deletels(
                                db.lichsugiaodich().get(position).getTime(), db
                                        .lichsugiaodich().get(position)
                                        .getPhanloai(), db.lichsugiaodich()
                                        .get(position).getSotien(), db
                                        .lichsugiaodich().get(position)
                                        .getTaikhoan());
                        Intent intent = new Intent(getApplicationContext(),
                                lichsuActivity.class);
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