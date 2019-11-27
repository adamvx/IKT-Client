package sk.stuba.fei.ikt.iktclient.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Base activity should be used on all application activities in order of retaining designed architecture
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutXml());
        bindViews();
    }

    public abstract void bindViews();

    public abstract int layoutXml();

    public void internalError() {
        Toast.makeText(getApplicationContext(), "Server offline!", Toast.LENGTH_SHORT).show();
    }
}
