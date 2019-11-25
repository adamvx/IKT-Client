package sk.stuba.fei.ikt.iktclient.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sk.stuba.fei.ikt.iktclient.base.BaseActivity;
import sk.stuba.fei.ikt.iktclient.dashboard.DashboardActivity;
import sk.stuba.fei.ikt.iktclient.R;

public class LoginActivity extends BaseActivity {

    private Button loginBtn;
    private EditText fillPassword;
    private EditText fillName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginBtn.setOnClickListener(v -> {
            if(fillName.getText().toString().equals("admin") && fillPassword.getText().toString().equals("admin")) {
                //TODO:  launch activity
                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(DashboardActivity.getIntent(LoginActivity.this));

            }else {
                Toast.makeText(getApplicationContext(), "Incorrect name or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void bindViews() {
        loginBtn = findViewById(R.id.login_btn);
        fillPassword = findViewById(R.id.fill_password);
        fillName = findViewById(R.id.fill_name);
    }

    @Override
    public int layoutXml() {
        return R.layout.activity_login;
    }
}