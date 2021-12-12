package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Advice;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Regions;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Group;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Mood;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Pin;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Support;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Database.AppsDatabase;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Other.FHApplication;

public class Repository {
    private static final String TAG = "Getting document:";
    private FirebaseFirestore fdb;
    private AppsDatabase db;
    private ExecutorService executor;
    private static Repository instance;
    private MutableLiveData<ArrayList<Psychologist>> psychs;
    private MutableLiveData<ArrayList<Group>> groups;
    private MutableLiveData<ArrayList<Advice>> advice;
    private MutableLiveData<ArrayList<Regions>> regions;
    private MutableLiveData<ArrayList<String>> lcommunities;
    private MutableLiveData<Support> support;
    private MutableLiveData<Mood> mood;
    private MutableLiveData<User> user;
    private LiveData<Pin> _pin;
    private String dateOnly;

    public static Repository getInstance(){
        if(instance==null){
            instance = new Repository();
        }
        return instance;
    }

    private Repository(){
        db = AppsDatabase.getDatabase(FHApplication.getAppContext());
        fdb = FirebaseFirestore.getInstance();
        executor = Executors.newSingleThreadExecutor();
        _pin = db.pinDAO().getPin();

        loadData("psychologists", "p");
        loadData("groups", "g");
        loadData("denmark", "c");
        loadData("advice", "a");
        loadDocument("support", "supportinfo", 3);

        //Hvis vi havde implementeret oprettelsen af Pinkode, ville dette være unødvendigt.
        loadTestUser();

        //copied from: https://www.codegrepper.com/code-examples/java/java+get+current+date+without+time
        Date currentDate = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
        dateOnly = dateFormat.format(currentDate);


    }

    private void loadTestUser(){
        Pin thepin = new Pin("123456","0101907652", 0);
        setPinAsynch(thepin);
    }

    public LiveData<ArrayList<Psychologist>> getPsychologists(){
        return psychs;
    }

    public LiveData<ArrayList<Group>> getGroups(){
        return groups;
    }

    public LiveData<ArrayList<String>> getCommunities(){
        if(lcommunities == null){
            lcommunities = new MutableLiveData<ArrayList<String>>();
        }
        ArrayList<String> communities = new ArrayList<>();
        for (Regions r : regions.getValue())
        {
            for(String s : r.getCommunities()){
                communities.add(s);
            }
        }
        // sort alphabetically: https://stackoverflow.com/questions/708698/how-can-i-sort-a-list-alphabetically
        Collections.sort(communities);
        lcommunities.setValue(communities);
        return lcommunities;
    }

    public LiveData<Support> getSupport(){
        return support;
    }

    public MutableLiveData<Mood> getMood() {
        loadDocument("mood"+user.getValue().getId(), dateOnly, 2);
        return mood;}

    public LiveData<ArrayList<Advice>> getAdvice() {
        return advice;
    }

    public void loadTheUser(String userId){
        loadDocument("users", userId, 1);
    }

    public LiveData<User> getUser(){
        if(user == null){
            user = new MutableLiveData<User>();
        }
        return user;}

    public LiveData<Pin> getPin() {
        if (_pin == null) {
            _pin = new MutableLiveData<Pin>();
        }
        return _pin;
    }

    public void setPinAsynch (Pin p){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                    db.pinDAO().insertPin(p);
                }
        });
    }

    public void updatePinAsynch (Pin p){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.pinDAO().updatePin(p);
            }
        });
    }

    public void deletePinAsynch(Pin pin) {
        executor.execute(new Runnable() {
           @Override
            public void run() {
                db.pinDAO().deletePin(pin);
            }
        });
    }

    //Load data methods, from https://firebase.google.com/docs/firestore/query-data/get-data.
    private void loadData(String collectionName, String type) {
        fdb.collection(collectionName)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot valueg, @Nullable FirebaseFirestoreException error) {
                        if(valueg != null && !valueg.isEmpty()){
                                if(type.equals("p")) {
                                    ArrayList<Psychologist> updatedPsychologists = new ArrayList<>();
                                    for (DocumentSnapshot docg : valueg.getDocuments()) {
                                        Log.d(TAG, "DocumentSnapshot data: " + docg.getData());
                                        Psychologist p = docg.toObject(Psychologist.class);
                                        if (p != null) {
                                            updatedPsychologists.add(p);
                                        }
                                    }
                                    if (psychs == null) {
                                        psychs = new MutableLiveData<ArrayList<Psychologist>>();
                                    }
                                    psychs.setValue(updatedPsychologists);
                                }
                                if(type.equals("g")){
                                    ArrayList<Group> updatedGroups = new ArrayList<>();
                                    for(DocumentSnapshot docg : valueg.getDocuments()) {
                                        Log.d(TAG, "DocumentSnapshot data: " + docg.getData());
                                        Group g = docg.toObject(Group.class);
                                        if (g != null) {
                                            updatedGroups.add(g);
                                        }
                                    }
                                    if (groups == null) {
                                        groups = new MutableLiveData<ArrayList<Group>>();
                                    }
                                    groups.setValue(updatedGroups);
                                }
                                if(type.equals("a")) {
                                    ArrayList<Advice> updatedAdvice = new ArrayList<>();
                                    for (DocumentSnapshot docg : valueg.getDocuments()) {
                                        Log.d(TAG, "DocumentSnapshot data: " + docg.getData());
                                        Advice a = docg.toObject(Advice.class);
                                        if (a != null) {
                                            updatedAdvice.add(a);
                                        }
                                    }
                                    if (advice == null) {
                                        advice = new MutableLiveData<ArrayList<Advice>>();
                                    }
                                    advice.setValue(updatedAdvice);
                                }
                                if(type.equals("c")) {
                                    ArrayList<Regions> updatedCommunities = new ArrayList<>();
                                    for (DocumentSnapshot docg : valueg.getDocuments()) {
                                        Log.d(TAG, "DocumentSnapshot data: " + docg.getData());
                                        Regions c = docg.toObject(Regions.class);
                                        if (c != null) {
                                            updatedCommunities.add(c);
                                        }
                                    }
                                    if (regions == null) {
                                        regions = new MutableLiveData<ArrayList<Regions>>();
                                    }
                                    regions.setValue(updatedCommunities);
                                }
                            }
                        }
                });
    }

    private void loadDocument(String collectionName, String documentName, int type) {
        final DocumentReference docRef = fdb.collection(collectionName).document(documentName);
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
                    if(type == 1) {
                        User u = snapshot.toObject(User.class);
                        if (u != null) {
                            if (user == null) {
                                user = new MutableLiveData<User>();
                            }
                            user.setValue(u);
                        }
                    }
                    if(type == 2) {
                        Mood m = snapshot.toObject(Mood.class);
                        if (m != null) {
                            if (mood == null) {
                                mood = new MutableLiveData<Mood>();
                            }
                            mood.setValue(m);
                        }
                    }
                    if(type == 3) {
                        Support s = snapshot.toObject(Support.class);
                        if (s != null) {
                            if (support == null) {
                                support = new MutableLiveData<Support>();
                            }
                            support.setValue(s);
                        }
                    }

                } else {
                    Log.d(TAG, source + " data: null");
                }
            }
        });
    }

    public void updateMood(String userid, String date, int moodToUpdate){
        DocumentReference docRef = fdb.collection("mood"+userid).document(date);
        docRef
                .update("mood", moodToUpdate)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });

    }
    public void saveMood(String userid, String date, int currentMood){
        Map<String, Object> mood = new HashMap<>();
        mood.put("mood", currentMood);
        fdb.collection("mood"+userid).document(date)
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
