package sk.stuba.fei.ikt.iktclient.edit_note;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sk.stuba.fei.ikt.iktclient.R;
import sk.stuba.fei.ikt.iktclient.api.RetroClient;
import sk.stuba.fei.ikt.iktclient.base.BaseActivity;
import sk.stuba.fei.ikt.iktclient.managers.StorageManager;
import sk.stuba.fei.ikt.iktclient.model.Note;

/**
 * Activity for editing note. Layout is shared with AddNoteActivity
 *
 * @see sk.stuba.fei.ikt.iktclient.add_note.AddNoteActivity
 */
public class EditNoteActivity extends BaseActivity {

    private static final String ID_KEY = "ID_KEY";
    private static final String TITLE_KEY = "TITLE_KEY";
    private static final String MESSAGE_KEY = "MESSAGE_KEY";

    private int id = 0;
    private EditText title, message;
    private Button save;


    public static Intent getIntent(Context context, int id, String title, String message) {
        Intent intent = new Intent(context, EditNoteActivity.class);
        intent.putExtra(ID_KEY, id);
        intent.putExtra(TITLE_KEY, title);
        intent.putExtra(MESSAGE_KEY, message);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getIntent().getIntExtra(ID_KEY, 0);
        title.setText(getIntent().getStringExtra(TITLE_KEY));
        message.setText(getIntent().getStringExtra(MESSAGE_KEY));


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
        RetroClient.getApiService().editNote(new Note(id, heading, text, token)).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                setResult(Activity.RESULT_OK);
                finish();
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

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
