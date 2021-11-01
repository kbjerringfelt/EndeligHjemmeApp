package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.group;

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
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.psychologist.PsychListActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.psychologist.PsychViewModel;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Group;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.GroupAdapter;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.PsychAdapter;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class GroupListActivity extends AppCompatActivity implements GroupAdapter.IGroupItemClickedListener {

    public static final String TAG = "LogF";
    private RecyclerView grcv;
    private GroupAdapter groupAdapter;
    private LiveData<ArrayList<Group>> lgroups;
    private ArrayList<Group> groups;
    FirebaseFirestore db;
    DatabaseReference mbase;
    GroupViewModel gvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        gvm = new ViewModelProvider(this).get(GroupViewModel.class);

        db = FirebaseFirestore.getInstance();
        groupAdapter = new GroupAdapter(this);
        grcv = findViewById(R.id.rcv_group);
        grcv.setLayoutManager(new LinearLayoutManager(this));
        grcv.setAdapter(groupAdapter);

        groups = new ArrayList<Group>();

        lgroups = gvm.getGroups();
        lgroups.observe(this, new Observer<ArrayList<Group>>() {
            @Override
            public void onChanged(ArrayList<Group> ngroups) {
                groupAdapter.updateMHPList(ngroups);
                groups = ngroups;
            }
        });
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
                        getText(R.string.descriptionTxt) +"\n" + g.getDescription());
        builder.create().show();
    }

}