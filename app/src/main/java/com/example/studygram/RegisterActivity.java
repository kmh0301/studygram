package com.example.studygram;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private Button mBtnRegister;
    private EditText nameReg, passwordReg, confirmPasswordReg, emailReg;
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
                String psw1= passwordReg.getText().toString();
                String psw2= confirmPasswordReg.getText().toString();
                if (nameReg.getText().toString().isEmpty() || passwordReg.getText().toString().isEmpty() || confirmPasswordReg.getText().toString().isEmpty()|| emailReg.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }else if(checkUsername()){
                    Toast.makeText(RegisterActivity.this, "This username already be used! ", Toast.LENGTH_SHORT).show();
                    return;
                }else if(confirmPasswordReg.getText().toString().equals(psw1)==false){
                    Toast.makeText(RegisterActivity.this, "Password should be the same", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    postData();
                }

            }

            public void postData() {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JSONObject object = new JSONObject();
                try {
                    //input your API parameters
                    object.put("Username", nameReg.getText().toString());
                    object.put("Email", emailReg.getText().toString());
                    object.put("Password", passwordReg.getText().toString());
                    object.put("ConfirmPassword", confirmPasswordReg.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Enter the correct url for your api service site
                String url = "https://b1d23e95-fb80-48ee-895c-c9102a8a7a29.mock.pstmn.io/users";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(RegisterActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }

            public boolean checkUsername(){

                return false;
            }


        });
    }


}