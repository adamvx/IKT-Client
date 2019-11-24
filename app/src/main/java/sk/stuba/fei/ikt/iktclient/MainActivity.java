package sk.stuba.fei.ikt.iktclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void launchActivity() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
