package dk.au.au339038.bachelorprojekt.endelighjemmeapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Group;
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



}
