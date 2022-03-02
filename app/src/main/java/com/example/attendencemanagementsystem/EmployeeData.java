package com.example.attendencemanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Datadata> userarraylist;
    MyAdapter myAdapter;
    FirebaseUser user;
    FirebaseAuth fauth;




    //CollectionReference collectionReference = fstore.collection("Attendence");

    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_data);

        recyclerView=findViewById(R.id.employeedata);
        
        fstore=FirebaseFirestore.getInstance();

        fauth=FirebaseAuth.getInstance();

        user=fauth.getCurrentUser();


        LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        userarraylist=new ArrayList<Datadata>();

        myAdapter = new MyAdapter(EmployeeData.this,userarraylist);


        recyclerView.setAdapter(myAdapter);

        EventChangeListener();




    }
    private void EventChangeListener(){



        fstore.collection("Attendence").orderBy("Name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable  QuerySnapshot value, @Nullable  FirebaseFirestoreException error) {

                if (error !=null){

                    Log.e("Firestore error", error.getMessage());

                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()){

                    if (dc.getType() == DocumentChange.Type.ADDED){

                        userarraylist.add(dc.getDocument().toObject(Datadata.class));
                    }

                    myAdapter.notifyDataSetChanged();
                }

            }
       });



    }
}