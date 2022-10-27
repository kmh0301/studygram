package com.example.studygram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.studygram.Adapter.PostAdapter;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView mRVpost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRVpost = findViewById(R.id.rv_post);
        mRVpost.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        mRVpost.setAdapter(new PostAdapter(HomeActivity.this, new PostAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(HomeActivity.this, "click" + pos, Toast.LENGTH_LONG).show();
            }
        }));
    }
}