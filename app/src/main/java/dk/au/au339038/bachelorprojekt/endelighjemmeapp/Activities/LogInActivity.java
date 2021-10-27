package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;

import java.util.List;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Pin;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels.LogInViewModel;

public class LogInActivity extends AppCompatActivity {

    private LogInViewModel lvm;
    private LiveData<Pin> pin;
    private int _pin;
    private Button logInButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logInButton = findViewById(R.id.logInBtn);

        lvm = new ViewModelProvider(this).get(LogInViewModel.class);

        pin = lvm.getPin();
        pin.observe(this, new Observer<Pin>() {
            @Override
            public void onChanged(Pin thepin) {
                loadTestPin(thepin);
                _pin = thepin.getPincode();
            }
        });

    }

    private void loadTestPin(Pin p){
        if(p==null){
        Pin newp = new Pin(123456);
        lvm.setPin(newp);
        }
    }
}