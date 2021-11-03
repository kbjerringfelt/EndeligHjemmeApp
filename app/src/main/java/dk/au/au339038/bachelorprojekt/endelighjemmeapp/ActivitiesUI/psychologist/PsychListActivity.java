package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.psychologist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;


import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.MenuActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.PsychAdapter;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

// To get the data from Firebase I used this tutorial: https://www.youtube.com/watch?v=Az4gXQAP-a4
public class PsychListActivity extends AppCompatActivity implements PsychAdapter.IPsychItemClickedListener {

    public static final String TAG = "LogF";
    private RecyclerView prcv;
    private PsychAdapter psychAdapter;
    private LiveData<ArrayList<Psychologist>> lpsychologists;
    private ArrayList<Psychologist> psychologists;
    PsychViewModel pvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychologist);

        pvm = new ViewModelProvider(this).get(PsychViewModel.class);

        psychAdapter = new PsychAdapter(this);
        prcv = findViewById(R.id.rcv_psych);
        prcv.setLayoutManager(new LinearLayoutManager(this));
        prcv.setAdapter(psychAdapter);

        psychologists = new ArrayList<Psychologist>();

        lpsychologists = pvm.getPsychologists();
        lpsychologists.observe(this, new Observer<ArrayList<Psychologist>>() {
            @Override
            public void onChanged(ArrayList<Psychologist> psychs) {
                psychAdapter.updateMHPList(psychs);
                psychologists = psychs;
            }
        });
        // psychAdapter.updateMHPList(psychologists);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_top,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.bar_menu) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPsychologistClicked(int index) {
        ArrayList<Psychologist> ml = lpsychologists.getValue();
        showDialogue(ml.get(index));
    }

    //show a dialogue
    private void showDialogue(Psychologist psychologist){
       //create a dialogue popup - and show it
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(psychologist.getName())
        .setMessage(getText(R.string.phonetxt) +" " +psychologist.getPhone() + "\n\n" +
                getText(R.string.pricetxt) +" " + psychologist.getPrice() + "\n\n" +
                getText(R.string.cityTxt) +" " + psychologist.getCity() + "\n\n" +
                getText(R.string.educationtxt) +" " + psychologist.getEducation() + "\n\n" +
                getText(R.string.sextxt) +" " + psychologist.getSex()+ "\n\n" +
                getText(R.string.insuranceTxt) +" " + psychologist.getInsurance() + "\n\n" +
                getText(R.string.languagetxt) +" " + psychologist.getLanguage() + "\n\n" +
                getText(R.string.disabilitytxt) +" " + psychologist.getDisability() + "\n\n" +
                getText(R.string.specialtyTxt) +"\n" + psychologist.getSpecialty() + "\n\n" +
                getText(R.string.moreinfo) +"\n" + psychologist.getMoreinfo())
        .setNegativeButton(R.string.backBtn, (dialogInterface, i) -> {});
        builder.create().show();
    }

}