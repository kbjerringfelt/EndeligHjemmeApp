package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.group.GroupListActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.psychologist.PsychListActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class MenuActivity extends AppCompatActivity {

    Button psych, group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        psych = findViewById(R.id.list_btn_psych);
        psych.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPsychActivity();
            }
        });

        group = findViewById(R.id.list_btn_group);
        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGroup();
            }
        });

    }

    private void goToGroup() {
        Intent intent = new Intent(this, GroupListActivity.class);
        launcher.launch(intent);
    }

    private void goToPsychActivity() {
        Intent intent = new Intent(this, PsychListActivity.class);
        launcher.launch(intent);
    }


    //Også lavet ud fra samme metode i Test12 demo
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();

                    }
                }
            });
}