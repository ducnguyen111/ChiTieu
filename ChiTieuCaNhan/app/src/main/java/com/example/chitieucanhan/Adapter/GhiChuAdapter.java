package com.example.chitieucanhan.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chitieucanhan.Activity.GhiChuActivity;
import com.example.chitieucanhan.Dto.GhiChu;
import com.example.chitieucanhan.Dto.Quanly;
import com.example.chitieucanhan.R;

import java.util.ArrayList;
import java.util.List;


public class GhiChuAdapter extends ArrayAdapter<GhiChu> {
    private Activity activity;
    private int idLayout;
    private ArrayList<GhiChu> llist;
    private ArrayList<GhiChu> mListFilter;
    private GhiChuActivity s;
    int a;

    public GhiChuAdapter(Activity activity, int idLayout, ArrayList<GhiChu> llist) {
        super(activity, idLayout, llist);
        this.activity = activity;
        this.idLayout = idLayout;
        this.llist = llist;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        convertView = inflater.inflate(idLayout, null);
        TextView tieude = (TextView) convertView.findViewById(R.id.item_tieude);
        TextView ngay = (TextView) convertView.findViewById(R.id.item_ngay);
        TextView noidung = (TextView) convertView.findViewById(R.id.item_noidung);

        tieude.setText(llist.get(position).getTieude());
        ngay.setText(llist.get(position).getTime());
        noidung.setText(llist.get(position).getNoidung());
        return convertView;
    }

}
