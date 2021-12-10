package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.NotDone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class NemIdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nem_id);

        ImageView image = findViewById(R.id.mockupnemid);
        image.setImageResource(R.mipmap.mockupnemid);
        TextView text = findViewById(R.id.text_nemId);
        text.setText(R.string.not_implemented);

    }
}