package com.example.studygram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private Button mBtnRegister;
    private EditText nameReg, passwordReg, confirmPasswordReg, emailReg;
    private boolean validCheck = true;
    private String usernameExist;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // initializing our views
        mBtnRegister = findViewById(R.id.btn_register_2);
        nameReg= findViewById(R.id.usernameReg);
        passwordReg= findViewById(R.id.passwordReg);
        confirmPasswordReg = findViewById(R.id.confirmpasswordReg);
        emailReg = findViewById(R.id.emailReg);

        // adding on click listener to our button.
        mBtnRegister.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                checkUsername();
            }
            public void checkUserRegisterDate(boolean validCheck){
                String psw1= passwordReg.getText().toString();
                if (nameReg.getText().toString().isEmpty() || passwordReg.getText().toString().isEmpty() || confirmPasswordReg.getText().toString().isEmpty()|| emailReg.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }else if(validCheck){
                    Toast.makeText(RegisterActivity.this, "This username already be used! ", Toast.LENGTH_SHORT).show();
                    nameReg.setText("");
                    return;
                }else if(confirmPasswordReg.getText().toString().equals(psw1)==false){
                    Toast.makeText(RegisterActivity.this, "Password should be the same", Toast.LENGTH_SHORT).show();
                    passwordReg.setText("");
                    confirmPasswordReg.setText("");
                    return;
                }else{
                    postData();
                    returnMain();

                }
            }

            public void checkUsername(){

                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                String url1 ="https://0dd5-218-102-211-54.ap.ngrok.io/user?username="+ nameReg.getText().toString();
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url1,null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        usernameExist = "";
                        try {
                            JSONObject Users = response.getJSONObject(0);
                            usernameExist = Users.getString("username");
//                            Toast.makeText(RegisterActivity.this, "123", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(nameReg.getText().toString().equals(usernameExist)){
                            validCheck = true;

                        }else{
                            validCheck=false;
                        }
                        checkUserRegisterDate(validCheck);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(RegisterActivity.this, "789", Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegisterActivity.this, "something wrong!",Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }

            public void postData() {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JSONObject object = new JSONObject();
                try {
                    //input your API parameters
                    object.put("username", nameReg.getText().toString());
                    object.put("email", emailReg.getText().toString());
                    object.put("pwd", passwordReg.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Enter the correct url for your api service site
                String url2 = "https://0dd5-218-102-211-54.ap.ngrok.io/users?username="+nameReg.getText().toString()+"&email="+emailReg.getText().toString()+"&pwd="+passwordReg.getText().toString();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url2, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Failed Please Try Again!", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }

            public void returnMain(){
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }


        });
    }


}