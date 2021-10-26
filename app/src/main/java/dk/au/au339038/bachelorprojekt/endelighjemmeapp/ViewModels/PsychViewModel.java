package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Repository;

public class PsychViewModel extends ViewModel {
    private Repository repository;
    LiveData<ArrayList<Psychologist>> psychologists;

    public PsychViewModel(){
        repository = Repository.getInstance();
        psychologists = repository.getPsychologists();
    }

    public LiveData<ArrayList<Psychologist>> getPsychologists() {
        if(psychologists == null){
            psychologists = new MutableLiveData<ArrayList<Psychologist>>();
        }
        return psychologists;
    }

    public void setPsychologists(LiveData<ArrayList<Psychologist>> psychologists) {
        this.psychologists = psychologists;
    }

}
