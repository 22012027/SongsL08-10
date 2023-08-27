package sg.edu.rp.c346.id22012027.songsl08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class update extends AppCompatActivity {

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
    Button buttonUpdate;
    Button buttonDelete;
    Songs song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

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
        buttonUpdate= findViewById(R.id.buttonUpdate);
        buttonDelete= findViewById(R.id.buttonSDelete);

        Intent i= getIntent();
        song= (Songs) i.getSerializableExtra("song");

        editTextSongTitle.setText(song.getTitle());
        editTextSinger.setText(song.getSinger());
        editTextYear.setText(song.getYear());
        int star= song.getStar();

        if(star == 1){
            radioButton1.setChecked(true);
        }
        else if(star == 2){
            radioButton2.setChecked(true);
        }
        else if(star == 3){
            radioButton3.setChecked(true);
        }
        else if(star == 4){
            radioButton4.setChecked(true);
        }
        else if(star == 5){
            radioButton5.setChecked(true);
        }

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh= new DBHelper(update.this);
                song.setTitle(editTextSongTitle.getText().toString());
                song.setYear(Integer.parseInt(editTextYear.getText().toString()));
                int newStars;

                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton1) {
                    newStars = 1;
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton2) {
                    newStars = 2;
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton3) {
                    newStars = 3;
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton4) {
                    newStars = 4;
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton5) {
                    newStars = 5;
                } else {
                    Toast.makeText(update.this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }

                song.setStar(newStars);

                dbh.updateSong(song);
                dbh.close();
                finish();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh= new DBHelper(update.this);
                dbh.deleteSong(song.getId());

                finish();
            }
        });
    }
}
