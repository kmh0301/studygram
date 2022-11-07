package com.example.studygram.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studygram.R;
import com.example.studygram.ToDoModel;
import com.example.studygram.TodoFragment;


import java.util.List;

public class ToDoAdpater extends RecyclerView.Adapter<ToDoAdpater.ViewHolder> {
    private List<ToDoModel> todoList;
    private TodoFragment fragment;

    public ToDoAdpater(TodoFragment fragment){
        this.fragment = fragment;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(itemView);

    }
    public void onBindViewHolder(ViewHolder holder, int position) {
        ToDoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setText(item.getTask());
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
    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;

        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}
