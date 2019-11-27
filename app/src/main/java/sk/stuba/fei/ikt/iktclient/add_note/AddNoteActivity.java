package sk.stuba.fei.ikt.iktclient.add_note;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sk.stuba.fei.ikt.iktclient.R;
import sk.stuba.fei.ikt.iktclient.api.RetroClient;
import sk.stuba.fei.ikt.iktclient.base.BaseActivity;
import sk.stuba.fei.ikt.iktclient.dashboard.DashboardActivity;
import sk.stuba.fei.ikt.iktclient.managers.StorageManager;
import sk.stuba.fei.ikt.iktclient.model.Note;

public class AddNoteActivity extends BaseActivity {

    private EditText title, message;
    private Button save;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, AddNoteActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        save.setOnClickListener(v -> saveNote());
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void saveNote() {
        String heading = title.getText().toString();
        if (heading.isEmpty()) {
            title.setError("This field is required!");
            return;
        }
        String text = message.getText().toString();
        if (text.isEmpty()) {
            message.setError("This field is required!");
            return;
        }

        String token = StorageManager.getInstance().getToken(this);
        RetroClient.getApiService().createNote(new Note(heading, text, token)).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                setResult(Activity.RESULT_OK);
                finish();
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                internalError();
            }
        });
    }

    @Override
    public void bindViews() {
        title = findViewById(R.id.title);
        message = findViewById(R.id.message);
        save = findViewById(R.id.save);
    }

    @Override
    public int layoutXml() {
        return R.layout.activity_add_note;
    }
}
