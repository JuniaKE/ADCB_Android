package co.ke.meritsystems.adcb_service_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class DashboardController extends AppCompatActivity {

    public TextView welcome;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        welcome = (TextView) findViewById(R.id.welcome_dashboard);

        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> userDetails = sessionManager.getUserDetailsFromSession();

        String name = userDetails.get(SessionManager.KEY_NAME);
        if (name == null || name.isEmpty()) {
            startActivity(new Intent(this, LoginController.class));
            finish();
        }
        else {
            welcome.setText("Hello "+ name +", Welcome back");
            welcome.setAllCaps(false);
        }
    }


    public void Verification(View view) {
        Intent intent = new Intent(DashboardController.this, VerificationMain.class);
        startActivity(intent);
    }

    public void InspectionMain(View view) {
        Intent intent = new Intent(DashboardController.this, SubCountyInspection.class);
        startActivity(intent);
    }
}
