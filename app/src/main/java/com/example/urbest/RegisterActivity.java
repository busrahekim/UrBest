package com.example.urbest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText secondName;
    private EditText userName;
    private EditText mail;
    private EditText password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        firstName = findViewById(R.id.name);
        secondName = findViewById(R.id.surname);
        userName = findViewById(R.id.inputUsername);
        mail = findViewById(R.id.inputMail);
        password = findViewById(R.id.inputPassword);
    }

    public void rgBtnClicked(View view) {

        String username = userName.getText().toString();
        String name = firstName.getText().toString();
        String surname = secondName.getText().toString();
        String pw = password.getText().toString();
        String email = mail.getText().toString();

        boolean checked = checkInputs(username,name,surname,pw,email);

        if(checked == true){
            mAuth.createUserWithEmailAndPassword(email,pw)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(username,name,surname,pw,email);

                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){

                                            Toast.makeText(getApplicationContext(),"User has been registered successfully!",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                            startActivity(intent);
                                        }
                                        else{
                                            Log.d("aglamakben",task.getException().getMessage());
                                            Toast.makeText(getApplicationContext(),"Failed to register! Try again!",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }else {Log.d("nebununderdiReg",task.getException().getMessage());
                                Toast.makeText(getApplicationContext(),"Failed to register! Try again!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    private boolean checkInputs(String username, String name, String surname, String pw, String email) {
        if(username.isEmpty()){
            userName.setError("Username is required!");
            userName.requestFocus();
            return false;
        }
        if(name.isEmpty() ){
            firstName.setError("First name is required!");
            firstName.requestFocus();
            return false;
        }
        if(surname.isEmpty()){
            secondName.setError("Second name is required!");
            secondName.requestFocus();
            return false;
        }
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

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mail.setError("Please provide valid email!");
            mail.requestFocus();
            return false;
        }
        return true;
    }
}