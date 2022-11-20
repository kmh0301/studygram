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


public class UserProfileFragment extends Fragment {
    private RecyclerView mypost;
    private ArrayList<Post> postList;
    private TextView username;
    private static User user = new User();
    String url ="https://c64c-218-102-211-54.ap.ngrok.io/post?username="+user.getUsername();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        username = view.findViewById(R.id.tv_username_2);
        username.setText(user.getUsername());
        dataInitialize();
        mypost = view.findViewById(R.id.rv_post_1);
        mypost.setLayoutManager(new LinearLayoutManager(getContext()));
        mypost.setHasFixedSize(true);
    }

    private void dataInitialize(){
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

                PostAdapter postAdapter = new PostAdapter(getContext(),postList);
                mypost.setAdapter(postAdapter);
                Toast.makeText(getActivity(), "hello2", Toast.LENGTH_SHORT).show();
                postAdapter.notifyDataSetChanged();
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

//    private void GetData(){
//
//        postList = new ArrayList<>();
//
//        //queue.start();
//    }



}