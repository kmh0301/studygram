package com.example.studygram;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
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
    String url ="https://2c1f-218-102-211-54.ap.ngrok.io/posts";


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        GetData();
        mRVpost = view.findViewById(R.id.rv_post);
        mRVpost.setLayoutManager(new LinearLayoutManager(getContext()));
        mRVpost.setHasFixedSize(true);
        return view;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        dataInitialize();
//        GetData();
//        mRVpost = view.findViewById(R.id.rv_post);
//        mRVpost.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRVpost.setHasFixedSize(true);
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
        postList = new ArrayList<>();
//        StringRequest stringRequest = new StringRequest(url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray jsonArray = jsonObject.getJSONArray("Posts");
//                            for(int i = 0 ; i<jsonArray.length();i++) {
//                                JSONObject Posts = jsonArray.getJSONObject(i);
//                                String username = Posts.getString("Username");
//                                String post_content = Posts.getString("Post content");
//                                Post post = new Post(username,post_content);
//                                postList.add(post);
//                            }
//                            adapter = new PostAdapter(getActivity().getApplicationContext(),postList);
//                            mRVpost.setAdapter(adapter);
//                            adapter.notifyDataSetChanged();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("TAG", error.getMessage(), error);
//            }
//        });

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                postList = new ArrayList<>();
                try {
//                    JSONArray jsonArray = response.getJSONArray("username");
                    for(int i = 0 ; i<response.length();i++) {
                        JSONObject Posts = response.getJSONObject(i);

                        String username = Posts.getString("username");
                        String post_content = Posts.getString("content");
                        Post post = new Post(username,post_content);
                        postList.add(post);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new PostAdapter(getActivity().getApplicationContext(),postList);
                mRVpost.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "something wrong!",Toast.LENGTH_SHORT).show();
            }
        });


        // queue.add(stringRequest);
        queue.add(request);
        //queue.start();
    }


}