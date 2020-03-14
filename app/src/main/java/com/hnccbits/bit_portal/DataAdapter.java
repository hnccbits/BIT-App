package com.hnccbits.bit_portal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class DataAdapter extends ArrayAdapter<Data> {
    public DataAdapter(Activity context, ArrayList<Data> data) {
        super(context,0, data);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView = convertView;
        if(ListItemView==null)
        {
            ListItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Data CurrentItem=getItem(position);

        TextView dText=(TextView)ListItemView.findViewById(R.id.data_text);
        dText.setText(CurrentItem.getDataText());

        ImageView dImage=(ImageView)ListItemView.findViewById(R.id.data_image);
        dImage.setImageResource(CurrentItem.getmDataImgRecourceId());

        return ListItemView;
    }
}
