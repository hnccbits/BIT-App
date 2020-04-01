package com.hnccbits.bit_portal;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {
    private EditText id,password;
    private Button login,signup;
    private ImageView img;
    private LinearLayout linearLayout;
    private TextView txtProgress,txt_aninate;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private TextView errorMessage;
    private static final String TAG = "MyTag";


    @Override
    protected void onStart() {
        super.onStart();
        // Not a good place to check this.
//        FirebaseUser currentUser = auth.getCurrentUser();//check if the user is already logged in
//        Log.d(TAG, "current User = "+currentUser);
//        if(currentUser!=null){
//            Log.d(TAG, "onStart: "+currentUser.getDisplayName()+"---"+
//                    currentUser.getEmail()+"****"+currentUser.getProviderId()+
//                    "___"+currentUser.getUid());//if the user is logged in goto MainActivity
//            // PENDING to be added later
//            startActivity(new Intent(StartActivity.this, MainActivity.class));
//        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        // Checking if user is already logged in.
        // It should be done here before we load layouts and animations.
        // We should make a splash screen for period in which it is skipping this activity and loading MainActivity
        if (auth.getCurrentUser() != null){
            Log.d(TAG, "onCreate: User already logged in, Launching MainActivity");
            startActivity(new Intent(this, MainActivity.class));
        }

        setContentView(R.layout.activity_start);


        id=(EditText)findViewById(R.id.txt_username);
        password=(EditText)findViewById(R.id.txt_password);
        login=(Button)findViewById(R.id.btn_ok);
        signup=(Button)findViewById(R.id.btn_signup);
        txtProgress=(TextView)findViewById(R.id.txt_progress);
        progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        img=(ImageView)findViewById(R.id.icon_animate);
        linearLayout=(LinearLayout)findViewById(R.id.linear);
        txt_aninate=(TextView)findViewById(R.id.text_animate);

        initializeVariables();

        linearLayout.animate().alpha(0f).setDuration(1);

        TranslateAnimation animation=new TranslateAnimation(0,0,-900,-200);
        animation.setDuration(2200);
        animation.setFillAfter(false);

        animation.setAnimationListener(new MyAnimationListener());


        TranslateAnimation animation2=new TranslateAnimation(0,0,900,150);
        animation2.setDuration(2200);
        animation2.setFillAfter(false);
        animation2.setAnimationListener(new MyAnimationListener());
        txt_aninate.setAnimation(animation2);
        img.setAnimation(animation);


        Toast.makeText(StartActivity.this,"use Email as UserName",Toast.LENGTH_LONG).show();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_id=id.getText().toString();
                String text_password=password.getText().toString();
                if(TextUtils.isEmpty(text_id)||TextUtils.isEmpty(text_password))
                {
                    id.setError("enter name");
                    password.setError("enter password");
                    Toast.makeText(StartActivity.this,"invalid credientiala",Toast.LENGTH_SHORT).show();
                    errorMessage.setText("Empty field(s)");

                }
                else
                {
                    progressBar.setVisibility(View.VISIBLE);
                    txtProgress.setText("Loading... please wait.");
                    txtProgress.setVisibility(View.VISIBLE);
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
                errorMessage.setText("");
                Toast.makeText(StartActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                finish();
            }
        });
        auth.signInWithEmailAndPassword(text_id,text_password).addOnFailureListener(StartActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {

                progressBar.setVisibility(View.GONE);
                txtProgress.setVisibility(View.GONE);
//                UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
//                System.out.println("Successfully fetched user data: " + userRecord.getUid());
                Toast.makeText(StartActivity.this,"not Registered ",Toast.LENGTH_SHORT).show();
                errorMessage.setText("Incorrect password");

            }
        });


    }

    private class MyAnimationListener implements Animation.AnimationListener
    {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override

        public void onAnimationEnd(Animation animation) {
            img.clearAnimation();
            img.setVisibility(View.INVISIBLE);
            txt_aninate.clearAnimation();
            txt_aninate.setVisibility(View.INVISIBLE);
            linearLayout.animate().alpha(1f).setDuration(1000);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private void initializeVariables() {
        id=(EditText)findViewById(R.id.txt_username);
        password=(EditText)findViewById(R.id.txt_password);
        login=(Button)findViewById(R.id.btn_ok);
        signup=(Button)findViewById(R.id.btn_signup);
        errorMessage=findViewById(R.id.error_message);
    }
}
