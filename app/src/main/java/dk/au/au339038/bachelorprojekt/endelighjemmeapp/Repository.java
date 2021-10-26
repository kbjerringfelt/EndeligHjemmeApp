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

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;

public class Repository {
    FirebaseFirestore db;
    private ExecutorService executor;
    private static Repository instance;
    MutableLiveData<ArrayList<Psychologist>> psychs;

    public static Repository getInstance(){
        if(instance==null){
            instance = new Repository();
        }
        return instance;
    }

    //constructor - takes Application object for context
    private Repository(){
        db = FirebaseFirestore.getInstance();
        executor = Executors.newSingleThreadExecutor();
        loadData();

    }

    public LiveData<ArrayList<Psychologist>> getPsychologists(){
        if(psychs==null){
            psychs = new MutableLiveData<ArrayList<Psychologist>>();
        }
        return psychs;
    }

    private void loadData() {
        db.collection("psychologists")
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
                            psychs.setValue(updatedPsychologists);
                        }
                    }
                });

    }

}
