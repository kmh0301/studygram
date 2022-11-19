package com.example.studygram;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.studygram.Adapter.PostAdapter;

import org.json.JSONException;
import org.json.JSONObject;


public class NewPostFragment extends Fragment {

    private EditText postContent;
    private Button addpost;
    private static User user = new User();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_post, container, false);
        postContent = view.findViewById(R.id.new_post_content);
        addpost = view.findViewById(R.id.add_post);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addpost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "000", Toast.LENGTH_SHORT).show();
                AddPost();
                Toast.makeText(getActivity(), "111", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void AddPost(){
        String url = "https://0399-218-102-211-54.ap.ngrok.io/post?username="+user.getUsername()+"&content="+postContent.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JSONObject object = new JSONObject();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getActivity(), "123", Toast.LENGTH_SHORT).show();
                        try {
                            //input your API parameters


//                            String username = object.getString("username");
//                            String content = object.getString("content");

                            object.put("username", user.getUsername());
                            object.put("content", postContent.getText().toString());
                            Toast.makeText(getActivity(), "456", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(getActivity(), "Add New Post", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Failed Please Try Again!", Toast.LENGTH_SHORT).show();
            }




        });
        requestQueue.add(jsonObjectRequest);
    }


}