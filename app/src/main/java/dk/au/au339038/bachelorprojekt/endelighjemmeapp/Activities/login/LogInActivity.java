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

        logInButton = findViewById(R.id.logInBtn);
        userText = findViewById(R.id.pinText);

        lvm = new ViewModelProvider(this).get(LogInViewModel.class);

        pin = lvm.getPin();
        pin.observe(this, new Observer<Pin>() {
            @Override
            public void onChanged(Pin newpin) {
               // _pin = newpin.getPin();
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

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = userText.getText().toString();
                /*if(s.equals("")){
                    wrongPin = pin.getValue().getWrongPinCount();
                    wrongPin ++;
                    pin.getValue().setWrongPinCount(wrongPin);
                    lvm.updatePin(pin.getValue());
                    Toast.makeText(FHApplication.getAppContext(), R.string.wrong_pin, Toast.LENGTH_LONG).show();
                }*/

                int i = validatePin(s);
                if (i == 1) {
                        wrongPin = 0;
                        pin.getValue().setWrongPinCount(wrongPin);
                        lvm.updatePin(pin.getValue());
                        goToMain();
                }
                if (i == 0) {
                        wrongPin = pin.getValue().getWrongPinCount();
                        wrongPin++;
                        pin.getValue().setWrongPinCount(wrongPin);
                        lvm.updatePin(pin.getValue());
                        Toast.makeText(FHApplication.getAppContext(), R.string.wrong_pin, Toast.LENGTH_SHORT).show();
                }
                if (pin.getValue().getWrongPinCount()>=3){
                    lvm.deletePin(pin.getValue());
                    thepin = null;
                    toNemID();
                    finish();
                }
            }
        });
    }

    private void goToMain() {
        lvm.loadUserData(thepin.getUid());
        String userId = thepin.getUid();
        Intent i = new Intent(this, HomeActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("userID", userId);
        i.putExtras(b);
        launcher.launch(i);
        finish();
    }

    private void toNemID(){
        Intent intent = new Intent(this, NemIdActivity.class);
        launcher.launch(intent);
        finish();
    }

    //Also from Test12 demo
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

    public int validatePin(String incomingpin) {
        if(incomingpin.equals(pin.getValue().getPin())){
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

//Christina, barn 8 uger, hedder julie, født 28+4.