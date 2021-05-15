package co.ke.meritsystems.adcb_service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    public void ViewSubCounties(View view){
        String endpoint = URLs.URL_SUB_COUNTIES;

        StringRequest request = new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject data = new JSONObject(response);
                    JSONObject subCounties = data.getJSONObject("response");

                    result.setText(subCounties.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(SubCountyInspection.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result.setText(error.toString());
                Toast.makeText(SubCountyInspection.this, "There was an Error: "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                SessionManager sessionManager = new SessionManager(SubCountyInspection.this);
                HashMap<String, String> userDetails = sessionManager.getUserDetailsFromSession();

                String accesstoken = userDetails.get(SessionManager.KEY_ACCESSTOKEN);
                headers.put("Authorization", "Bearer " + accesstoken);
                return headers;
            }
        };
        DataServiceDriver.getInstance(SubCountyInspection.this).addToRequestQueue(request);

        Toast.makeText(this, "You are viewing Sub Counties", Toast.LENGTH_SHORT).show();
    }
}