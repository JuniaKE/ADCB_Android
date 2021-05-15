package co.ke.meritsystems.adcb_service_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.ke.meritsystems.adcb_service_app.models.Subcounty;

public class SubCountyListing extends AppCompatActivity {
    RecyclerView recyclerView;
    DataAdapter adapter;
    List<Subcounty> subCountyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_county_listing);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subCountyList = new ArrayList<>();
        getSubCounties();
    }

    private void getSubCounties() {
        String endpoint = URLs.URL_SUB_COUNTIES;
        StringRequest request = new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject data = new JSONObject(response);
                    JSONArray jsonArray = data.getJSONArray("subcounties");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = null;
                        obj = jsonArray.getJSONObject(i);
                        Subcounty subcounty = new Subcounty();
                        subcounty.setId(obj.getInt("id"));
                        subcounty.setSub_county_name(obj.getString("sub_county_name"));
                        subcounty.setSub_county_requests(obj.getInt("sub_county_requests"));
                        subCountyList.add(subcounty);
                    }
                    adapter = new DataAdapter(subCountyList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SubCountyListing.this, "There was an Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                SessionManager sessionManager = new SessionManager(SubCountyListing.this);
                HashMap<String, String> userDetails = sessionManager.getUserDetailsFromSession();
                String accesstoken = userDetails.get(SessionManager.KEY_ACCESSTOKEN);
                headers.put("Authorization", "Bearer " + accesstoken);
                return headers;
            }
        };
        DataServiceDriver.getInstance(SubCountyListing.this).addToRequestQueue(request);
    }
}