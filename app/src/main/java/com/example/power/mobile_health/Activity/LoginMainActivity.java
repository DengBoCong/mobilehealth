package com.example.power.mobile_health.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.power.mobile_health.R;

public class LoginMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        TextView textView = (TextView)findViewById(R.id.tv_visitor_login);
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    public void enterPhoneLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void enterRegister(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    public void enterVisitorLogin(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_wechat:
                Toast.makeText(this, "抱歉，暂不支持第三方登录！", Toast.LENGTH_LONG).show();
                break;
            case R.id.iv_qq:
                Toast.makeText(this, "抱歉，暂不支持第三方登录！", Toast.LENGTH_LONG).show();
                break;
            case R.id.iv_sina:
                Toast.makeText(this, "抱歉，暂不支持第三方登录！", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
