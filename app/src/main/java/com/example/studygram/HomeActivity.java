package com.example.studygram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.studygram.Adapter.PostAdapter;
import com.example.studygram.databinding.ActivityHomeBinding;
import com.example.studygram.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());

        binding.bottomNavigationView.setBackground(null);

        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.timer:
                    replaceFragment(new TimerFragment());
                    break;

                case R.id.todo:
                    replaceFragment(new TodoFragment());
                    break;

                case R.id.userprofile:
                    replaceFragment(new UserProfileFragment());
                    break;

                case R.id.addpost:
                    replaceFragment(new NewPostFragment());
                    Toast.makeText(HomeActivity.this, "button work", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_navbar, fragment);
        fragmentTransaction.commit();

    }
}
