package com.example.studygram;

import android.annotation.SuppressLint;
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
    private RecyclerView myPost;
    private ArrayList<Post> postList;
    private User user = new User();
    private TextView usernameProfile;
    PostAdapter adapter;
    String url ="https://2c1f-218-102-211-54.ap.ngrok.io/post?username="+user.getUsername();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        GetData();
        usernameProfile.setText(user.getUsername());
        myPost = view.findViewById(R.id.rv_post_1);
        myPost.setLayoutManager(new LinearLayoutManager(getContext()));
        myPost.setHasFixedSize(true);
        PostAdapter postAdapter = new PostAdapter(getContext(),postList);
        myPost.setAdapter(postAdapter);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void GetData(){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        postList = new ArrayList<>();
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
                myPost.setAdapter(adapter);
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