package com.example.studygram;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.studygram.Adapter.ToDoAdpater;
import com.example.studygram.Data.AddNewTask;
import com.example.studygram.Data.DatabaseHandler;
import com.example.studygram.Data.DialogCloseListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoFragment extends Fragment implements DialogCloseListener {
    private RecyclerView taskRecyclerView;
    private ToDoAdpater tasksAdapter;
    private List<ToDoModel> taskList;
    private DatabaseHandler db;
    private FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new DatabaseHandler(getContext());
        db.openDatabase();

        taskList = new ArrayList<>();


        taskRecyclerView = view.findViewById(R.id.taskRecyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tasksAdapter = new ToDoAdpater(db, this);
        taskRecyclerView.setAdapter(tasksAdapter);

        fab = view.findViewById(R.id.fab);

        taskList = db.getAllTasks();

        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getActivity().getSupportFragmentManager(), AddNewTask.TAG);
            }
        });




//        ToDoModel task = new ToDoModel();
//        task.setTask("This is a Test Task");
//        task.setStatus(0);
//        task.setId(1);
//
//
//        taskList.add(task);
//        taskList.add(task);
//        taskList.add(task);
//        taskList.add(task);
//        taskList.add(task);
//        taskList.add(task);
//
//        tasksAdapter.setTasks(taskList);


    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();

    }
}