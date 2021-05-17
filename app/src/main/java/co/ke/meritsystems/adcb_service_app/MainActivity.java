package co.ke.meritsystems.adcb_service_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_SCREEN = 5000;
    // Variables
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView powered, welcome, version, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        // Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Add the Hooks to Animation
        image = findViewById(R.id.imageView);
        welcome = findViewById(R.id.welcome);
        powered = findViewById(R.id.powered);
        about = findViewById(R.id.about);
        version = findViewById(R.id.version);

        //Set Animations
        if (bottomAnim != null || topAnim != null) {
            image.setAnimation(topAnim);
            welcome.setAnimation(bottomAnim);
            powered.setAnimation(topAnim);
            version.setAnimation(topAnim);
            about.setAnimation(bottomAnim);
        }
        
        new Handler().postDelayed(() -> {
            SessionManager sessionManager = new SessionManager(MainActivity.this);
            HashMap<String, String> userDetails = sessionManager.getUserDetailsFromSession();

            String name = userDetails.get(SessionManager.KEY_NAME);
            if (name == null || name.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, LoginController.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(MainActivity.this, DashboardController.class);
                startActivity(intent);
                finish();
                Toast.makeText(MainActivity.this, "Logged in as " + name, Toast.LENGTH_SHORT).show();
            }

        }, SPLASH_SCREEN);
    }
}