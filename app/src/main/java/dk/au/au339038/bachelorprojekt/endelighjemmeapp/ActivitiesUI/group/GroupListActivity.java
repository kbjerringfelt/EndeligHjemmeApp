package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.group;

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
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.NotDone.CreateGroupActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Group;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.FHApplication;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Adapter.GroupAdapter;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class GroupListActivity extends AppCompatActivity implements GroupAdapter.IGroupItemClickedListener {

    public static final String TAG = "LogF";
    private RecyclerView grcv;
    private GroupAdapter groupAdapter;
    private LiveData<ArrayList<Group>> lgroups;
    private ArrayList<Group> groups;
    private LiveData<User> user;
    private User _user;
    GroupViewModel gvm;
    private Button createBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        gvm = new ViewModelProvider(this).get(GroupViewModel.class);

        groupAdapter = new GroupAdapter(this);
        grcv = findViewById(R.id.rcv_group);
        grcv.setLayoutManager(new LinearLayoutManager(this));
        grcv.setAdapter(groupAdapter);

        groups = new ArrayList<Group>();

        user = gvm.getUser();
        user.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                _user = user;
            }
        });

        lgroups = gvm.getGroups();
        lgroups.observe(this, new Observer<ArrayList<Group>>() {
            @Override
            public void onChanged(ArrayList<Group> ngroups) {
                for (Group g : ngroups)
                {
                    if (g.getArea().equals( _user.getArea())){
                        groups.add(g);
                    }
                }
                groupAdapter.updateMHPList(groups);
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
        ArrayList<Group> ml = lgroups.getValue();
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

    private void signUpForGroup() {
        Toast.makeText(FHApplication.getAppContext(), getText(R.string.signUpGroup)+ " " + getText(R.string.not_implemented), Toast.LENGTH_SHORT).show();

    }

}