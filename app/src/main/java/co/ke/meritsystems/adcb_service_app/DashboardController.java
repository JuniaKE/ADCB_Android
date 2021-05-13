package co.ke.meritsystems.adcb_service_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardController extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
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
