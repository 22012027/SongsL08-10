package sg.edu.rp.c346.id22012027.songsl08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class SongList extends AppCompatActivity {

    ListView listViewResults;
    ArrayAdapter<String> aa;
    ArrayList<Songs> al;
    CustomAdapter adapter;
    ToggleButton toggleButton5Stars;
    Button buttonBack;
    boolean asc= true;
    CustomAdapter customAdapter;
    CustomAdapter caFiltered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        listViewResults= findViewById(R.id.listViewResults);
        toggleButton5Stars = findViewById(R.id.toggleButton5Stars);
        buttonBack= findViewById(R.id.buttonBack);

        DBHelper db= new DBHelper(SongList.this);

        ArrayList<Songs> songs = db.getSongs();
        db.close();

        customAdapter= new CustomAdapter(this, R.layout.row, songs);

        toggleButton5Stars.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ArrayList<Songs> filteredList = new ArrayList<>();
                    for (int i = 0; i < db.getSongs().size(); i++) {
                        if (songs.get(i).getStar() == 5) {
                            filteredList.add(songs.get(i));
                        }
                        caFiltered= new CustomAdapter(SongList.this, R.layout.row, filteredList);
                        listViewResults.setAdapter(caFiltered);
                    }


                } else {
                    listViewResults.setAdapter(customAdapter);
                }
            }
        });

        listViewResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Songs song = songs.get(position);
                Intent i = new Intent(SongList.this, update.class);
                i.putExtra("song", song);
                startActivity(i);

            } });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SongList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper db= new DBHelper(SongList.this);

        ArrayList<Songs> songs= db.getSongs();
        db.close();

        songs.clear();
        songs.addAll(db.getSongs());

        customAdapter= new CustomAdapter(this, R.layout.row, songs);
        listViewResults.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
    }
}