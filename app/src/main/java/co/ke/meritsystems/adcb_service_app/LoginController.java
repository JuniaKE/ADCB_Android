package co.ke.meritsystems.adcb_service_app;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

    EditText txt_email, txt_password;
    ProgressBar progressBar;
    TextView data;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_password = (EditText) findViewById(R.id.txt_password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        data = (TextView) findViewById(R.id.loginResponse);
        btnLogin.setText("Login");
    }


    public void UserLogin(View view) {
        //first getting the values
        final String email = txt_email.getText().toString();
        final String password = txt_password.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(email)) {
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
        btnLogin.setText("Checking Credentials. Please Wait");
        //if everything is fine

        StringRequest loginRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        if (response != null) {
                            //Create user Data
                            String _userId = "";
                            String _name = "";
                            String _email = "";
                            String _phone = "";
                            String _darkMode = "";
                            String _accessToken = "";
                            try {
                                JSONObject responseData = new JSONObject(response);

                                JSONObject userData = responseData.getJSONObject("user");
                                _userId = userData.getString("id");
                                _accessToken = userData.getString("access_token");
                                _name = userData.getString("name");
                                _email = userData.getString("email");
                                Toast.makeText(LoginController.this, "Logging in as "+_name, Toast.LENGTH_SHORT).show();
                                _phone = userData.getString("phone");
                                Toast.makeText(LoginController.this, "Login Successfully! ", Toast.LENGTH_SHORT).show();
                                //Create Session
                                SessionManager sessionManager = new SessionManager(LoginController.this);
                                sessionManager.createLoginSession(_name, _email, _phone, _userId, _darkMode, _accessToken);
                                // Start Activity
                                startActivity(new Intent(getApplicationContext(), DashboardController.class));
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                    }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        btnLogin.setText("Login");
                        Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        DataServiceDriver.getInstance(this).addToRequestQueue(loginRequest);
    }
}
