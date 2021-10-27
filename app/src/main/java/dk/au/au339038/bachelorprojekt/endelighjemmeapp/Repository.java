package dk.au.au339038.bachelorprojekt.endelighjemmeapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Pin;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Database.AppsDatabase;

public class Repository {
    private FirebaseFirestore fdb;
    private AppsDatabase db;
    private ExecutorService executor;
    private static Repository instance;
    private MutableLiveData<ArrayList<Psychologist>> psychs;
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
        loadData();

    }

    public LiveData<ArrayList<Psychologist>> getPsychologists(){
       // if(psychs==null){
       //     psychs = new MutableLiveData<ArrayList<Psychologist>>();
       // }
        return psychs;
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

    private void loadData() {
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

}
