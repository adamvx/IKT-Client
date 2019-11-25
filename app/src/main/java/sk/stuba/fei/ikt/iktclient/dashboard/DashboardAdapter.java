package sk.stuba.fei.ikt.iktclient.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sk.stuba.fei.ikt.iktclient.R;
import sk.stuba.fei.ikt.iktclient.model.Note;


public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private DashboardHandler handler;

    private List<Note> data;
    private Context context;

    DashboardAdapter(Context context) {
        this.data = new ArrayList<>();
        this.context = context;
        this.handler = (DashboardHandler) context;
    }

    void updateData(List<Note> notes) {
        this.data = notes;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardAdapter.ViewHolder holder, int position) {
        holder.heading.setText(data.get(position).getHeading());
        holder.text.setText(data.get(position).getMessage());
    }

    @NonNull
    @Override
    public DashboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    interface DashboardHandler {
        void onNoteDeleted(Note note);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView heading, text;
        ImageView more;

        ViewHolder(View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.heading);
            text = itemView.findViewById(R.id.text);
            more = itemView.findViewById(R.id.more);

            more.setOnClickListener(this::showPopup);
        }

        private void showPopup(View anchor) {
            PopupMenu popup = new PopupMenu(context, anchor);
            popup.getMenuInflater().inflate(R.menu.dashboard_popup, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.action_delete) {
                    handler.onNoteDeleted(data.get(getAdapterPosition()));
                    return true;
                }
                return false;
            });

            popup.show();
        }

    }
}
