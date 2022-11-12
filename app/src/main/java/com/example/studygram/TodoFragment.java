package com.example.studygram;

import static com.example.studygram.databinding.FragmentTodoBinding.inflate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.studygram.Adapter.ToDoAdpater;
import com.example.studygram.Data.AddNewTask;
import com.example.studygram.Data.DatabaseHandler;
import com.example.studygram.Data.DialogCloseListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class TodoFragment extends Fragment implements DialogCloseListener {
    private DatabaseHandler db;
    private RecyclerView tasksRecyclerView;
    private ToDoAdpater tasksAdapter;
    private FloatingActionButton fab;
    private Button fab2;
    private AddNewTask addNewTask;

    private List<ToDoModel> taskList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = View.inflate(getActivity(),R.layout.fragment_todo,null);

        db = new DatabaseHandler(getActivity());
        db.openDatabase();

        tasksRecyclerView = view.findViewById(R.id.taskRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasksAdapter = new ToDoAdpater(db,TodoFragment.this);
        tasksRecyclerView.setAdapter(tasksAdapter);


        fab = view.findViewById(R.id.fab);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);

        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(v -> {
          AddNewTask.newInstance().show(getParentFragmentManager(), AddNewTask.TAG);
            Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
        });
        return view;
    }


    @Override
    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();

    }
}