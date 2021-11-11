package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.NotDone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class MoodGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_graph);

        ImageView image = findViewById(R.id.mockupmoodgraph);
        image.setImageResource(R.mipmap.moodoverview);
        TextView text = findViewById(R.id.text_moodgraph);
        text.setText(R.string.not_implemented);

    }

}