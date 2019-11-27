package sk.stuba.fei.ikt.iktclient.dashboard;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sk.stuba.fei.ikt.iktclient.R;
import sk.stuba.fei.ikt.iktclient.add_note.AddNoteActivity;
import sk.stuba.fei.ikt.iktclient.api.RetroClient;
import sk.stuba.fei.ikt.iktclient.base.BaseActivity;
import sk.stuba.fei.ikt.iktclient.edit_note.EditNoteActivity;
import sk.stuba.fei.ikt.iktclient.managers.StorageManager;
import sk.stuba.fei.ikt.iktclient.model.Note;
import sk.stuba.fei.ikt.iktclient.model.ServerResponse;
import sk.stuba.fei.ikt.iktclient.welcome.WelcomeActivity;

public class DashboardActivity extends BaseActivity implements DashboardAdapter.DashboardHandler {

    private static final int NOTE_REQUEST_CODE = 1;

    private RecyclerView rv;
    private DashboardAdapter dashboardAdapter;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, DashboardActivity.class);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        setupSearch(searchView);


        return true;
    }

    private void setupSearch(SearchView searchView) {

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dashboardAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                dashboardAdapter.getFilter().filter(query);
                return false;
            }
        });

        searchView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {

            @Override
            public void onViewDetachedFromWindow(View arg0) {
                dashboardAdapter.getFilter().filter(null);
            }

            @Override
            public void onViewAttachedToWindow(View arg0) {
                dashboardAdapter.getFilter().filter(null);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                startActivityForResult(AddNoteActivity.getIntent(this), NOTE_REQUEST_CODE);
                return true;
            case R.id.action_sign_out:
                StorageManager.getInstance().setToken(this, null);
                startActivity(WelcomeActivity.getIntent(this));
                finish();
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        requestNotes();

    }

    private void requestNotes() {
        String token = StorageManager.getInstance().getToken(this);
        RetroClient.getApiService().getNotes(new ServerResponse(token)).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                dashboardAdapter = new DashboardAdapter(DashboardActivity.this);
                dashboardAdapter.updateData(response.body());
                rv.setAdapter(dashboardAdapter);
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                internalError();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NOTE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            requestNotes();
        }
    }

    @Override
    public void onNoteDeleted(Note note) {
        String token = StorageManager.getInstance().getToken(this);
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

    @Override
    public void onNoteEdited(Note note) {
        startActivityForResult(EditNoteActivity.getIntent(this, note.getId(), note.getHeading(), note.getMessage()), NOTE_REQUEST_CODE);
    }
}
