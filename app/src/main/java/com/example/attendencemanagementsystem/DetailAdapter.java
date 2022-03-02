package com.example.attendencemanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    Context context;
    ArrayList<DetailStudent>  user;

    public DetailAdapter(Context context, ArrayList<DetailStudent> user) {
        this.context = context;
        this.user = user;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detaildata,parent,false);
        return new DetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {

        DetailStudent detail = user.get(position);

        holder.Name.setText(detail.getName());
        holder.Attendence.setText(detail.getAttendence());
        holder.Attendence.setText(detail.getAttendence());
        holder.Attendence.setText(detail.getAttendence());

        holder.PRESENT.setText(detail.getAttendence());
        holder.ABSENT.setText(detail.getAttendence());
        holder.LEAVE.setText(detail.getAttendence());




    }

    @Override
    public int getItemCount() {
        return user.size() ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name,Attendence,PRESENT,ABSENT,LEAVE;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.moeez);

            Attendence= itemView.findViewById(R.id.present);
            Attendence = itemView.findViewById(R.id.absent);
            Attendence= itemView.findViewById(R.id.leave);

            PRESENT = itemView.findViewById(R.id.p);
            ABSENT = itemView.findViewById(R.id.a);
            LEAVE = itemView.findViewById(R.id.l);
        }
    }
}
