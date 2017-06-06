package com.ismailr13.pkb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by azhzh on 6/4/2017.
 */

public class PasalAdapter extends BaseAdapter {
    // params
    ArrayList listItem;
    Activity activity;

    public PasalAdapter(Activity activity, ArrayList listItem){
        this.activity = activity;
        this.listItem = listItem;
    }

    //method ini akan menentukan seberapa banyak data yang akan ditampilkan didalam ListView
    //listItem.size() == jumlah data dalam object List yang ada
    @Override
    public int getCount() {
        return listItem.size();
    }

    //method ini untuk mengakses per-item objek dalam list
    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //method ini yang akan menampilkan baris per baris dari item yang ada di ListView
    //dengan menggunakan pattern ViewHolder untuk optimasi performa dari ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_pasal, null);
            holder.txtNumber = (TextView)view.findViewById(R.id.txt_item_number);
            holder.txtTitle = (TextView)view.findViewById(R.id.txt_item_title);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        PasalModel law = (PasalModel) getItem(position);
        holder.txtNumber.setText(law.getNumber());
        holder.txtTitle.setText(law.getTitle());
        return view;
    }

    static class ViewHolder{
        TextView txtNumber, txtTitle;
    }
}
