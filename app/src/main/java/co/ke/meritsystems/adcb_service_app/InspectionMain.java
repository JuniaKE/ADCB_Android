package co.ke.meritsystems.adcb_service_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.volley.Request.Method.GET;

public class InspectionMain extends AppCompatActivity {

    private ArrayList<SubCounty> subCountiesList;
    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_main);

        ActivityCompat.requestPermissions(InspectionMain.this, new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION
                },PackageManager.PERMISSION_GRANTED);
        btnLoad = (Button) findViewById(R.id.btn_counties);
        subCountiesList = new ArrayList<>();

    }

    private void setSubCountyInfo(View view) {
        String endpoint = "https://licensing.meritsystems.co.ke/api/V1/inspections/subcounties/requests";
        JsonObjectRequest sub_counties = new JsonObjectRequest(Request.Method.GET, endpoint, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(InspectionMain.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InspectionMain.this, "An Error Occurred:" +error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        DataServiceDriver.getInstance(InspectionMain.this).addToRequestQueue(sub_counties);
    }
}