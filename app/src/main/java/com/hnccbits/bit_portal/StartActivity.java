package com.hnccbits.bit_portal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {
    private EditText id,password;
    private Button login,signup;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        id=(EditText)findViewById(R.id.txt_username);
        password=(EditText)findViewById(R.id.txt_password);
        login=(Button)findViewById(R.id.btn_ok);
        signup=(Button)findViewById(R.id.btn_signup);

        auth=FirebaseAuth.getInstance(); //creating an object [auth] of [FirebaseAuth] class.
        Toast.makeText(StartActivity.this,"use Email as UserName",Toast.LENGTH_LONG).show();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_id=id.getText().toString();
                String text_password=password.getText().toString();
                if(TextUtils.isEmpty(text_id)||TextUtils.isEmpty(text_password))
                {
                    Toast.makeText(StartActivity.this,"invalid credientiala",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    loginUser(text_id, text_password);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, RegisterActivity.class));
            }
        });

    }

    private void loginUser(String text_id, String text_password)
    {

        auth.signInWithEmailAndPassword(text_id,text_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {


            @Override
            public void onSuccess(AuthResult authResult)
            {

                Toast.makeText(StartActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                finish();
            }
        });
        auth.signInWithEmailAndPassword(text_id,text_password).addOnFailureListener(StartActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Toast.makeText(StartActivity.this,"not Registered ",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
