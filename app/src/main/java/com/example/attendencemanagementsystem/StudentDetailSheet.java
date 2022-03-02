package com.example.attendencemanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StudentDetailSheet extends AppCompatActivity {

    private TextView p1,a1,l1,mm,pp1,aa1,ll1,ppp1,aaa1,lll1, grade1,grade2,grade3;
    FirebaseFirestore fstore,fstore2,fstore3,fstore4,fstore5,fstore6,fstore7,fstore8,fstore9;
    int g1,g2,g3,tot;
    double avg;
    CardView card;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail_sheet);

        p1=findViewById(R.id.p);
        a1=findViewById(R.id.a);
        l1=findViewById(R.id.l);
        pp1=findViewById(R.id.pp);
        aa1=findViewById(R.id.aa);
        ll1=findViewById(R.id.ll);
        aaa1=findViewById(R.id.aaa);
        ppp1=findViewById(R.id.ppp);
        lll1=findViewById(R.id.lll);

        grade1=findViewById(R.id.grade);
        grade2=findViewById(R.id.grade2);
        grade3=findViewById(R.id.grade3);


        g1=Integer.parseInt(p1.getText().toString());
        g2=Integer.parseInt(pp1.getText().toString());
        g3=Integer.parseInt(ppp1.getText().toString());


        tot = g1 +g2 +g3;

        avg= tot/3;




        card=findViewById(R.id.cardView);

        fstore=FirebaseFirestore.getInstance();
        fstore2=FirebaseFirestore.getInstance();
        fstore3=FirebaseFirestore.getInstance();
        fstore4=FirebaseFirestore.getInstance();
        fstore5=FirebaseFirestore.getInstance();
        fstore6=FirebaseFirestore.getInstance();
        fstore7=FirebaseFirestore.getInstance();
        fstore8=FirebaseFirestore.getInstance();
        fstore9=FirebaseFirestore.getInstance();

        mm=findViewById(R.id.moeez);




        ViewFunction();




    }

    private void ViewFunction() {




        fstore.collection("Attendence").whereEqualTo("Name", "Moeez").whereEqualTo("Attendence","Present")
        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {

                if (task.isSuccessful()){



                    int total , p = 0, a = 0, l = 0;


                    for (QueryDocumentSnapshot document : task.getResult()){

                        String  attend = document.getString("Attendence");
                        String  nam = document.getString("Name");



                            switch (attend) {
                                case "Present":

                                   p++;

                                    break;

                                case "present" :
                                    p++;

                                    break;

                            }

                        }

                    p1.setText(p+"");




                    if ((p >= 1) && (p <=3)){

                         grade1.setText("D");


                    }
                    else if ((p >= 4) && ( p <= 15)){

                        grade1.setText("C");

                    }

                    else if ((p >= 16) && (p  <= 22 )){

                        grade1.setText("B");

                    }
                    else if ((p >= 23) && (p  <= 30 )){

                        grade1.setText("A");

                    }



                    Toast.makeText(getApplicationContext(), "Done." , Toast.LENGTH_SHORT).show();


                }

            }
        });

        fstore2.collection("Attendence").whereEqualTo("Name", "Moeez").whereEqualTo("Attendence","Absent")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    int total , p = 0, a = 0, l = 0;


                    for (QueryDocumentSnapshot document : task.getResult()){

                        String  attend = document.getString("Attendence");
                        String  nam = document.getString("Name");



                        switch (attend) {
                            case "Absent":
                                a++;

                                break;
                            case "absent":
                                a++;

                                break;

                        }

                    }

                    a1.setText(a+"");

                    Toast.makeText(getApplicationContext(), "Done." , Toast.LENGTH_SHORT).show();


                }

            }
        });

        fstore3.collection("Attendence").whereEqualTo("Name", "Moeez").whereEqualTo("Attendence","Leave")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    int total , p = 0, a = 0, l = 0;


                    for (QueryDocumentSnapshot document : task.getResult()){

                        String  attend = document.getString("Attendence");
                        String  nam = document.getString("Name");



                        switch (attend) {
                            case "Leave":
                                l++;

                                break;
                            case "leave":
                                l++;

                                break;

                        }

                    }

                    l1.setText(l+"");

                    Toast.makeText(getApplicationContext(), "Done." , Toast.LENGTH_SHORT).show();


                }

            }
        });

        fstore4.collection("Attendence").whereEqualTo("Name", "Ali").whereEqualTo("Attendence","Present")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    int total , pp = 0, aa = 0, ll = 0;


                    for (QueryDocumentSnapshot document : task.getResult()){

                        String  attend = document.getString("Attendence");
                        String  nam = document.getString("Name");



                        switch (attend) {
                            case "Present":
                                pp++;

                                break;
                            case "present" :
                                pp++;

                                break;

                        }

                    }

                    pp1.setText(pp+"");


                    if ((pp >= 1) && (pp <=3)){

                        grade2.setText("D");


                    }
                    else if ((pp >= 4) && ( pp <= 15)){

                        grade2.setText("C");

                    }

                    else if ((pp >= 16) && (pp  <= 22 )){

                        grade2.setText("B");

                    }
                    else if ((pp >= 23) && (pp  <= 30 )){

                        grade2.setText("A");

                    }

                    Toast.makeText(getApplicationContext(), "Done." , Toast.LENGTH_SHORT).show();


                }

            }
        });

        fstore5.collection("Attendence").whereEqualTo("Name", "Ali").whereEqualTo("Attendence","Absent")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    int total , pp = 0, aa = 0, ll = 0;


                    for (QueryDocumentSnapshot document : task.getResult()){

                        String  attend = document.getString("Attendence");
                        String  nam = document.getString("Name");



                        switch (attend) {
                            case "Absent":
                                aa++;

                                break;
                            case "absent":
                                aa++;

                                break;

                        }

                    }

                    aa1.setText(aa+"");

                    Toast.makeText(getApplicationContext(), "Done." , Toast.LENGTH_SHORT).show();


                }

            }
        });

        fstore6.collection("Attendence").whereEqualTo("Name", "Ali").whereEqualTo("Attendence","Leave")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    int total , pp = 0, aa = 0, ll = 0;


                    for (QueryDocumentSnapshot document : task.getResult()){

                        String  attend = document.getString("Attendence");
                        String  nam = document.getString("Name");



                        switch (attend) {
                            case "Leave":
                                ll++;

                                break;
                            case "leave" :
                                ll++;

                                break;

                        }

                    }

                    ll1.setText(ll+"");

                    Toast.makeText(getApplicationContext(), "Done." , Toast.LENGTH_SHORT).show();


                }

            }
        });



        fstore7.collection("Attendence").whereEqualTo("Name", "Humza").whereEqualTo("Attendence","Present")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    int total , ppp = 0, aaa = 0, lll = 0;


                    for (QueryDocumentSnapshot document : task.getResult()){

                        String  attend = document.getString("Attendence");
                        String  nam = document.getString("Name");



                        switch (attend) {
                            case "Present":
                                ppp++;

                                break;
                            case "present" :
                                ppp++;

                                break;

                        }

                    }

                    ppp1.setText(ppp+"");

                    if ((ppp >= 1) && (ppp <=3)){

                        grade3.setText("D");


                    }
                    else if ((ppp >= 4) && (ppp <= 15)){

                        grade3.setText("C");

                    }

                    else if ((ppp >= 16) && (ppp  <= 22 )){

                        grade3.setText("B");

                    }
                    else if ((ppp >= 23) && (ppp  <= 30 )){

                        grade3.setText("A");

                    }

                    Toast.makeText(getApplicationContext(), "Done." , Toast.LENGTH_SHORT).show();


                }

            }
        });


        fstore8.collection("Attendence").whereEqualTo("Name", "Humza").whereEqualTo("Attendence","Absent")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    int total , ppp = 0, aaa = 0, lll = 0;


                    for (QueryDocumentSnapshot document : task.getResult()){

                        String  attend = document.getString("Attendence");
                        String  nam = document.getString("Name");



                        switch (attend) {
                            case "Absent":
                                aaa++;

                                break;
                            case "absent":
                                aaa++;

                                break;

                        }

                    }

                    aaa1.setText(aaa+"");

                    Toast.makeText(getApplicationContext(), "Done." , Toast.LENGTH_SHORT).show();


                }

            }
        });

        fstore9.collection("Attendence").whereEqualTo("Name", "Humza").whereEqualTo("Attendence","Leave")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    int total , ppp = 0, aaa = 0, lll = 0;


                    for (QueryDocumentSnapshot document : task.getResult()){

                        String  attend = document.getString("Attendence");
                        String  nam = document.getString("Name");



                        switch (attend) {
                            case "Leave":
                                lll++;

                                break;

                            case "leave":
                                lll++;

                                break;

                        }

                    }

                    lll1.setText(lll+"");

                    Toast.makeText(getApplicationContext(), "Done." , Toast.LENGTH_SHORT).show();


                }

            }
        });





    }
}