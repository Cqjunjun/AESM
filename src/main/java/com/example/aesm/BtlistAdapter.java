package com.example.aesm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.List;

public class BtlistAdapter extends ArrayAdapter {
    private int resourceId;
    private TextView name;
    private TextView address;
    private String A;
    private String B;
    public BtlistAdapter(@NonNull Context context, int resource, @NonNull List<Btlist> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Btlist btlist = (Btlist) getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        }else {
            view = convertView;
        }
         name =  view.findViewById(R.id.Bt_name);
        address =  view.findViewById(R.id.Bt_ardress);
        A = btlist.getBtName();
        B = btlist.getBtAddress();
        name.setText(A);
        address.setText(B);
        return view;
    }

}
