package sk.stuba.fei.ikt.iktclient.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sk.stuba.fei.ikt.iktclient.R;
import sk.stuba.fei.ikt.iktclient.api.RetroClient;
import sk.stuba.fei.ikt.iktclient.base.BaseActivity;
import sk.stuba.fei.ikt.iktclient.dashboard.DashboardActivity;
import sk.stuba.fei.ikt.iktclient.model.ServerResponse;
import sk.stuba.fei.ikt.iktclient.model.User;

public class LoginActivity extends BaseActivity {

    private Button loginBtn;
    private EditText fillPassword;
    private EditText fillName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginBtn.setOnClickListener(v -> login(fillName.getText().toString(), fillPassword.getText().toString()));
    }

    private void login(String email, String password) {
        RetroClient.getApiService().login(new User(email, password)).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.body() != null && response.body().getToken() != null) {
                    startActivity(DashboardActivity.getIntent(LoginActivity.this, response.body().getToken()));
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect name or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                internalError();
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