package dk.au.au339038.bachelorprojekt.endelighjemmeapp.FragmentsUI.mood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Mood;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Pin;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Repository;

public class MoodViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<Mood> mood;

    public MoodViewModel(){
        repository = Repository.getInstance();


    }

    public LiveData<Mood> getMood() {
        mood = repository.getMood();

        if(mood == null){
            mood = new MutableLiveData<Mood>();
            Mood defaultMood = new Mood(11);
            mood.setValue(defaultMood);
        }
        return mood;
    }

    public void setMood(MutableLiveData<Mood> mood) {
        this.mood = mood;
    }

    public void saveMood(String date, Mood m){
        int newmood = m.getMood();
        repository.saveMood(date, newmood);
    }


}