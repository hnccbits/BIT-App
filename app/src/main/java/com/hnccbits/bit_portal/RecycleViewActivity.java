package com.hnccbits.bit_portal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class RecycleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ListView List=(ListView)findViewById(R.id.list);

        ArrayList<Data> data= QueryUtils.ExtractData();

        DataAdapter adapter= new DataAdapter(this,data);
        List.setAdapter(adapter);
    }
}
