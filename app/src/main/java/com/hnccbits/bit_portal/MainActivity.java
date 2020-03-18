package com.hnccbits.bit_portal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button menu_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menu_button=findViewById(R.id.menu);
    }

    public void menu_operation(View view) {
        PopupMenu my_menu=new PopupMenu(getApplicationContext(),menu_button);
        MenuInflater inf=getMenuInflater();
        inf.inflate(R.menu.popup_menu,my_menu.getMenu());
        my_menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Item1:
                        Toast.makeText(MainActivity.this, "ITEM 1 CLICKED", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.Item2:
                        Toast.makeText(MainActivity.this, "ITEM 2 CLICKED", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.Item3:
                        Toast.makeText(MainActivity.this, "ITEM 3 CLICKED", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.Item4:
                        Toast.makeText(MainActivity.this, "ITEM 4 CLICKED", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
        my_menu.show();
    }
}
