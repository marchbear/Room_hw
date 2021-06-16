package com.example.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Wait> allWait= new ArrayList<>();

    public void setAllWords(List<Wait> allWait) {
        this.allWait = allWait;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView= layoutInflater.inflate(R.layout.cell,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        Wait wait=allWait.get(position);
        holder.textViewnum.setText(String.valueOf(wait.getCOLUMN_PARTY_SIZE()));
        holder.textViewname.setText(wait.getCOLUMN_GUEST_NAME());
    }

    @Override
    public int getItemCount() {
        return allWait.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewnum,textViewname;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            textViewname=itemView.findViewById(R.id.gname);
            textViewnum=itemView.findViewById(R.id.num);

        }

    }
    public void removed(int pos,MyAdapter myAdapter,WaitDao waitDao)
    {

        waitDao.deleteWait(allWait.get(pos));

        myAdapter.notifyItemRemoved(pos);
    }
}
