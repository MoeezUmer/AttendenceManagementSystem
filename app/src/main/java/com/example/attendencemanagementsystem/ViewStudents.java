package com.example.attendencemanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.attendencemanagementsystem.R.layout.add_update_lay;

public class ViewStudents extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fbutton,ff;
    Dialog dialog;
    ArrayList<Datadata> arrContacts = new ArrayList<Datadata>();

    FirebaseFirestore fstore;
    FirebaseAuth fauth;

    RecyclerContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        recyclerView=findViewById(R.id.recyclerstudents);
        fbutton=findViewById(R.id.floatingButton);
        ff=findViewById(R.id.floating);

        fstore=FirebaseFirestore.getInstance();
        fauth=FirebaseAuth.getInstance();

        ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),AdminPanel.class));

            }
        });

        EventListener();


        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(ViewStudents.this);
                dialog.setContentView(add_update_lay);

                EditText tname = dialog.findViewById(R.id.edname);
                EditText tattendence = dialog.findViewById(R.id.edattendence);
                EditText tdate = dialog.findViewById(R.id.eddate);
                Button bt = dialog.findViewById(R.id.btn);

                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        String name = tname.getText().toString();
                        String attendence = tattendence.getText().toString();
                        String date = tdate.getText().toString();






                        Map<String,Object> added = new HashMap<>();

                         added.put("Name",name);
                         added.put("Attendence",attendence);
                         added.put("Date",date);

                         fstore.collection("Attendence").add(added).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                          public void onComplete(@NonNull Task<DocumentReference> task) {

                             if (task.isSuccessful()){

                                 Toast.makeText(ViewStudents.this,"Attendence Save",Toast.LENGTH_LONG).show();

                             }

                           }
                          }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull  Exception e) {

                             }
                          });

                        arrContacts.add(new Datadata());



                        adapter.notifyItemInserted(arrContacts.size()-1);
                        recyclerView.scrollToPosition(arrContacts.size()-1);
                        dialog.dismiss();








                    }
                });

                dialog.show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new RecyclerContactAdapter(this, arrContacts);

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);
    }

    private void EventListener() {

        fstore.collection("Attendence").orderBy("Name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error !=null){

                    Log.e("Firestore error", error.getMessage());

                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()){

                    if (dc.getType() == DocumentChange.Type.ADDED){

                        arrContacts.add(dc.getDocument().toObject(Datadata.class));
                    }

                    adapter.notifyDataSetChanged();
                }

            }
        });


    }
}