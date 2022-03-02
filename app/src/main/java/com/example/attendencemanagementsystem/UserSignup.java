package com.example.attendencemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class UserSignup extends AppCompatActivity {

    EditText txtfullname,txtemail,txtusername,txtpassword;
    FloatingActionButton btnregister;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        txtfullname= findViewById(R.id.fullname);
        txtemail = findViewById(R.id.email);
        txtusername= findViewById(R.id.username);
        txtpassword=findViewById(R.id.password);




        btnregister=findViewById(R.id.register);

        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();


        if (fauth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),UserLogin.class));
        }





        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = txtfullname.getText().toString();
                String em = txtemail.getText().toString().trim();
                String usern= txtusername.getText().toString();
                String pass= txtpassword.getText().toString().trim();


                if (TextUtils.isEmpty(em)){
                    txtemail.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    txtpassword.setError("Password is Required");
                    return;
                }
                if (pass.length() < 6){
                    txtpassword.setError("Password must b >= 6 Characters.");
                    return;
                }

                fauth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(UserSignup.this,"User Created ",Toast.LENGTH_LONG).show();
                            userID = fauth.getCurrentUser().getUid();
                            DocumentReference documentReference= fstore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Fullname",fname);
                            user.put("Email",em);
                            user.put("Username",usern);

                            user.put("isUser","1");

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Log.d(TAG,"onSuccess: User profile is created for"+userID);

                                }
                            });

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));



                        }

                        else {
                            Toast.makeText(UserSignup.this,"Error."+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }



                    }
                });


            }
        });


    }
}