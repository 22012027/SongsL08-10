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

public class MainActivity extends AppCompatActivity {
    TextView textViewSongTitle;
    EditText editTextSongTitle;
    TextView textViewSinger;
    EditText editTextSinger;
    TextView textViewYear;
    EditText editTextYear;
    RadioGroup radioGroup;
    Button buttonInsert;
    Button buttonShowList;

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
        buttonInsert= findViewById(R.id.buttonUpdate);
        buttonShowList= findViewById(R.id.buttonDelete);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int star;

                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton1) {
                    star = 1;
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton2) {
                    star = 2;
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton3) {
                    star = 3;
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton4) {
                    star = 4;
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton5) {
                    star = 5;
                } else {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper db= new DBHelper(MainActivity.this);

                if (editTextSongTitle.getText().toString().trim().length() != 0 && editTextSinger.getText().toString().trim().length() != 0 && editTextYear.getText().toString().trim().length() != 0 && radioGroup.getCheckedRadioButtonId() != -1) {
                    db.insertSong(editTextSongTitle.getText().toString(), editTextSinger.getText().toString(), Integer.parseInt(editTextYear.getText().toString()), star);
                } else {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (MainActivity.this, SongList.class);
                startActivity(intent);
            }
        });
    }
}