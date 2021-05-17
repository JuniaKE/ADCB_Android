package co.ke.meritsystems.adcb_service_app;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.ke.meritsystems.adcb_service_app.adapters.WardRequestsAdapter;
import co.ke.meritsystems.adcb_service_app.models.WardRequests;

public class WardRequestListing extends AppCompatActivity {
    RecyclerView recyclerView;
    WardRequestsAdapter adapter;
    List<WardRequests> WardRequestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ward_request_listing);
        recyclerView = findViewById(R.id.recyclerViewWardRequests);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        WardRequestList = new ArrayList<>();
        getWardRequests();
    }

    private void getWardRequests() {
        String endpoint = URLs.URL_WARD_REQUESTS + "?ward_id=" + getIntent().getStringExtra("ID");
        StringRequest request = new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject data = new JSONObject(response);
                    JSONArray jsonArray = data.getJSONArray("requests");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = null;
                        obj = jsonArray.getJSONObject(i);
                        WardRequests wardRequests = new WardRequests();
                        wardRequests.setId(obj.getInt("id"));
                        wardRequests.setBusiness_name(obj.getString("business_name"));
                        wardRequests.setPhone(obj.getString("phone"));
                        wardRequests.setInspection_id(Integer.parseInt(obj.getString("inspection_id")));
                        WardRequestList.add(wardRequests);
                    }
                    adapter = new WardRequestsAdapter(WardRequestList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WardRequestListing.this, "There was an error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                SessionManager sessionManager = new SessionManager(WardRequestListing.this);
                HashMap<String, String> userDetails = sessionManager.getUserDetailsFromSession();
                String accesstoken = userDetails.get(SessionManager.KEY_ACCESSTOKEN);
                headers.put("Authorization", "Bearer " + accesstoken);
                return headers;
            }
        };
        DataServiceDriver.getInstance(WardRequestListing.this).addToRequestQueue(request);
    }
}