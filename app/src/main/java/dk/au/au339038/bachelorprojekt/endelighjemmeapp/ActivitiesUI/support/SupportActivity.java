package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.support;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.psychologist.PsychViewModel;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Support;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.FHApplication;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class SupportActivity extends AppCompatActivity {

    TextView faqTxt, faqLink, furtherHelp, contactInfo, contactHours;
    private SupportViewModel svm;
    private Support support;
    private LiveData<Support> lsupport;
    private Button wishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        faqTxt = findViewById(R.id.faqTxt);
        faqLink = findViewById(R.id.faqLink);
        furtherHelp = findViewById(R.id.suppotContactTxt);
        contactInfo = findViewById(R.id.supportContactInfo);
        contactHours = findViewById(R.id.contactHours);
        wishBtn = findViewById(R.id.wishBtn);

        svm = new ViewModelProvider(this).get(SupportViewModel.class);

        support = new Support();

        lsupport = svm.getSupport();
        lsupport.observe(this, new Observer<Support>() {
            @Override
            public void onChanged(Support s) {
                support = s;
                contactHours.setText(support.getPhoneHours());
                contactInfo.setText(getText(R.string.contactTxt) + " " + support.getPhone());
            }
        });

        wishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FHApplication.getAppContext(), getText(R.string.wishButton)+ " " + getText(R.string.not_implemented), Toast.LENGTH_SHORT).show();
            }
        });



    }
}