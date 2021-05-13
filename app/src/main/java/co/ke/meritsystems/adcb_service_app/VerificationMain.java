package co.ke.meritsystems.adcb_service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VerificationMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_main);
    }
    public void LicenseValidation(View view){
        Intent intent = new Intent(VerificationMain.this, LicenseValidation.class);
        startActivity(intent);
    }
    public void InvoiceValidation(View view){
        Intent intent = new Intent(VerificationMain.this, InvoiceValidation.class);
        startActivity(intent);
    }
    public void ReceiptValidation(View view){
        Intent intent = new Intent(VerificationMain.this, ReceiptValidation.class);
        startActivity(intent);
    }
}