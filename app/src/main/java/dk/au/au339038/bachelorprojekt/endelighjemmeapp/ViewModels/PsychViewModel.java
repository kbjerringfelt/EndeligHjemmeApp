package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Repository.Repository;

public class PsychViewModel extends ViewModel {
    private Repository repository;
    private LiveData<ArrayList<Psychologist>> psychologists;
    private MutableLiveData<ArrayList<Psychologist>> psychologistsForArea;
    private LiveData<ArrayList<String>> communities;
    private LiveData<User> user;

    public PsychViewModel(){
        repository = Repository.getInstance();
        psychologists = repository.getPsychologists();
    }

    //Returnerer psykologer for den valgte kommune, eller default brugerens kommune som livedata liste.
     public LiveData<ArrayList<Psychologist>> getPsychologistsForArea(String area) {
        psychologistsForArea = new MutableLiveData<ArrayList<Psychologist>>();
        ArrayList<Psychologist> newPsychologists = new ArrayList<Psychologist>();
        for (Psychologist p : psychologists.getValue())
        {
            if (p.getArea().equals(area)){
                newPsychologists.add(p);
            }
            psychologistsForArea.setValue(newPsychologists);
        }
        return psychologistsForArea;
    }

    //returnerer bruger som livedata.
    public LiveData<User> getUser(){
        user = repository.getUser();
        if (user == null){
            user = new MutableLiveData<User>();
        }
        return user;
    }

    //Returnerer kommune livedata
    public LiveData<ArrayList<String>> getCommunities(){
        communities = repository.getCommunities();
        if (communities == null){
            communities = new MutableLiveData<ArrayList<String>>();
        }
        return communities;
    }

}
