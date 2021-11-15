package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.advice;

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
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.NotDone.CreateGroupActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.group.GroupViewModel;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Adapter.AdviceAdapter;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Adapter.GroupAdapter;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Advice;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Group;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.FHApplication;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class AdviceActivity extends AppCompatActivity implements AdviceAdapter.IAdviceItemClickedListener{

    public static final String TAG = "LogF";
    private RecyclerView arcv;
    private AdviceAdapter adviceAdapter;
    private LiveData<ArrayList<Advice>> ladvice;
    private ArrayList<Advice> advice;
    AdviceViewModel avm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);

        avm = new ViewModelProvider(this).get(AdviceViewModel.class);

        adviceAdapter = new AdviceAdapter(this);
        arcv = findViewById(R.id.rcv_advice);
        arcv.setLayoutManager(new LinearLayoutManager(this));
        arcv.setAdapter(adviceAdapter);

        advice = new ArrayList<Advice>();

        ladvice = avm.getAdvice();
        ladvice.observe(this, new Observer<ArrayList<Advice>>() {
            @Override
            public void onChanged(ArrayList<Advice> nadvice) {
                adviceAdapter.updateAdviceList(nadvice);
                advice = nadvice;
            }
        });

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
    public void onAdviceClicked(int index) {
        ArrayList<Advice> ml = ladvice.getValue();
       // showAdvice(ml.get(index).getTitle());
    }

   /* private void showAdvice(String title){
        Intent i = new Intent(this, ChosenAdviceActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("Title", title);
        i.putExtras(b);
        launcher.launch(i);
    }

    //show a dialogue
    //Method to open link: https://stackoverflow.com/questions/2201917/how-can-i-open-a-url-in-androids-web-browser-from-my-application?page=1&tab=votes#tab-top
    private void showDialogue(Advice a){
        //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        //launcher.launch(browserIntent);

        //create a dialogue popup - and show it
       /*AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(g.getTitle())
                .setMessage(
                        getText(R.string.bdateTxt) +" " + g.getBdate() + "\n\n" +
                                getText(R.string.contactTxt) +" " + g.getContact() + "\n\n" +
                                getText(R.string.phonetxt) +" " +g.getPhone() + "\n\n" +
                                getText(R.string.place_txt) +" " + g.getPlace() + "\n\n" +
                                getText(R.string.descriptionTxt) +"\n" + g.getDescription())
                .setPositiveButton(R.string.signUpGroup, (dialogInterface, i) -> signUpForGroup())
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {});
        builder.create().show();*/
    //}
}