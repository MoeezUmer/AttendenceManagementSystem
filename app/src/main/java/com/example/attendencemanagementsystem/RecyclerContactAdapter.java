package com.example.attendencemanagementsystem;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {

    FirebaseFirestore fstore;
    FirebaseAuth fauth;

    Context context;
    ArrayList<Datadata>  arrContact;

    public RecyclerContactAdapter(Context context, ArrayList<Datadata> arrContact) {
        this.context = context;
        this.arrContact = arrContact;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.listdata,parent,false);
        return new RecyclerContactAdapter.ViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerContactAdapter.ViewHolder holder, int position) {

        Datadata model = arrContact.get(position);

        holder.Fullname.setText(model.getName());
        holder.Attendence.setText(model.getAttendence());
        holder.Date.setText(model.getDate());

        holder.lrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog =new Dialog(context);
                dialog.setContentView(R.layout.add_update_lay);

                fstore=FirebaseFirestore.getInstance();
                fauth=FirebaseAuth.getInstance();

                EditText tname = dialog.findViewById(R.id.edname);
                EditText tattendence = dialog.findViewById(R.id.edattendence);
                EditText tdate = dialog.findViewById(R.id.eddate);
                Button bt = dialog.findViewById(R.id.btn);

                TextView txttitle = dialog.findViewById(R.id.text);

                txttitle.setText("Update Student");

                bt.setText("Update");

                tname.setText(arrContact.get(position).Name);
                tattendence.setText(arrContact.get(position).Attendence);
                tdate.setText(arrContact.get(position).Date);



                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       // String name= "",attendence = "", date= "";

                        String name = tname.getText().toString();
                        String attendence = tattendence.getText().toString();
                        String date = tdate.getText().toString();







                        arrContact.set(position, new Datadata(name,attendence,date));
                        notifyItemChanged(position);

                        dialog.dismiss();


                    }
                });

                dialog.show();


            }
        });

        holder.lrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                fstore=FirebaseFirestore.getInstance();
                fauth=FirebaseAuth.getInstance();

                AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("Delete contact")
                        .setMessage("Are you sure want to delete").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                arrContact.remove(position);
                                notifyItemRemoved(position);

                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                              FirebaseFirestore fstore = FirebaseFirestore.getInstance();
                              CollectionReference cr = fstore.collection("Attendence");
                             Query query = cr.whereEqualTo("Name", model.Name).whereEqualTo("Attendence",model.Attendence).whereEqualTo("Date",model.Date);
                             query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                 @Override
                                 public void onComplete(@NonNull  Task<QuerySnapshot> task) {

                                     if (task.isSuccessful()){

                                         for (DocumentSnapshot document : task.getResult()){

                                             cr.document(document.getId()).delete();
                                         }


                                     }
                                     else {
                                         
                                     }

                                 }
                             });

                Map<String,Object> del = new HashMap<>();

                del.put("Name", FieldValue.delete());
                del.put("Attendence",FieldValue.delete());
                del.put("Date",FieldValue.delete());




                builder.show();
                return true;


            }
        });


    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView Fullname,Attendence,Date;
        LinearLayout lrow;


        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            Fullname=itemView.findViewById(R.id.moeez);
            Attendence=itemView.findViewById(R.id.attendence);
            Date=itemView.findViewById(R.id.date);
            lrow=itemView.findViewById(R.id.linear);


        }
    }
}
