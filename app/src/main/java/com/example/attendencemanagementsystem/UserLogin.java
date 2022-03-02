package com.example.attendencemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserLogin extends AppCompatActivity {

    Button btnlogin;
    FloatingActionButton btnsignup;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    EditText txtemail,txtpassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        btnsignup=findViewById(R.id.signup);
        btnlogin=findViewById(R.id.login);
        txtemail=findViewById(R.id.email);
        txtpassword=findViewById(R.id.password);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();







        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txtemail.getText().toString().trim();
                String password  = txtpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    txtemail.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    txtpassword.setError("Password is Required");
                    return;
                }
                if (password.length() < 6){
                    txtpassword.setError("Password must b >= 6 Characters.");
                    return;
                }

                fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(UserLogin.this,"Login Successfully",Toast.LENGTH_LONG).show();

                            checkUserAccessLevel(fauth.getCurrentUser().getUid());

                            startActivity(new Intent(getApplicationContext(),AttendenceHome.class));

                        }
                        else {
                            Toast.makeText(UserLogin.this, "Error." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }



                    }


                });



            }
        });
    }

    private void checkUserAccessLevel(String uid) {

        DocumentReference df= fstore.collection("users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","Success"+documentSnapshot.getData());

                if (documentSnapshot.getString("isAdmin") !=null){
                    startActivity(new Intent(getApplicationContext(),AdminPanel.class));
                    finish();
                }

                if(documentSnapshot.getString("isUser") !=null){

                    startActivity(new Intent(getApplicationContext(), AttendenceHome.class));
                    finish();
                }

            }
        });
    }

    public void Signup(View view){

        startActivity(new Intent(getApplicationContext(),UserSignup.class));
    }

    protected void onStart(){

        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() !=null){

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }


}