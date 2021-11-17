package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.login;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.HomeActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.NotDone.NemIdActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Pin;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.FHApplication;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class LogInActivity extends AppCompatActivity {

    private LogInViewModel lvm;
    private LiveData<Pin> pin;
    private Pin thepin;
    private int _pin;
    private Button logInButton, changePin;
    private EditText userText;
    private String userId;
    private int wrongPin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        wrongPin = 0;
        logInButton = findViewById(R.id.logInBtn);
        userText = findViewById(R.id.pinText);

        lvm = new ViewModelProvider(this).get(LogInViewModel.class);

       // if(pin == null){
         //   pin = new MutableLiveData<Pin>();
       //}
        pin = lvm.getPin();
        pin.observe(this, new Observer<Pin>() {
            @Override
            public void onChanged(Pin newpin) {
                _pin = newpin.getPin();
                thepin = newpin;
            }
        });

        //Hvis vi havde implementeret oprettelsen af Pinkode, ville dette være unødvendigt.
        if(thepin == null) {
             loadTestUser(thepin);
        }

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
                if(s.equals("")){
                    wrongPin++;
                    Toast.makeText(FHApplication.getAppContext(), R.string.wrong_pin, Toast.LENGTH_SHORT).show();
                }
                else {
                    int pin = Integer.parseInt(s);
                    int i = validatePin(pin);
                    if (i == 1) {
                        goToMain();
                        //finish();
                    }
                    if (i == 0) {
                        wrongPin++;
                        Toast.makeText(FHApplication.getAppContext(), R.string.wrong_pin, Toast.LENGTH_SHORT).show();

                    }
                }
                if (wrongPin>=3){
                    toNemID();
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

    private int validatePin(int pin) {
        if(pin == _pin){
            return 1;
        }
        else {
            return 0;
        }
    }

   private void loadTestUser(Pin p){
        if(p==null){
        thepin = new Pin(123456,"0101907652");
        lvm.setPin(thepin);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

//Christina, barn 8 uger, hedder julie, født 28+4.