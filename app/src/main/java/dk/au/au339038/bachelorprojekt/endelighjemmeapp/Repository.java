package dk.au.au339038.bachelorprojekt.endelighjemmeapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Group;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Mood;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Pin;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Support;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Database.AppsDatabase;

public class Repository {
    private static final String TAG = "Getting document:";
    private FirebaseFirestore fdb;
    private AppsDatabase db;
    private ExecutorService executor;
    private static Repository instance;
    private MutableLiveData<ArrayList<Psychologist>> psychs;
    private MutableLiveData<ArrayList<Group>> groups;
    private MutableLiveData<Support> support;
    private MutableLiveData<Mood> mood;
    private LiveData<Pin> pin;

    public static Repository getInstance(){
        if(instance==null){
            instance = new Repository();
        }
        return instance;
    }

    //constructor - takes Application object for context
    private Repository(){
        db = AppsDatabase.getDatabase(FHApplication.getAppContext());
        fdb = FirebaseFirestore.getInstance();
        executor = Executors.newSingleThreadExecutor();
        pin = db.pinDAO().getPin();
        loadPsychData();
        loadGroupData();
        loadSupportData();

        Date currentDate = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
        String dateOnly = dateFormat.format(currentDate);
        loadMood(dateOnly);

    }


    public LiveData<ArrayList<Psychologist>> getPsychologists(){
        return psychs;
    }

    public LiveData<ArrayList<Group>> getGroups(){
        return groups;
    }

    public LiveData<Support> getSupport(){
        return support;
    }

    public MutableLiveData<Mood> getMood() {return mood;}




    public LiveData<Pin> getPin(){
        if(pin == null){
            pin = new MutableLiveData<Pin>();
        }
        return pin;
    }

    public void setPinAsynch(Pin p){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.pinDAO().insertPin(p);
            }
        });
    }

    //Load data methods, from https://firebase.google.com/docs/firestore/query-data/get-data.
    private void loadPsychData() {
        fdb.collection("psychologists")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        ArrayList<Psychologist> updatedPsychologists = new ArrayList<>();
                        if(value != null && !value.isEmpty()){
                            for(DocumentSnapshot doc : value.getDocuments()){
                                Psychologist p = doc.toObject(Psychologist.class);
                                if(p!=null){
                                    updatedPsychologists.add(p);
                                    Log.d(TAG, "DocumentSnapshot data: " + doc.getData());
                                }
                            }
                            if(psychs==null){
                                psychs = new MutableLiveData<ArrayList<Psychologist>>();
                            }
                            psychs.setValue(updatedPsychologists);
                        }
                    }
                });


    }
    private void loadGroupData() {
                fdb.collection("groups")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot valueg, @Nullable FirebaseFirestoreException error) {
                        ArrayList<Group> updatedGroups = new ArrayList<>();
                        if(valueg != null && !valueg.isEmpty()){
                            for(DocumentSnapshot docg : valueg.getDocuments()){
                                Group g = docg.toObject(Group.class);
                                if(g!=null){
                                    updatedGroups.add(g);
                                }
                                Log.d(TAG, "DocumentSnapshot data: " + docg.getData());
                            }
                            if(groups==null){
                                groups = new MutableLiveData<ArrayList<Group>>();
                            }
                            groups.setValue(updatedGroups);
                        }
                    }
                });
    }
    private void loadSupportData() {
        DocumentReference docRef = fdb.collection("support").document("supportinfo");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Support s = document.toObject(Support.class);
                        if(s!=null){
                            if(support==null){
                                support = new MutableLiveData<Support>();
                            }
                            support.setValue(s);
                        }
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    private void loadMood(String date) {
       /* DocumentReference docRef = fdb.collection("mood").document(date);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Mood m = document.toObject(Mood.class);
                        if(m!=null){
                            if(mood==null){
                                mood = new MutableLiveData<Mood>();
                            }
                            mood.setValue(m);
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });*/
        final DocumentReference docRef = fdb.collection("mood").document(date);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                String source = snapshot != null && snapshot.getMetadata().hasPendingWrites()
                        ? "Local" : "Server";

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, source + " data: " + snapshot.getData());
                    Mood m = snapshot.toObject(Mood.class);
                    if(m!=null) {
                        if (mood == null) {
                            mood = new MutableLiveData<Mood>();
                        }
                        mood.setValue(m);
                    }

                } else {
                    Log.d(TAG, source + " data: null");
                }
            }
        });
    }

    public void saveMood(String date, int currentMood){
        Map<String, Object> mood = new HashMap<>();
        mood.put("mood", currentMood);
        fdb.collection("mood").document(date)
                .set(mood)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }



}
