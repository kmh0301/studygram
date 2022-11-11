package com.example.studygram.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studygram.Data.AddNewTask;
import com.example.studygram.Data.DatabaseHandler;
import com.example.studygram.R;
import com.example.studygram.ToDoModel;
import com.example.studygram.TodoFragment;


import java.util.List;

public class ToDoAdpater extends RecyclerView.Adapter<ToDoAdpater.ViewHolder> {
    private List<ToDoModel> todoList;
    private TodoFragment fragment;
    private DatabaseHandler db;

    public ToDoAdpater(DatabaseHandler db, TodoFragment fragment){
        this.db=db;
        this.fragment = fragment;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(itemView);

    }
    public void onBindViewHolder(ViewHolder holder, int position) {
        db.openDatabase();
        ToDoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    db.updateStatus(item.getId(), 1);
                }else {
                    db.updateStatus(item.getId(),0);
                }

            }
        });
    }

    public int getItemCount(){
        return todoList.size();
    }
    private boolean toBoolean(int n){
        return n!=0;
    }

    public void setTasks(List<ToDoModel> todoList){
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public void editItem(int position){
        ToDoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(fragment.getParentFragmentManager(), AddNewTask.TAG);//activity.getSupportFramgnetManager( part5: 29:40
    }
    public Context getContext(){
        return fragment.getActivity();//O:activity Part5: 26:35
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;

        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}
