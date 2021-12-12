package dk.au.au339038.bachelorprojekt.endelighjemmeapp.FragmentsUI.mood;

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
        mood = repository.getMood();
        user = repository.getUser();
    }

    public LiveData<Mood> getMood() {
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

    public void setMood(int mood) {
        tempmood = new MutableLiveData<>();
        Mood m = new Mood(mood);
        tempmood.setValue(m);
    }

    public void saveMood(String date, Mood m){
        int newmood = m.getMood();
        repository.saveMood(user.getValue().getId(), date, newmood);

    }

    /*public LiveData<User> getUser() {
        if(user == null){
            user= new MutableLiveData<User>();
        }
        return user;
    }*/

    public void updateMood(String date, Mood m){
        int newmood = m.getMood();
        repository.updateMood(user.getValue().getId(), date, newmood);
    }


}