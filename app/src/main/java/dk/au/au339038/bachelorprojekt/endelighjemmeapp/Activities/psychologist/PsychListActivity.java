package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.psychologist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Adapter.PsychAdapter;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels.PsychViewModel;

// To get the data from Firebase I used this tutorial: https://www.youtube.com/watch?v=Az4gXQAP-a4
public class PsychListActivity extends AppCompatActivity implements PsychAdapter.IPsychItemClickedListener, AdapterView.OnItemSelectedListener {

    public static final String TAG = "LogF";
    private RecyclerView prcv;
    private PsychAdapter psychAdapter;
    private LiveData<ArrayList<Psychologist>> lpsychologists;
    private LiveData<ArrayList<String>> lcommunities;
    private List<String> communities;
    private LiveData<User> user;
    private ArrayList<Psychologist> psychologists;
    private User _user;
    private PsychViewModel pvm;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychologist);

        //Viewmodel for aktiviteten
        pvm = new ViewModelProvider(this).get(PsychViewModel.class);

        //Forbindes med widgets
        textView = findViewById(R.id.textViewpsych);

        //Adapter for recyclerviewet
        psychAdapter = new PsychAdapter(this);
        prcv = findViewById(R.id.rcv_psych);
        prcv.setLayoutManager(new LinearLayoutManager(this));
        prcv.setAdapter(psychAdapter);

        //Lister
        psychologists = new ArrayList<Psychologist>();
        communities = new ArrayList<String>();

        //Brugeren
        user = pvm.getUser();
        user.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                _user = user;
            }
        });

        //Kommuner til drop down menu
        lcommunities = pvm.getCommunities();
        lcommunities.observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> ncommunities) {
                communities = ncommunities;
                //default valg i spinner: https://www.tutorialspoint.com/how-to-make-an-android-spinner-with-initial-default-text
                communities.add(0, "" + getText(R.string.spinnerDefault));
                setUpSpinner();
            }
        });

    }

    //Metode opdaterer listen i recyclerview efter valg i dropdown (spinner)
    private void updateList(String area){
        if(area.equals(getText(R.string.spinnerDefault))){area = user.getValue().getArea();}
        LiveData<ArrayList<Psychologist>> newlpsychologists = new MutableLiveData<>();
        newlpsychologists = pvm.getPsychologistsForArea(area);
        newlpsychologists.observe(this, new Observer<ArrayList<Psychologist>>() {
            @Override
            public void onChanged(ArrayList<Psychologist> npsychologists) {
                psychologists = npsychologists;
                psychAdapter.updateMHPList(npsychologists);
                if(npsychologists.size() == 0){
                    textView.setText(getText(R.string.noDataArea));
                }
                else{
                    textView.setText("");
                }
            }
        });

    }

    //Vælg item fra drop down menu
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        updateList(item);

    }
    //Ingen valgt i spinner/dropdown
    public void onNothingSelected(AdapterView<?> arg0) {
        updateList(user.getValue().getArea());
    }

    //Menu i toppen af applikationen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_top,menu);
        return true;
    }

    //Klik på menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.bar_menu) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //Vælg psykolog på listen
    @Override
    public void onPsychologistClicked(int index) {
        showDialogue(psychologists.get(index));
    }

    //Dialog der bliver vist ved valgt psykolog
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

    //Spinner lavet ud fra toturial fra: https://www.tutorialspoint.com/android/android_spinner_control.htm
    private void setUpSpinner(){
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.psychSpinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, communities);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

}