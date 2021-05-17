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

import co.ke.meritsystems.adcb_service_app.adapters.SubCountyAdapter;
import co.ke.meritsystems.adcb_service_app.adapters.SubCountyWardAdapter;
import co.ke.meritsystems.adcb_service_app.models.SubCounty;
import co.ke.meritsystems.adcb_service_app.models.SubCountyWards;

public class SubCountyWardsListing extends AppCompatActivity {
    RecyclerView recyclerView;
    SubCountyWardAdapter adapter;
    List<SubCountyWards> subCountyWardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_county_ward_listing);
        recyclerView = findViewById(R.id.recyclerViewWards);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subCountyWardList = new ArrayList<>();
        getSubCountyWards();
    }

    private void getSubCountyWards() {
        String endpoint = URLs.URL_SUB_COUNTY_WARDS + "?sub_county_id=" + getIntent().getStringExtra("ID");
        StringRequest request = new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject data = new JSONObject(response);
                    JSONArray jsonArray = data.getJSONArray("wards");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = null;
                        obj = jsonArray.getJSONObject(i);
                        SubCountyWards subcounty = new SubCountyWards();
                        subcounty.setId(obj.getInt("id"));
                        subcounty.setWard_name(obj.getString("ward_name"));
                        subcounty.setWard_request_count(obj.getInt("ward_request_count"));
                        subCountyWardList.add(subcounty);
                    }
                    adapter = new SubCountyWardAdapter(subCountyWardList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SubCountyWardsListing.this, "There was an Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                SessionManager sessionManager = new SessionManager(SubCountyWardsListing.this);
                HashMap<String, String> userDetails = sessionManager.getUserDetailsFromSession();
                //headers.put("sub_county_id", getIntent().getStringExtra("ID"));
                String accesstoken = userDetails.get(SessionManager.KEY_ACCESSTOKEN);
                headers.put("Authorization", "Bearer " + accesstoken);
                return headers;
            }
        };
        DataServiceDriver.getInstance(SubCountyWardsListing.this).addToRequestQueue(request);
    }
}