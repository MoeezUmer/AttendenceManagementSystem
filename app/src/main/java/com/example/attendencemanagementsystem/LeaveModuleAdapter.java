package com.example.attendencemanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class LeaveModuleAdapter extends RecyclerView.Adapter<LeaveModuleAdapter.ViewHolder> {

    Context context;
    ArrayList<LeaveData> userlist;

    public LeaveModuleAdapter(Context context, ArrayList<LeaveData> userarraylist) {
        this.context = context;
        this.userlist = userarraylist;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.leavedata,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  LeaveModuleAdapter.ViewHolder holder, int position) {

        LeaveData leave = userlist.get(position);

        holder.Name.setText(leave.getName());
        holder.Days.setText(leave.getLeaveDays());
        holder.Todat.setText(leave.getToDate());
        holder.Fromdat.setText(leave.getFromDate());

        holder.REJECT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore fstore = FirebaseFirestore.getInstance();
                CollectionReference cr = fstore.collection("LeaveRequest");
                Query query = cr.whereEqualTo("Name", leave.Name).whereEqualTo("LeaveDays",leave.LeaveDays).whereEqualTo("ToDate",leave.ToDate).whereEqualTo("FromDate",leave.FromDate);

                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){

                            for (DocumentSnapshot document : task.getResult()){

                                cr.document(document.getId()).delete();
                            }


                        }
                        else {



                        }

                    }
                });


            }
        });


        holder.REJECT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore fstore2 = FirebaseFirestore.getInstance();
                CollectionReference cr = fstore2.collection("LeaveRequest");
                Query query = cr.whereEqualTo("ToDate",leave.ToDate).whereEqualTo("FromDate",leave.FromDate);

                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){

                            for (DocumentSnapshot document : task.getResult()){

                                cr.document(document.getId()).delete();
                            }


                        }
                        else {



                        }

                    }
                });


            }
        });

        holder.ACCEPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context.getApplicationContext(), "Leave Accepted",Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView Name,Days,Todat,Fromdat;
        Button ACCEPT,REJECT;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            Name= itemView.findViewById(R.id.moeez);
            Days=itemView.findViewById(R.id.days);
            Todat=itemView.findViewById(R.id.date1);
            Fromdat=itemView.findViewById(R.id.date2);

            ACCEPT=itemView.findViewById(R.id.accept);
            REJECT=itemView.findViewById(R.id.reject);

        }
    }
}
