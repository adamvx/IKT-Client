package sk.stuba.fei.ikt.iktclient.welcome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import sk.stuba.fei.ikt.iktclient.R;
import sk.stuba.fei.ikt.iktclient.base.BaseActivity;
import sk.stuba.fei.ikt.iktclient.dashboard.DashboardActivity;
import sk.stuba.fei.ikt.iktclient.login.LoginActivity;
import sk.stuba.fei.ikt.iktclient.managers.StorageManager;
import sk.stuba.fei.ikt.iktclient.register.RegisterActivity;

/**
 * Initial activity that will be presented to user is he not singed in.
 */
public class WelcomeActivity extends BaseActivity {

    private Button login, register;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (StorageManager.getInstance().getToken(this) != null) {
            startActivity(DashboardActivity.getIntent(this));
            finish();
        }

        login.setOnClickListener(v -> startActivity(LoginActivity.getIntent(WelcomeActivity.this)));
        register.setOnClickListener(v -> startActivity(RegisterActivity.getIntent(WelcomeActivity.this)));
    }

    @Override
    public void bindViews() {
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
    }

    @Override
    public int layoutXml() {
        return R.layout.activity_welcome;
    }
}
