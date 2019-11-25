package sk.stuba.fei.ikt.iktclient.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sk.stuba.fei.ikt.iktclient.api.RetroClient;
import sk.stuba.fei.ikt.iktclient.base.BaseActivity;
import sk.stuba.fei.ikt.iktclient.R;
import sk.stuba.fei.ikt.iktclient.model.Note;
import sk.stuba.fei.ikt.iktclient.model.ServerResponse;

public class DashboardActivity extends BaseActivity implements DashboardAdapter.DashboardHandler {

    private static final String TOKEN_CODE = "TOKEN_CODE";
    private String token;

    private RecyclerView rv;
    private DashboardAdapter dashboardAdapter;

    public static Intent getIntent(Context context, String token) {
        Intent intent = new Intent(context, DashboardActivity.class);
        intent.putExtra(TOKEN_CODE, token);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void bindViews() {
        rv = findViewById(R.id.rv);
    }

    @Override
    public int layoutXml() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = getIntent().getStringExtra(TOKEN_CODE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        RetroClient.getApiService().getNotes(new ServerResponse(token)).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                dashboardAdapter = new DashboardAdapter(DashboardActivity.this);
                dashboardAdapter.updateData(response.body());
                rv.setAdapter(dashboardAdapter);
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                Log.e("TAG", "IC DO PICI");
            }
        });

    }

    @Override
    public void onNoteDeleted(Note note) {
        note.setToken(token);
        RetroClient.getApiService().deleteNote(note).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                dashboardAdapter.updateData(response.body());
                dashboardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                internalError();
            }
        });
    }
}
