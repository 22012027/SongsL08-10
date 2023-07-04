package sg.edu.rp.c346.id22012027.songsl08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textViewSongTitle;
    EditText editTextSongTitle;
    TextView textViewSinger;
    EditText editTextSinger;
    TextView textViewYear;
    EditText editTextYear;
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    RadioButton radioButton5;
    Button buttonInsert;
    Button buttonShowList;
    TextView textViewResults;
    ListView listView;
    ArrayAdapter<String> aa;
    ArrayList<Songs> al;
    boolean asc= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewSongTitle= findViewById(R.id.textViewSongTitle);
        editTextSongTitle= findViewById(R.id.editTextSongTitle);
        textViewSinger= findViewById(R.id.textViewSinger);
        editTextSinger= findViewById(R.id.editTextSinger);
        textViewYear= findViewById(R.id.textViewYear);
        editTextYear= findViewById(R.id.editTextYear);
        radioGroup= findViewById(R.id.radioGroup);
        radioButton1= findViewById(R.id.radioButton1);
        radioButton2= findViewById(R.id.radioButton2);
        radioButton3= findViewById(R.id.radioButton3);
        radioButton4= findViewById(R.id.radioButton4);
        radioButton5= findViewById(R.id.radioButton5);
        buttonInsert= findViewById(R.id.buttonInsert);
        buttonShowList= findViewById(R.id.buttonShowList);
        textViewResults=findViewById(R.id.textViewResults);
        listView= findViewById(R.id.listView);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db= new DBHelper(MainActivity.this);
                db.insertSong(editTextSongTitle.getText().toString(), editTextSinger.getText().toString(), Integer.parseInt(editTextYear.getText().toString());
                db.close();
            }
        });

        buttonShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db2 = new DBHelper(MainActivity.this);
                al = db2.getSongs();
                db2.close();
                asc = !asc;
                aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, al);
                listView.setAdapter(aa);
            }
        });
    }
}