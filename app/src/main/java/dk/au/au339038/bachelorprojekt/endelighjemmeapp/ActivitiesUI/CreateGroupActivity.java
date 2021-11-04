package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class CreateGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        ImageView image = findViewById(R.id.mockupcreategroup);
        image.setImageResource(R.mipmap.opretgruppe);
        TextView text = findViewById(R.id.text_creategroup);
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
            Intent intent = new Intent();
            int j = 1;
            intent.putExtra("int", j);
            setResult(RESULT_OK, intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}