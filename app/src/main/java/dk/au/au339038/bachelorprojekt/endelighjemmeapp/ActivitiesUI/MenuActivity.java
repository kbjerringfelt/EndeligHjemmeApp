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
import android.widget.Toast;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.group.GroupListActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.psychologist.PsychListActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.settings.SettingsActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.FHApplication;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class MenuActivity extends AppCompatActivity {

    Button psych, group, settings, adviceBtn, checklistBtn, thisWeekBtn;
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

        settings = findViewById(R.id.list_btn_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSettings();
            }
        });

        adviceBtn = findViewById(R.id.list_btn_advice);
        adviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FHApplication.getAppContext(), getText(R.string.btn_advice)+ " " + getText(R.string.not_implemented), Toast.LENGTH_SHORT).show();

            }
        });

        checklistBtn = findViewById(R.id.list_btn_checklist);
        checklistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FHApplication.getAppContext(),getText(R.string.btn_checklist)+ " " + getText(R.string.not_implemented), Toast.LENGTH_SHORT).show();

            }
        });

        thisWeekBtn = findViewById(R.id.list_btn_babyweek);
        thisWeekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FHApplication.getAppContext(), getText(R.string.btn_babyweek)+ " " + getText(R.string.not_implemented), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void goToSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        launcher.launch(intent);
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
                    if (result.getResultCode() == RESULT_CANCELED) {
                        Intent intent = new Intent();
                        setResult(RESULT_CANCELED, intent);
                        finish();
                    }
                }
            });

}