package co.ke.meritsystems.adcb_service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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


public class SubCountyInspection extends AppCompatActivity {

    Button btn_sc;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_county_inspection);
        btn_sc = (Button) findViewById(R.id.btn_sc);
        result = (TextView) findViewById(R.id.response);

    }

    public void ViewSubCounties(View view) {

        startActivity(new Intent(getApplicationContext(), SubCountyListing.class));
        finish();
        Toast.makeText(this, "You are viewing Sub Counties", Toast.LENGTH_SHORT).show();
    }
}