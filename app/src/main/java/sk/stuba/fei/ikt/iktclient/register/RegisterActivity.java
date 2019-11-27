package sk.stuba.fei.ikt.iktclient.register;

import android.content.Context;
import android.content.Intent;
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
import sk.stuba.fei.ikt.iktclient.login.LoginActivity;
import sk.stuba.fei.ikt.iktclient.managers.StorageManager;
import sk.stuba.fei.ikt.iktclient.model.ServerResponse;
import sk.stuba.fei.ikt.iktclient.model.User;

public class RegisterActivity extends BaseActivity {

    private Button register;
    private EditText password;
    private EditText email;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        register.setOnClickListener(v -> login(email.getText().toString(), password.getText().toString()));
    }

    private void login(String email, String password) {
        if (email.isEmpty()) {
            this.email.setError("This field is required!");
            return;
        }
        if (password.isEmpty()) {
            this.password.setError("This field is required!");
            return;
        }
        RetroClient.getApiService().register(new User(email, password)).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.body() != null && response.body().getToken() != null) {
                    StorageManager.getInstance().setToken(RegisterActivity.this, response.body().getToken());
                    startActivity(DashboardActivity.getIntent(RegisterActivity.this));
                    finishAffinity();
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
        register = findViewById(R.id.login);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
    }

    @Override
    public int layoutXml() {
        return R.layout.activity_register;
    }

}
