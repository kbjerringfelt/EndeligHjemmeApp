package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.login;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.Home.HomeActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.NotDone.NemIdActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Pin;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Other.FHApplication;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels.LogInViewModel;

public class LogInActivity extends AppCompatActivity {

    private LogInViewModel lvm;
    private LiveData<Pin> pin;
    private Pin thepin;
    private Button logInButton, changePin;
    private EditText userText;
    private int wrongPin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //Finder widgets i layout
        logInButton = findViewById(R.id.logInBtn);
        userText = findViewById(R.id.pinText);

        lvm = new ViewModelProvider(this).get(LogInViewModel.class);

        //Observerer pinkoden
        pin = lvm.getPin();
        pin.observe(this, new Observer<Pin>() {
            @Override
            public void onChanged(Pin newpin) {
                thepin = newpin;
            }
        });


        changePin = findViewById(R.id.newPasswordBtn);
        changePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FHApplication.getAppContext(), getText(R.string.passwordBtn) +" "+ getText(R.string.not_implemented), Toast.LENGTH_SHORT).show();
            }
        });

        //Logind knap. Registrerer et klik.
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = userText.getText().toString();
                int i = validatePin(s);
                //Tjekker hvor mange gange pinkoden er tastet forkert, hvis den er <=2 går den in i denne løkke
                if(pin.getValue().getWrongPinCount()<=2) {
                    //Hvis validate har returnereret 1 kan man logge på.
                    if (i == 1) {
                        wrongPin = 0;
                        pin.getValue().setWrongPinCount(wrongPin);
                        lvm.updatePin(pin.getValue());
                        goToMain();
                    }
                    //Hvis validate returnerer 0 kommer der fejlmeddelelser, og antal forkerte forsøg øges med 1
                    if (i == 0) {
                        wrongPin = pin.getValue().getWrongPinCount();
                        wrongPin++;
                        pin.getValue().setWrongPinCount(wrongPin);
                        lvm.updatePin(pin.getValue());
                        Toast.makeText(FHApplication.getAppContext(), R.string.wrong_pin, Toast.LENGTH_SHORT).show();
                    }
                }
                //Hvis antal forkerte forsøg er >=3 bliver pinkoden slettet i databasen og man logges ud til NemId verificering
                if (pin.getValue().getWrongPinCount()>=3){
                    lvm.deletePin(pin.getValue());
                    thepin = null;
                    toNemID();
                    finish();
                }
            }
        });
    }

    //Åbner Hjemme aktiviteten
    private void goToMain() {
        lvm.loadUserData(thepin.getUid());
        String userId = thepin.getUid();
        Intent i = new Intent(this, HomeActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("userID", userId);
        i.putExtras(b);
        launcher.launch(i);
    }

    //Åbner NemId aktiviteten
    private void toNemID(){
        Intent intent = new Intent(this, NemIdActivity.class);
        launcher.launch(intent);
        finish();
    }

    //Launcher hjælper med at launche aktiviteter
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {

                    }
                    if (result.getResultCode() == RESULT_CANCELED) {
                        userText.setText("");
                    }
                }
            });

    //Tjekker at den indtastede pinkode er den samme som den i databasen.
    public int validatePin(String incomingpin) {
        if(incomingpin.equals(pin.getValue().getPin())){
            return 1;
        }
        else {
            return 0;
        }
    }

    //Bestemmer hvad der sker, når der trykkes på tilbage pilen.
    @Override
    public void onBackPressed() {
        finish();
    }
}

//Christina, barn 8 uger, hedder julie, født 28+4.