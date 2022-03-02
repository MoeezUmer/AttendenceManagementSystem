package com.example.attendencemanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

      public Context context;
      public ArrayList<Datadata> userarraylist;

    public MyAdapter(Context context, ArrayList<Datadata> userarraylist) {
        this.context = context;
        this.userarraylist = userarraylist;
    }

    @NonNull

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.listdata,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyAdapter.MyViewHolder holder, int position) {

        Datadata datadata = userarraylist.get(position);


        holder.Fullname.setText(datadata.getName());
        holder.Attendence.setText(datadata.getAttendence());
        holder.Date.setText(datadata.getDate());

        //Glide.with(context).load(userarraylist.get(position).getImageUrl()).into(holder.image);

        //String imageUrl = null;
        //imageUrl=Datadata.getImageUrl();
        //Picasso.get().load(imageUrl).into(holder.image);



    }

    @Override
    public int getItemCount() {
        return userarraylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Fullname,Attendence,Date;
        //CircleImageView image;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Fullname=itemView.findViewById(R.id.moeez);
            Attendence=itemView.findViewById(R.id.attendence);
            Date=itemView.findViewById(R.id.date);
           // image=itemView.findViewById(R.id.ima);

        }
    }
}
