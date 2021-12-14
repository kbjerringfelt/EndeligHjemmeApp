package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Mood;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Repository.Repository;

public class MoodViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<Mood> mood;
    private MutableLiveData<Mood> tempmood;
    private LiveData<User> user;

    public MoodViewModel(){
        repository = Repository.getInstance();
        user = repository.getUser();

    }

    //Returnerer humøret som livedata. Returnerer det midlertidige, hvis dette eksisterer, ellers det fra databasen, hvis dette eksisterer og ellers et default
    public LiveData<Mood> getMood() {
        mood = repository.getMood();
        if(mood == null){
            mood = new MutableLiveData<Mood>();
            Mood defaultMood = new Mood(11);
            mood.setValue(defaultMood);
        }
        if(tempmood == null){
            return mood;
        }
        else {
            return tempmood;
        }
    }

    //Sætter det midlertidige humør.
    public void setMood(int mood) {
        tempmood = new MutableLiveData<>();
        Mood m = new Mood(mood);
        tempmood.setValue(m);
    }

    //Gemmer humøret i databasen
    public void saveMood(String date, Mood m){
        int newmood = m.getMood();
        repository.saveMood(user.getValue().getId(), date, newmood);

    }

}