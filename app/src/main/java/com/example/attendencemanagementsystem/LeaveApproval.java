package com.example.attendencemanagementsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LeaveApproval extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList <LeaveData> userlist;

    LeaveModuleAdapter hiadapter;

    FirebaseFirestore fstore;
    FirebaseAuth fauth;
    FloatingActionButton fbutton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_approval);

        fstore=FirebaseFirestore.getInstance();
        fauth=FirebaseAuth.getInstance();
        recyclerView =findViewById(R.id.leaverecycler);
        fbutton=findViewById(R.id.floats);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        userlist= new ArrayList<LeaveData>();

        hiadapter= new LeaveModuleAdapter(LeaveApproval.this,userlist);

        recyclerView.setAdapter(hiadapter);

        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AdminPanel.class));
            }
        });



        EventListenerData();

    }

    private void EventListenerData() {

        fstore.collection("LeaveRequest").orderBy("Name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error !=null){

                    Log.e("Firestore error", error.getMessage());

                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()){

                    if (dc.getType() == DocumentChange.Type.ADDED){

                        userlist.add(dc.getDocument().toObject(LeaveData.class));
                    }

                    hiadapter.notifyDataSetChanged();
                }

            }
        });
    }


}