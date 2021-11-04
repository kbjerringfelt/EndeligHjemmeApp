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

    Button psych, group, settings, adviceBtn, checklistBtn, thisWeekBtn, logOutBtn;
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
                goToAdviceActivity();
            }
        });

        checklistBtn = findViewById(R.id.list_btn_checklist);
        checklistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToChecklist();
            }
        });

        thisWeekBtn = findViewById(R.id.list_btn_babyweek);
        thisWeekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToThisWeek();
            }
        });

        logOutBtn = findViewById(R.id.signOutBtn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogIn();
            }
        });

    }

    private void goToThisWeek() {
        Intent intent = new Intent(this, ThisWeekActivity.class);
        launcher.launch(intent);
    }

    private void goToChecklist() {
        Intent intent = new Intent(this, CheckListActivity.class);
        launcher.launch(intent);
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

    private void goToAdviceActivity() {
        Intent intent = new Intent(this, AdviceActivity.class);
        launcher.launch(intent);
    }

    private void goToLogIn(){
        Intent intent = new Intent();
        int j = 1;
        intent.putExtra("int", j);
        setResult(RESULT_OK, intent);
        finish();
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
                    if (result.getResultCode() == RESULT_FIRST_USER) {
                        Intent intent = new Intent();
                        setResult(RESULT_FIRST_USER, intent);
                        finish();
                    }
                }
            });

}