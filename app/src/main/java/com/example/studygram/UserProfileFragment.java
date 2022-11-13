package com.example.studygram;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studygram.Adapter.PostAdapter;

import java.util.ArrayList;


public class UserProfileFragment extends Fragment {
    private RecyclerView mypost;
    private ArrayList<Post> postList;
    private TextView myUsername;
    private User user;
    private String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myUsername=view.findViewById(R.id.tv_username_2);
        dataInitialize();
        mypost = view.findViewById(R.id.rv_post_1);
        mypost.setLayoutManager(new LinearLayoutManager(getContext()));
        mypost.setHasFixedSize(true);
        PostAdapter postAdapter = new PostAdapter(getContext(),postList);
        mypost.setAdapter(postAdapter);
    }

    private void dataInitialize(){
        postList = new ArrayList<>();
        Post post1 = new Post("username1","12 day ago", R.drawable.user1_icon, "100", R.drawable.user1_icon, "This is my icon!");
        Post post2 = new Post("username2","2 day ago", R.drawable.user2_icon, "50", R.drawable.user2_icon, "This is my icon!");


        postList.add(post1);
        postList.add(post2);

    }



}