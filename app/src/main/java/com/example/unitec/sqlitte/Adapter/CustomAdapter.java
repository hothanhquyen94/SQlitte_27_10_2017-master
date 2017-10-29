package com.example.unitec.sqlitte.Adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.unitec.sqlitte.DTO.NhanVienDTO;
import com.example.unitec.sqlitte.R;

import java.util.List;

/**
 * Created by Unitec on 27/10/2017.
 */

public class CustomAdapter extends ArrayAdapter<NhanVienDTO>{
    Context context;
    int resource;
    List<NhanVienDTO> objects;
    public CustomAdapter( Context context, int resource,  List<NhanVienDTO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource,parent,false);
        TextView txt = (TextView)view.findViewById(R.id.tenNhanVienCustom);
        txt.setText(objects.get(position).getTenNhanvien().toString());
        return view;
    }
}
