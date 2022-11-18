package com.example.studygram;

import static com.example.studygram.databinding.FragmentTodoBinding.inflate;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.studygram.Adapter.ToDoAdapter;
import com.example.studygram.Data.AddNewTask;
import com.example.studygram.Data.DatabaseHandler;
import com.example.studygram.Data.DialogCloseListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;


public class TodoFragment extends Fragment implements DialogCloseListener,IRefresh {
    private DatabaseHandler db;
    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;
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
        tasksAdapter = new ToDoAdapter(db,TodoFragment.this);
        tasksRecyclerView.setAdapter(tasksAdapter);


        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecycleItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);


        fab = view.findViewById(R.id.fab);

//        taskList = db.getAllTasks();
//        Collections.reverse(taskList);
//
//        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(v -> {
            AddNewTask.newInstance(this).show(getParentFragmentManager(), AddNewTask.TAG);
        });
        return view;
    }

    public void refresh(){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
    }
    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();

    }
}