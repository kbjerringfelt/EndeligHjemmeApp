package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.NotDone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class MoodGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_graph);

        ImageView image = findViewById(R.id.mockupchecklist);
        image.setImageResource(R.mipmap.moodoverview);
        TextView text = findViewById(R.id.text_checklist);
        text.setText(R.string.not_implemented);

    }

}