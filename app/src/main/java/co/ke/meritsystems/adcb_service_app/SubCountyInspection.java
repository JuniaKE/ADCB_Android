package co.ke.meritsystems.adcb_service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

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

    public void ViewSubCounties(View view){
        String endpoint = "https://licensing.meritsystems.co.ke/api/V1/inspections/subcounties/requests";
//        JSONObject headers = new JSONObject();
//        try {
//            //input Email and Password as parameters
//            headers.put("Authorization", "Bearer " + CommonUtils.getInstance().getSharedPrefString(ServiceConstant.User.TOKEN));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, endpoint, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                result.setText(response.toString());
                Toast.makeText(SubCountyInspection.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result.setText(error.toString());
                Toast.makeText(SubCountyInspection.this, "There was an Error: "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        request.setRetryPolicy(policy);
        DataServiceDriver.getInstance(SubCountyInspection.this).addToRequestQueue(request);

        Toast.makeText(this, "You are viewing Sub Counties", Toast.LENGTH_SHORT).show();
    }
}