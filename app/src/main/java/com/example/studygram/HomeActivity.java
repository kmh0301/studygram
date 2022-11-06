package com.example.studygram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.studygram.Adapter.PostAdapter;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView mRVpost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRVpost = findViewById(R.id.rv_post);
        mRVpost.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

        List<Post> postList = new ArrayList<>();
        Post post1 = new Post("username1","12 day ago", R.drawable.user1_icon, "100", R.drawable.user1_icon, "This is my icon!");
        Post post2 = new Post("username2","2 day ago", R.drawable.user2_icon, "50", R.drawable.user2_icon, "This is my icon!");
        postList.add(post1);
        postList.add(post2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRVpost.setLayoutManager(layoutManager);
        PostAdapter postAdapter = new PostAdapter(postList);
        mRVpost.setAdapter(postAdapter);
    }
}
