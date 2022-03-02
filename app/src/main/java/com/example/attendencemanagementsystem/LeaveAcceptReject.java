package com.example.attendencemanagementsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LeaveAcceptReject extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<LeaveAcceptionData> userlistss;
    LeaveStatusAdapter  ladapter;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_accept_reject);

        recyclerView = findViewById(R.id.rec);
        fstore=FirebaseFirestore.getInstance();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        userlistss=new ArrayList<LeaveAcceptionData>();

        ladapter = new LeaveStatusAdapter(LeaveAcceptReject.this,userlistss);
        recyclerView.setAdapter(ladapter);


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

                        userlistss.add(dc.getDocument().toObject(LeaveAcceptionData.class));
                    }

                    ladapter.notifyDataSetChanged();
                }

            }
        });
    }
}