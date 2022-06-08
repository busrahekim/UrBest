package com.example.urbest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    public EditText email, password;
    private TextView forgot;
    public MaterialButton loginButton, registerButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        email = findViewById(R.id.loginMail);
        password = findViewById(R.id.loginPassword);
        loginButton =  findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        forgot = findViewById(R.id.forgotPW);

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent);
            }
        }
        mAuth = FirebaseAuth.getInstance();
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            email.setText(sharedText);
        }
    }

    public void register(View view){
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
    }


    public void login(View view) {
        String pw = password.getText().toString().trim();
        String emailText = email.getText().toString().trim();
        boolean checked = checkInputs(pw,emailText);

        if(checked == true){
            mAuth.signInWithEmailAndPassword(emailText,pw).addOnCompleteListener(
                    task -> {
                        if(task.isSuccessful()){
                            Intent i = new Intent(this,TimeSpentService.class);
                            startService(i);
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Failed to login, please check the mail and password!",Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }

    }

    private boolean checkInputs(String pw, String emailText) {
        if(pw.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return false;
        }

        if(pw.length() < 6){
            password.setError("Min password length should be 6 characters!");
            password.requestFocus();
            return false;
        }

        if(emailText.isEmpty()){
            email.setError("Mail is required!");
            email.requestFocus();
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            email.setError("Please provide valid email!");
            email.requestFocus();
            return false;
        }
        return true;
    }

    public void forgotPassword(View view) {
        startActivity(new Intent(getApplicationContext(),ForgotPassword.class));
    }
}
