package co.ke.meritsystems.adcb_service_app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BusinessInspection extends AppCompatActivity {
    TextView txtBusName, txtbusPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_inspection);
        txtBusName = (TextView) findViewById(R.id.txtbizName);
        txtBusName.setText(getIntent().getStringExtra("bizname"));
        txtbusPhone = (TextView) findViewById(R.id.txtBizPhone);
        txtbusPhone.setText(getIntent().getStringExtra("bizphone"));

        //LoadData();
    }

    private void LoadData() {
        String endpoint = URLs.URL_REQUEST + "?inspection_id=" + getIntent().getStringExtra("ID");
        StringRequest request = new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject data = new JSONObject(response);
                    JSONArray jsonArray = data.getJSONArray("requests");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = null;
                        obj = jsonArray.getJSONObject(i);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BusinessInspection.this, "There was an error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                SessionManager sessionManager = new SessionManager(BusinessInspection.this);
                HashMap<String, String> userDetails = sessionManager.getUserDetailsFromSession();
                String accesstoken = userDetails.get(SessionManager.KEY_ACCESSTOKEN);
                headers.put("Authorization", "Bearer " + accesstoken);
                return headers;
            }
        };
        DataServiceDriver.getInstance(BusinessInspection.this).addToRequestQueue(request);
    }

    public void InspectionMain(View view) {

    }
}