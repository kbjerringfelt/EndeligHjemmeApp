package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.login;

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

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.HomeActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Pin;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.FHApplication;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class LogInActivity extends AppCompatActivity {

    private LogInViewModel lvm;
    private LiveData<Pin> pin;
    private int _pin;
    private Button logInButton;
    private EditText pinText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logInButton = findViewById(R.id.logInBtn);
        pinText = findViewById(R.id.pinText);

        lvm = new ViewModelProvider(this).get(LogInViewModel.class);

        pin = lvm.getPin();
        pin.observe(this, new Observer<Pin>() {
            @Override
            public void onChanged(Pin thepin) {
                loadTestPin(thepin);
                _pin = thepin.getPincode();
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = pinText.getText().toString();
                if(s.equals("")){
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
                        Toast.makeText(FHApplication.getAppContext(), R.string.wrong_pin, Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


    }

    private void goToMain() {
        Intent i = new Intent(this, HomeActivity.class);
        // Bundle b = new Bundle();
        // b.putSerializable("Title", title);
        // i.putExtras(b);
        launcher.launch(i);
    }
    //Also from Test12 demo
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        //                      Intent data = result.getData();
                        //                    movielist = lvm.getMovies();
                        //                  movieAdapter.updateMovieList(movielist.getValue());
                    }
                    if (result.getResultCode() == RESULT_CANCELED) {
                        pinText.setText("");
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

    private void loadTestPin(Pin p){
        if(p==null){
        Pin newp = new Pin(123456);
        lvm.setPin(newp);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

//Christina, barn 8 uger, hedder julie, født 28+4.