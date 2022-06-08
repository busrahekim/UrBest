package com.example.urbest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText m;
    private Button resetButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        m = findViewById(R.id.forgotPwMail);
        resetButton = findViewById(R.id.resetPW);

        mAuth = FirebaseAuth.getInstance();
    }

    public void resetPassword(View view) {
        String e = m.getText().toString().trim();

        if(e.isEmpty()){
          m.setError("Mail is required!");
          m.requestFocus();
          return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
            m.setError("Please provide valid mail!");
            m.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(e).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Check your email to reset the password!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Try again!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}