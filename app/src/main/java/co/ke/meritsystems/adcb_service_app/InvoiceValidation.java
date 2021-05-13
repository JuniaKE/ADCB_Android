package co.ke.meritsystems.adcb_service_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class InvoiceValidation extends AppCompatActivity {

    public TextView result_title;
    public String serial = "";
    public Button btn_scan;
    public TextView txt_application_serial;
    public TextView txt_invoice_serial;
    public TextView txt_applicant_name;
    public TextView txt_business_name;
    public TextView txt_bill_amount;
    public TextView txt_balance;
    public TextView txt_financial_year;
    public TextView txt_sub_county_name;
    public TextView txt_ward_name;
    public TextView txt_license_type;
    public TextView txt_phone;
    public TextView txt_service_name;
    public TextView txt_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_validation);
        result_title = (TextView) findViewById(R.id.result_title);
        txt_application_serial = (TextView) findViewById(R.id.txt_application_serial);
        txt_type = (TextView) findViewById(R.id.txt_type);
        txt_invoice_serial = (TextView) findViewById(R.id.txt_invoice_serial);
        txt_business_name = (TextView) findViewById(R.id.txt_business_name);
        txt_applicant_name = (TextView) findViewById(R.id.txt_applicant_name);
        txt_bill_amount = (TextView) findViewById(R.id.txt_bill_amount);
        txt_balance = (TextView) findViewById(R.id.txt_balance);
        txt_financial_year = (TextView) findViewById(R.id.txt_financial_year);
        txt_sub_county_name = (TextView) findViewById(R.id.txt_sub_county_name);
        txt_ward_name = (TextView) findViewById(R.id.txt_ward_name);
        txt_license_type = (TextView) findViewById(R.id.txt_license_type);
        txt_service_name = (TextView) findViewById(R.id.txt_service_name);
        txt_phone = (TextView) findViewById(R.id.txt_phone);
        btn_scan = (Button) findViewById(R.id.btn_scan);
    }
    public void ScanInvoice(View view){
        // Set Results to text views
        result_title.setText("Scan the Invoice to Validate. Not Scanned Yet");
        txt_application_serial.setText("");
        txt_invoice_serial.setText("");
        txt_business_name.setText("");
        txt_applicant_name.setText("");
        txt_type.setText("");
        txt_bill_amount.setText("");
        txt_balance.setText("");
        txt_financial_year.setText("");
        txt_ward_name.setText("");
        txt_sub_county_name.setText("");
        txt_phone.setText("");
        txt_license_type.setText("");
        txt_service_name.setText("");

        btn_scan.setText("Scan Invoice to Validate");
        result_title.setAllCaps(false);
        btn_scan.setEnabled(true);

        // Use the Scanner Library
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setTorchEnabled(false);
        intentIntegrator.setCameraId(0);
        intentIntegrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null){
            if (intentResult.getContents() == null){
                result_title.setText("Scan Cancelled by User");
                btn_scan.setText("Canceled. Scan Again");
                result_title.setAllCaps(false);
                btn_scan.setEnabled(true);

            }

            else {
                result_title.setText("We are validating Invoice Serial NO: "+intentResult.getContents().toString()+". Please wait");
                Toast.makeText(this, "We are validating Invoice Serial NO: "+intentResult.getContents().toString()+". Please wait", Toast.LENGTH_SHORT).show();
                btn_scan.setText("Validating... Please Wait");
                btn_scan.setEnabled(false);
                // Store the scan serial to the variable serial
                serial = intentResult.getContents().toString();
                // Start the query to the api end point

                RequestQueue queue = Volley.newRequestQueue(InvoiceValidation.this);

                String endpoint = URLs.URL_INVOICE_VALIDATION + serial;

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, endpoint,null,
                        new Response.Listener<JSONArray>(){
                            @Override
                            public void onResponse(JSONArray response) {
                                String application_id = "";
                                String invoice_serial = "";
                                String business_name = "";
                                String applicant_name = "";
                                String type = "";
                                String bill_amount = "";
                                String financial_year = "";
                                String ward_name = "";
                                String sub_county_name = "";
                                String phone = "";
                                String license_type = "";
                                String balance = "";
                                String service_name = "";
                                try {
                                    JSONObject invoice = response.getJSONObject(0);
                                    application_id = invoice.getString("application_serial");
                                    invoice_serial = invoice.getString("serial");
                                    business_name = invoice.getString("business_name");
                                    applicant_name = invoice.getString("name");
                                    type = invoice.getString("type");
                                    bill_amount = invoice.getString("bill_amount");
                                    balance = invoice.getString("balance");
                                    financial_year = invoice.getString("financial_year");
                                    ward_name = invoice.getString("ward_name");
                                    sub_county_name = invoice.getString("sub_county_name");
                                    phone = invoice.getString("phone");
                                    license_type = invoice.getString("license_type");
                                    service_name = invoice.getString("service_name");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // Set Results to text views
                                txt_application_serial.setText("APPLICATION SERIAL: "+application_id);
                                txt_invoice_serial.setText("INVOICE SERIAL: "+invoice_serial);
                                txt_business_name.setText("BUSINESS: "+business_name);
                                txt_applicant_name.setText("BUSINESS OWNER: "+applicant_name);
                                txt_type.setText("INVOICE TYPE: "+type);
                                txt_bill_amount.setText("AMOUNT INVOICES: KSHS. "+bill_amount);
                                txt_balance.setText("PENDING BALANCE: KSHS. "+balance);
                                txt_financial_year.setText("FINANCIAL YEAR: "+financial_year);
                                txt_ward_name.setText("WARD: "+ward_name);
                                txt_sub_county_name.setText("SUB COUNTY: "+sub_county_name);
                                txt_phone.setText("PHONE NUMBER: "+phone);
                                txt_license_type.setText("LICENSE TYPE: "+license_type);
                                txt_service_name.setText("LICENSE OPTION: "+service_name);

                                result_title.setText("The serial NO: "+serial+" matches "+business_name+"'s application no "+application_id);
                                btn_scan.setEnabled(true);

                                result_title.setAllCaps(true);
                                Toast.makeText(InvoiceValidation.this,"Found! There is a match in the system" , Toast.LENGTH_SHORT).show();
                                btn_scan.setText("Completed. Scan another Invoice");
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        result_title.setText("The Invoice Serial NO: "+serial+" did not match any invoice in the system. Try again!");
                        Toast.makeText(InvoiceValidation.this, "The Invoice Serial NO: "+serial+" did not match any invoice in the system. Try again!", Toast.LENGTH_SHORT).show();
                        btn_scan.setText("Not Found. Scan Again");
                        btn_scan.setEnabled(true);
                    }
                });
            queue.add(request);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}