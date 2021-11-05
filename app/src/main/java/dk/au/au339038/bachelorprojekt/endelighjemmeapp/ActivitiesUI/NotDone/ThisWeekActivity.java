package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.NotDone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class ThisWeekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_week);

        ImageView image = findViewById(R.id.mockupweek);
        image.setImageResource(R.mipmap.nuvaerendeuge);
        TextView text = findViewById(R.id.text_week);
        text.setText(R.string.not_implemented);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_top,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.bar_menu) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}