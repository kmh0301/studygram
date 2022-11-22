package com.example.studygram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private Button mBtnlogin;
    private EditText  mEtloginUsername;
    private EditText  mEtloginPassword;
    private static User user = new User();
    private String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtnlogin = findViewById(R.id.btn_login_2);
        mEtloginUsername = findViewById(R.id.et_username_login);
        mEtloginPassword = findViewById(R.id.et_pw_login);

        mBtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                String url ="https://0dd5-218-102-211-54.ap.ngrok.io/user?username="+ mEtloginUsername.getText().toString();

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        username = "";
                        password = "";

                        try {
                            JSONObject Users = response.getJSONObject(0);
                            username = Users.getString("username");
                            password = Users.getString("pwd");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        checkNamePassword(username, password);



                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "something wrong!",Toast.LENGTH_SHORT).show();
                    }
                });


                if(mEtloginUsername.getText().toString().equals(username) && mEtloginPassword.getText().toString().equals(password) ){
                    Toast.makeText(LoginActivity.this,username, Toast.LENGTH_SHORT).show();

                }
                queue.add(request);


                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Error occured",Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue.





//
            }
        });
    }


    public void access(){
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void checkNamePassword(String username, String password){
        if(mEtloginUsername.getText().toString().equals(username) && mEtloginPassword.getText().toString().equals(password) ){
            Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
            user.setUsername(username);

            access();
        }else{
            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            Toast.makeText(LoginActivity.this, "Please Input correct username and password", Toast.LENGTH_SHORT).show();
            username="";
            password="";
            mEtloginPassword.setText("");
        }
    }
}