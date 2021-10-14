package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.IMHP;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.PsychAdapter;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

// To get the data from Firebase I used this tutorial: https://www.youtube.com/watch?v=Az4gXQAP-a4
public class PsychListActivity extends AppCompatActivity implements PsychAdapter.IPsychItemClickedListener {

    public static final String TAG = "LogF";
    private RecyclerView prcv;
    private PsychAdapter psychAdapter;
    private ArrayList<Psychologist> psychologists;
    FirebaseFirestore db;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychologist);

        psychAdapter = new PsychAdapter(this);
        prcv = findViewById(R.id.rcv_psych);
        prcv.setLayoutManager(new LinearLayoutManager(this));
        prcv.setAdapter(psychAdapter);

        psychologists = new ArrayList<Psychologist>();
        loadData();
        psychAdapter.updateMHPList(psychologists);
    }

    private void loadData() {

        db.collection("psychologists")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    @Override
    public void onPsychologistClicked(int index) {

    }
}