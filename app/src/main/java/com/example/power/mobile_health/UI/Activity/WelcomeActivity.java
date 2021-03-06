package com.example.power.mobile_health.UI.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.power.mobile_health.R;
import com.example.power.mobile_health.UI.Activity.LoginMainActivity;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enterLoginActivity();
            }
        },3000);
    }

    private void enterLoginActivity(){
        Intent intent = new Intent(this, LoginMainActivity.class);
        startActivity(intent);
        finish();
    }
}
