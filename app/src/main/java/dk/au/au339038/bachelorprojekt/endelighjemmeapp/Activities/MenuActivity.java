package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.advice.AdviceActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.NotDone.CheckListActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.NotDone.ThisWeekActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.group.GroupListActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.psychologist.PsychListActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.settings.SettingsActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class MenuActivity extends AppCompatActivity {

    Button psych, group, settings, adviceBtn, checklistBtn, thisWeekBtn, logOutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Knapper forbindes med widgets i layoutet og besked om hvad der skal ske ved tryk
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
                logOut();
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

    //Advarsel n??r man logger ud, for at bekr??fte valget
    private void logOut(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(R.string.logoutDialog)
                .setTitle(R.string.signOutBtn)
                .setPositiveButton(R.string.surelogOut, (dialogInterface, i) -> goToLogIn())
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {});
        builder.create().show();
    }

    //Logger ud ved at g?? til log ind og afslutte log ud aktiviteten
    private void goToLogIn(){

        Intent intent = new Intent();
        int j = 1;
        intent.putExtra("int", j);
        setResult(RESULT_OK, intent);
        finish();
    }


    //Launcher launcher aktiviteter
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