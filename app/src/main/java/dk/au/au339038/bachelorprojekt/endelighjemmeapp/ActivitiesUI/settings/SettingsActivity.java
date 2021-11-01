package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.settings;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.login.LogInActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.support.SupportActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.FHApplication;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class SettingsActivity extends AppCompatActivity {

    private Button supportBtn, area, childEdit;
    private Switch touch, face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        supportBtn = findViewById(R.id.supportBtn);
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSupportActivity();
            }
        });

        area = findViewById(R.id.areaBtn);
        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FHApplication.getAppContext(), getText(R.string.areaBtn)+ " " + getText(R.string.not_implemented), Toast.LENGTH_SHORT).show();

            }
        });

        childEdit = findViewById(R.id.childBtn);
        childEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FHApplication.getAppContext(), getText(R.string.childBtn)+ " " + getText(R.string.not_implemented), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToLogIn(){
        Intent intent = new Intent();
        intent.putExtra("IntResult", 1);
        finish();
    }

    private void goToSupportActivity() {
        Intent intent = new Intent(this, SupportActivity.class);
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