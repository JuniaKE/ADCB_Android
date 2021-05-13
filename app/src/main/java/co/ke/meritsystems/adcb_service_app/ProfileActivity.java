package co.ke.meritsystems.adcb_service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView txtUserID, txtUsername, txtEmail, txtPhone, txtLastLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtUserID = (TextView) findViewById(R.id.txtUserID);
        txtUsername = (TextView) findViewById(R.id.txtUsername);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtPhone = (TextView) findViewById(R.id.txtPhone);


        //getting the current user
        User user = SharedPrefManager.getInstance(this).getUser();
        //setting the values to the text views
        txtUserID.setText(String.valueOf(user.getId()));
        txtUsername.setText(user.getName());
        txtEmail.setText(user.getEmail());
        txtPhone.setText(user.getPhone());
        txtLastLogin.setText(user.getLast_login());

        //when the user presses logout button
        //calling the logout method
        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });
    }
}