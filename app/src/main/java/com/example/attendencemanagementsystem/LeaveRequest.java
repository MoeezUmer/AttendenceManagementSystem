package com.example.attendencemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LeaveRequest extends AppCompatActivity {

    EditText txtname,txtdays,txtto,txtfrom;
    Button btnsubmit;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);

        txtname=findViewById(R.id.name);
        txtdays=findViewById(R.id.leave);
        btnsubmit=findViewById(R.id.submit);
        txtto=findViewById(R.id.to);
        txtfrom=findViewById(R.id.from);

        fstore=FirebaseFirestore.getInstance();

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = txtname.getText().toString();
                String days = txtdays.getText().toString();
                String today = txtto.getText().toString();
                String fromday = txtfrom.getText().toString();

                if (TextUtils.isEmpty(name)){

                    txtname.setText("Enter Name");
                }
                if (TextUtils.isEmpty(days)){

                    txtdays.setText("Enter Leave Days");
                }

                Map<String,Object> leave = new HashMap<>();

                leave.put("Name",name);
                leave.put("LeaveDays",days);
                leave.put("ToDate", today);
                leave.put("FromDate", fromday);


                fstore.collection("LeaveRequest").add(leave).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(LeaveRequest.this,"Leave Request Submitted",Toast.LENGTH_LONG).show();
                        }

                        else{
                            Toast.makeText(LeaveRequest.this,"Error",Toast.LENGTH_LONG).show();

                        }

                    }
                });
            }
        });

    }
}