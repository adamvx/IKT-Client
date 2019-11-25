package sk.stuba.fei.ikt.iktclient.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sk.stuba.fei.ikt.iktclient.R;
import sk.stuba.fei.ikt.iktclient.model.Note;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private List<Note> data;
    private Context context;

    DashboardAdapter(Context context, List<Note> notes) {
        this.data = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public DashboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView heading, text;
        ImageView more;

        ViewHolder(View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.heading);
            text = itemView.findViewById(R.id.text);
            more = itemView.findViewById(R.id.more);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
