package com.example.chitieucanhan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.chitieucanhan.Dto.LichSu;
import com.example.chitieucanhan.Dto.ThongKe;
import com.example.chitieucanhan.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private ArrayList<LichSu> lichSus;
    private ArrayList<LichSu> ss;
    private Activity context;
    private LayoutInflater inflater;
    SearchView searchView;

    public CustomAdapter(Activity context, ArrayList<LichSu> lichSus) {
        super();
        this.context = context;
        this.lichSus = lichSus;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    public class ViewHolder {
        TextView tvtime, tvphanloai, tvsotien, tvtaikhoan;
    }


    @Override
    public int getCount() {
        return lichSus.size();
    }

    @Override
    public Object getItem(int position) {
        return lichSus.get(position).getTime();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_row_view, null);
            holder.tvtime = (TextView) convertView.findViewById(R.id.tvtime);
            holder.tvphanloai = (TextView) convertView.findViewById(R.id.tvphannhom);
            holder.tvsotien = (TextView) convertView.findViewById(R.id.tvsotien);
            holder.tvtaikhoan = (TextView) convertView.findViewById(R.id.tvtaikhoan);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.tvtime.setText("" + lichSus.get(position).getTime());
        holder.tvphanloai.setText("" + "" + lichSus.get(position).getPhanloai());
        holder.tvsotien.setText("" + "" + lichSus.get(position).getSotien());
        holder.tvtaikhoan.setText("" + "" + lichSus.get(position).getTaikhoan());

        return convertView;

    }
    @NonNull

    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()){
                   ss = lichSus;
                }else {
                    List<LichSu> one = new ArrayList<>();
                    for (LichSu thanhVien : lichSus){
                        if (thanhVien.getTaikhoan().toLowerCase().contains(charString.toLowerCase())){
                            one.add(thanhVien);
                        }
                    }
                   ss= (ArrayList<LichSu>) one;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = ss;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
               ss = (ArrayList<LichSu>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

}
