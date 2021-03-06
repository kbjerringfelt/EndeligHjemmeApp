package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.support;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Support;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Other.FHApplication;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels.SupportViewModel;

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

        //Widgets
        faqTxt = findViewById(R.id.faqTxt);
        faqLink = findViewById(R.id.faqLink);
        furtherHelp = findViewById(R.id.suppotContactTxt);
        contactInfo = findViewById(R.id.supportContactInfo);
        contactHours = findViewById(R.id.contactHours);
        wishBtn = findViewById(R.id.wishBtn);

        svm = new ViewModelProvider(this).get(SupportViewModel.class);

        support = new Support();

        //Observerer support info
        lsupport = svm.getSupport();
        lsupport.observe(this, new Observer<Support>() {
            @Override
            public void onChanged(Support s) {
                support = s;
                contactHours.setText(support.getPhoneHours());
                contactInfo.setText(getText(R.string.contactTxt) + " " + support.getPhone());
            }
        });

        //??nske knap
        wishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FHApplication.getAppContext(), getText(R.string.wishButton)+ " " + getText(R.string.not_implemented), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Menu i toppen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_top,menu);
        return true;
    }

    //Klik p?? menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.bar_menu) {
            Intent intent = new Intent();
            int j = 1;
            intent.putExtra("int", j);
            setResult(RESULT_OK, intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}