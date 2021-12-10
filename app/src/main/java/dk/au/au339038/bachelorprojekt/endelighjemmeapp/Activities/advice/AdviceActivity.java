package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.advice;

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

import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Adapter.AdviceAdapter;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Advice;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels.AdviceViewModel;

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
        showDialogue(ml.get(index));
    }

   private void goToLink(String link){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        launcher.launch(browserIntent);
    }

    //show a dialogue
    //Method to open link: https://stackoverflow.com/questions/2201917/how-can-i-open-a-url-in-androids-web-browser-from-my-application?page=1&tab=votes#tab-top
    private void showDialogue(Advice a){
        //create a dialogue popup - and show it
       AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(a.getTitle())
                .setMessage(a.getText())
                .setPositiveButton(R.string.goToLink, (dialogInterface, i) -> goToLink(a.getLink()))
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {});
        builder.create().show();
    }
}