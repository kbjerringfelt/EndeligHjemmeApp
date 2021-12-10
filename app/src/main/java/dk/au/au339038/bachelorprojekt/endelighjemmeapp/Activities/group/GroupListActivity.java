package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.group;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.NotDone.CreateGroupActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Group;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Other.FHApplication;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Adapter.GroupAdapter;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels.GroupViewModel;

public class GroupListActivity extends AppCompatActivity implements GroupAdapter.IGroupItemClickedListener, AdapterView.OnItemSelectedListener {

    public static final String TAG = "LogF";
    private RecyclerView grcv;
    private GroupAdapter groupAdapter;
    private LiveData<ArrayList<Group>> lgroups;
    private ArrayList<Group> groups;
    private LiveData<ArrayList<String>> lcommunities;
    private List<String> communities;
    private LiveData<User> user;
    private User _user;
    GroupViewModel gvm;
    private Button createBtn;
    private Spinner spinner;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        gvm = new ViewModelProvider(this).get(GroupViewModel.class);

        groupAdapter = new GroupAdapter(this);
        grcv = findViewById(R.id.rcv_group);
        grcv.setLayoutManager(new LinearLayoutManager(this));
        grcv.setAdapter(groupAdapter);

        textView = findViewById(R.id.textViewgroup);
        groups = new ArrayList<Group>();
        communities = new ArrayList<String>();


        user = gvm.getUser();
        user.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User newuser) {
                _user = newuser;
            }
        });

        lcommunities = gvm.getCommunities();
        lcommunities.observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> ncommunities) {
                communities = ncommunities;
                communities.add(0, "" + getText(R.string.spinnerDefault));
                setUpSpinner();
            }
        });

        createBtn = findViewById(R.id.createBtn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCreateGroup();
                 }
        });

    }

    private void goToCreateGroup() {
        Intent intent = new Intent(this, CreateGroupActivity.class);
        launcher.launch(intent);
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        Bundle b = data.getExtras();
                        int j = b.getInt("int");
                        if (j == 1){
                            finish();
                        }

                    }
                }
            });

    private void updateList(String area){
        if(area.equals(getText(R.string.spinnerDefault))){area = user.getValue().getArea();}
        lgroups = gvm.getGroupsForArea(area);
        lgroups.observe(this, new Observer<ArrayList<Group>>() {
            @Override
            public void onChanged(ArrayList<Group> ngroups) {
                groups = ngroups;
                groupAdapter.updateGroupList(ngroups);
                if(ngroups.size() == 0){
                    textView.setText(getText(R.string.noDataArea));
                }
                else{
                    textView.setText("");
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        updateList(item);

        }
    public void onNothingSelected(AdapterView<?> arg0) {
        updateList(user.getValue().getArea());
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
    public void onGroupClicked(int index) {
        ArrayList<Group> ml = groups;
        showDialogue(ml.get(index));
    }

    //show a dialogue
    private void showDialogue(Group g){
        //create a dialogue popup - and show it
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(g.getTitle())
                .setMessage(
                        getText(R.string.bdateTxt) +" " + g.getBdate() + "\n\n" +
                        getText(R.string.contactTxt) +" " + g.getContact() + "\n\n" +
                        getText(R.string.phonetxt) +" " +g.getPhone() + "\n\n" +
                        getText(R.string.place_txt) +" " + g.getPlace() + "\n\n" +
                        getText(R.string.descriptionTxt) +"\n" + g.getDescription())
                .setPositiveButton(R.string.signUpGroup, (dialogInterface, i) -> signUpForGroup())
        .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {});
        builder.create().show();
    }

    //Spinner in this class is based on the toturial from: https://www.tutorialspoint.com/android/android_spinner_control.htm
    private void setUpSpinner(){
        // Spinner element
        spinner = (Spinner) findViewById(R.id.groupSpinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, communities);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    private void signUpForGroup() {
        Toast.makeText(FHApplication.getAppContext(), getText(R.string.signUpGroup)+ " " + getText(R.string.not_implemented), Toast.LENGTH_SHORT).show();

    }

}