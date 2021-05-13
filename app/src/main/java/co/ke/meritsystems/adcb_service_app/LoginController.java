package co.ke.meritsystems.adcb_service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginController extends AppCompatActivity {

    String email, password;
    EditText txt_email,txt_password;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_password = (EditText) findViewById(R.id.txt_password);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, DashboardController.class));
        }

        //if user presses on login
        //calling the method login
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

    }

    private void userLogin() {
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

        if (!email.equals("") && !password.equals("")){
            JSONObject credentials = new JSONObject();
            try {
                credentials.put("email", email);
                credentials.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //if everything is fine
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLs.URL_LOGIN, credentials,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressBar.setVisibility(View.GONE);
                            try {
                                //converting response to json object
                                JSONObject obj = new JSONObject((Map) response);

                                //if no error in response
                                if (!obj.getBoolean("error")) {
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                    //getting the user from the response
                                    JSONObject userJson = obj.getJSONObject("user");

                                    //creating a new user object
                                    User user = new User(
                                            userJson.getInt("id"),
                                            userJson.getString("username"),
                                            userJson.getString("email"),
                                            userJson.getString("phone"),
                                            userJson.getString("last_login")
                                    );

                                    //storing the user in shared preferences
                                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                    //starting the profile activity
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), DashboardController.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginController.this, "Email or Password invalid", Toast.LENGTH_SHORT).show();
                        }
                    })

                    ;

            DataServiceDriver.getInstance(this).addToRequestQueue(request);
        }
        else{
            Toast.makeText(this, "Fill in the username and Email", Toast.LENGTH_SHORT).show();
        }

    }
}