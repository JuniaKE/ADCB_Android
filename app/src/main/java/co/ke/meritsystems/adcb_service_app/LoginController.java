package co.ke.meritsystems.adcb_service_app;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginController extends AppCompatActivity {

    String email, password;
    EditText txt_email, txt_password;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_password = (EditText) findViewById(R.id.txt_password);

    }

    public void UserLogin(View view) {
  /*      Intent intent = new Intent(LoginController.this, DashboardController.class);
        startActivity(intent);
        finish();*/
        //first getting the values
        final String username = txt_email.getText().toString();
        final String password = txt_password.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            txt_email.setError("Please enter your email");
            txt_email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            txt_password.setError("Please enter your password");
            txt_password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        System.out.println(" SERVER RESPONSE " + response);
                        if (response != null) {
                            startActivity(new Intent(getApplicationContext(), DashboardController.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "No response was received from server", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", username);
                params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
