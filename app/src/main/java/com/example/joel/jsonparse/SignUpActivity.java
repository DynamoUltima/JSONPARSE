package com.example.joel.jsonparse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class SignUpActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;

    private Button signUp;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

     //   firstName = (EditText)findViewById(R.id.firstField);
      //  lastName = (EditText)findViewById(R.id.lastField);
        email = (EditText)findViewById(R.id.email);

        signUp = (Button)findViewById(R.id.signUpbtn);

        mRequestQueue = Volley.newRequestQueue(this);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                final String first = firstName.getText().toString().trim();
   //             final String last = lastName.getText().toString().trim();
                final String mail = email.getText().toString().trim();
                final String pass = "Cisco_980";
        //        final String phone ="0265274457";
                try {
                    String url = "https://salty-garden-58363.herokuapp.com/v1/users/login";
                    JSONObject jsonBody =  new JSONObject();
                    jsonBody.put("email", mail);
                    jsonBody.put("password", pass);

                    JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {


                            Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_LONG).show();
                            Log.i("resz",response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            onBackPressed();

                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            final Map<String, String> headers = new HashMap<>();
                            headers.put("Authorization", "Basic " + "c2FnYXJAa2FydHBheS5jb206cnMwM2UxQUp5RnQzNkQ5NDBxbjNmUDgzNVE3STAyNzI=");//put your token here
                            return headers;
                        }
                    };
                    mRequestQueue.add(jsonOblect);

                } catch(JSONException e){
                    e.printStackTrace();
                }
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                            Toast.makeText(SignUpActivity.this, response, Toast.LENGTH_SHORT).show();
//
//                            Intent in = new Intent(SignUpActivity.this,MainActivity.class);
//                            startActivity(in);
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(SignUpActivity.this, "Error while Posting Data", Toast.LENGTH_SHORT).show();
//
//                            Log.i("error", String.valueOf(error));
//                        }
//                    }
//                ){
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String,String> params = new HashMap<>();
//                        params.put("first",firstName.getText().toString());
//                        params.put("last",lastName.getText().toString());
//                        params.put("email",email.getText().toString());
//
//                        return params;
//                    }
//                };
//
//                mRequestQueue.add(stringRequest);

            }
        });

    }
}
