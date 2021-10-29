package com.example.banking_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomeAdapter_sendtouser extends RecyclerView.Adapter<com.example.banking_app.ViewHolder> {

    Transcation sendtoUser;
    List<Model> modelList;
    Context context;

    public CustomeAdapter_sendtouser(Transcation sentoUser, List<Model> modelList) {
        this.sendtoUser = sentoUser;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public com.example.banking_app.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.userslist, parent, false);

        com.example.banking_app.ViewHolder viewHolder = new com.example.banking_app.ViewHolder(itemView);
        viewHolder.setOnClickListener((view, position) -> sendtoUser.selectuser(position));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.banking_app.ViewHolder holder, int position) {
        holder.mName.setText(modelList.get(position).getName());
        holder.mPhonenumber.setText(modelList.get(position).getPhoneno());
        holder.mBalance.setText(modelList.get(position).getBalance());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void setFilter(ArrayList<Model> newList){
        modelList = new ArrayList<>();
        modelList.addAll(newList);
        notifyDataSetChanged();
    }
}
