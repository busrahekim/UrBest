package com.example.urbest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ExampleDialog extends AppCompatDialogFragment {

    private EditText editFirstName;
    private EditText editLastName;
    private EditText editMail;
    private ExampleDialogListener exampleDialogListener;
    private int id;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    public ExampleDialog(int i) {
        this.id = i;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        editFirstName = view.findViewById(R.id.first);
        editLastName = view.findViewById(R.id.last);
        editMail = view.findViewById(R.id.mail);

        if (id == 1){
            editFirstName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editLastName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editMail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

            editFirstName.setHint("Original Password");
            editLastName.setHint("Enter new password");
            editMail.setHint("Enter new password again");
        }

        builder.setView(view)
                .setTitle("Edit Info")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(),"Canceled.",Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (id == 0) {
                            String firstname = editFirstName.getText().toString();
                            String lastname = editLastName.getText().toString();
                            String idk = editMail.getText().toString();

                            boolean checked = checkPW(firstname,lastname,idk);
                            //if(checked == true){
                                Log.d("here",checked+"a");
                                HashMap hashMap = new HashMap();
                                hashMap.put("firstName", firstname);
                                hashMap.put("lastName", lastname);
                                hashMap.put("mail", idk);

                                reference.child(userID).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        exampleDialogListener.applyText(firstname,lastname,idk);
                                    }
                                });
                                user.updateEmail(idk);
                            //}
                        } else {
                            String original = editFirstName.getText().toString();
                            String newOne = editLastName.getText().toString();
                            String again = editMail.getText().toString();
                            boolean checked = checkPW(original,newOne,again);

                            //if(checked == true){
                                HashMap hashMap = new HashMap();
                                hashMap.put("password", newOne);

                                reference.child(userID).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {

                                    }
                                });
                                user.updatePassword(newOne);
                            //}
                        }
                    }
                });

        return  builder.create();
    }

    private boolean checkInputs(String name, String surname, String email) {
        if(name.isEmpty() ){
            editFirstName.setError("First name is required!");
            editFirstName.requestFocus();
            return false;
        }
        if(surname.isEmpty()){
            editLastName.setError("Second name is required!");
            editLastName.requestFocus();
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editMail.setError("Please provide valid email!");
            editMail.requestFocus();
            return false;
        }
        return true;
    }
    private boolean checkPW(String pw, String pw0, String pw1) {
        if(pw.isEmpty()){
            editFirstName.setError("Password is required!");
            editFirstName.requestFocus();
            return false;
        }

        if(pw.length() < 6){
            editFirstName.setError("Original password length cannot be less than 6 characters!");
            editFirstName.requestFocus();
            return false;
        }

        if(pw0.isEmpty()){
            editLastName.setError("Password is required!");
            editLastName.requestFocus();
            return false;
        }

        if(pw0.length() < 6){
            editLastName.setError("Min new password length should be 6 characters!");
            editLastName.requestFocus();
            return false;
        }

        if(pw1.isEmpty()){
            editMail.setError("Password is required!");
            editMail.requestFocus();
            return false;
        }

        if(pw1.length() < 6){
            editMail.setError("Min password length should be 6 characters!");
            editMail.requestFocus();
            return false;
        }

        if(!pw0.equals(pw1)){
            editMail.setError("Min password length should be 6 characters!");
            editMail.requestFocus();
            return false;
        }

        return true;
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            exampleDialogListener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener{
        void applyText(String firstname, String lastname, String idk);
    }
}
