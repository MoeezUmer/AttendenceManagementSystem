package com.example.attendencemanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LeaveStatusAdapter extends RecyclerView.Adapter<LeaveStatusAdapter.ViewHolder> {
    Context context;
    ArrayList<LeaveAcceptionData> userlistss;

    public LeaveStatusAdapter(Context context, ArrayList<LeaveAcceptionData> userlistss) {
        this.context = context;
        this.userlistss = userlistss;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.leaveacceptrejectlist,parent,false);
        return new LeaveStatusAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull  LeaveStatusAdapter.ViewHolder holder, int position) {

        LeaveAcceptionData leave = userlistss.get(position);

        holder.Name.setText(leave.getName());
        holder.Days.setText(leave.getLeaveDays());
        holder.Todat.setText(leave.getToDate());
        holder.Fromdat.setText(leave.getFromDate());

    }

    @Override
    public int getItemCount() {
        return userlistss.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name,Days,Todat,Fromdat;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            Name= itemView.findViewById(R.id.moeez);
            Days=itemView.findViewById(R.id.days);
            Todat=itemView.findViewById(R.id.date1);
            Fromdat=itemView.findViewById(R.id.date2);
        }
    }
}
