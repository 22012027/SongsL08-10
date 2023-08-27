package sg.edu.rp.c346.id22012027.songsl08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Songs> {
    Context context;
    ArrayList<Songs> al;
    int resource;

    public CustomAdapter(Context context, int resource, ArrayList<Songs> al) {
        super(context, resource, al);
        this.context = context;
        this.resource = resource;
        this.al = al;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(resource, parent, false);

        TextView textViewSongTitle2 = rowView.findViewById(R.id.textViewSongTitle2);
        TextView textViewSinger2 = rowView.findViewById(R.id.textViewSinger2);
        TextView textViewYear = rowView.findViewById(R.id.textViewYear);
        TextView textViewStars2 = rowView.findViewById(R.id.textViewStars2);

        Songs currentVersion = al.get(position);

        textViewSongTitle2.setText(currentVersion.getTitle());
        textViewSinger2.setText(currentVersion.getSinger());
        textViewYear.setText(currentVersion.getYear());
        textViewStars2.setText(currentVersion.getStar());

        return rowView;
    }
}
