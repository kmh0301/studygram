package com.example.studygram;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.studygram.Adapter.PostAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView mRVpost;
    private ArrayList<Post> postList;
    private String username;
    private String postdate;
    PostAdapter adapter;
    String url ="https://7673ea35-b3b8-4ab3-be53-d738fe9da3c7.mock.pstmn.io/post";


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

//        dataInitialize();
//
        mRVpost = view.findViewById(R.id.rv_post);
        mRVpost.setLayoutManager(new LinearLayoutManager(getContext()));
        mRVpost.setHasFixedSize(true);

        GetData();

//        PostAdapter postAdapter = new PostAdapter(getActivity().getApplicationContext(),postList);
//        mRVpost.setAdapter(postAdapter);
    }

    private void dataInitialize(){
        postList = new ArrayList<>();
        Post post1 = new Post("username1","12 day ago",R.drawable.user1_icon, "50", R.drawable.user1_icon, "This is my icon!");
        Post post2 = new Post("username2","2 day ago", R.drawable.user2_icon, "50", R.drawable.user2_icon, "This is my icon!");
        postList.add(post1);
        postList.add(post2);

    }

    private void GetData(){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0 ; i<=response.length();i++){
                    try {
                        JSONObject Posts = response.getJSONObject(i);
                        postList.add(new Post(Posts.getString("Username"), Posts.getString("Post content")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapter = new PostAdapter(getActivity().getApplicationContext(),postList);
                    mRVpost.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "something wrong!",Toast.LENGTH_SHORT).show();
            }
        });


        queue.add(request);
    }


}