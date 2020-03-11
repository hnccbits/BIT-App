package com.hnccbits.bit_portal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private Button alumni,student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        alumni=(Button)findViewById(R.id.btn_alumniReg);
        student=(Button)findViewById(R.id.btn_studentReg);

        alumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this,"coming soon",Toast.LENGTH_SHORT).show();
            }
        });


        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(RegisterActivity.this,StudentRegistrationActivity.class));
            }
        });
    }

}
