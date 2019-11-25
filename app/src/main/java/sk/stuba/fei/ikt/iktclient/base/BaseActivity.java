package sk.stuba.fei.ikt.iktclient.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutXml());
        bindViews();
    }

    public abstract void bindViews();

    public abstract int layoutXml();
}
