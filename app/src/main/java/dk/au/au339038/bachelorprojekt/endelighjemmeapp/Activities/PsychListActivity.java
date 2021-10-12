package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.IMHP;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.PsychAdapter;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

// To get the data from Firebase I used this tutorial: https://www.youtube.com/watch?v=Az4gXQAP-a4
public class PsychListActivity extends AppCompatActivity implements PsychAdapter.IPsychItemClickedListener {

    private RecyclerView prcv;
    private PsychAdapter psychAdapter;
    private ArrayList<IMHP> psychologists;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychologist);

        psychAdapter = new PsychAdapter(this);
        prcv = findViewById(R.id.rcv_psych);
        prcv.setLayoutManager(new LinearLayoutManager(this));
        prcv.setAdapter(psychAdapter);

        psychologists = new ArrayList<IMHP>();
        loadData();
        psychAdapter.updateMHPList(psychologists);
    }

    private void loadData() {

       db = FirebaseFirestore.getInstance();
   db.collection("psychologists").addSnapshotListener(new EventListener<QuerySnapshot>() {
       @Override
       public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
           for (DocumentChange dc : value.getDocumentChanges()){
               if (dc.getType() == DocumentChange.Type.ADDED){
                   psychologists.add(dc.getDocument().toObject(Psychologist.class));
               }
               psychAdapter.notifyDataSetChanged();
           }
       }
   }); }


    @Override
    public void onPsychologistClicked(int index) {

    }
}