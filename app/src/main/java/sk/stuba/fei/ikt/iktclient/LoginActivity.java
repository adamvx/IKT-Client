package sk.stuba.fei.ikt.iktclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn = (Button)findViewById(R.id.LoginBtn);
    EditText fillPassword = (EditText)findViewById(R.id.FillPassword);
    EditText fillName = (EditText)findViewById(R.id.FillName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                if(fillName.getText().toString().equals("admin") && fillPassword.getText().toString().equals("admin")) {
                    // launch activity
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT);

                }else {
                    Toast.makeText(getApplicationContext(), "Incorrect name or password", Toast.LENGTH_SHORT);
                }
            }
            });
    }
}