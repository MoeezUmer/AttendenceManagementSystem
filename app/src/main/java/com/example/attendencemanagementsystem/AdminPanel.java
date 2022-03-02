package com.example.attendencemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminPanel extends AppCompatActivity {

    Button btnclick,btnleave,btnview;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        btnclick=findViewById(R.id.button2);
        btnleave=findViewById(R.id.leavepage);
        btnview=findViewById(R.id.view);
        image=findViewById(R.id.imageView3);

        image.setBackgroundResource(R.drawable.attend);


        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewStudents.class));
                finish();
            }
        });

        btnleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),LeaveApproval.class));
                finish();

            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),StudentDetailSheet.class));
            }
        });

    }
}