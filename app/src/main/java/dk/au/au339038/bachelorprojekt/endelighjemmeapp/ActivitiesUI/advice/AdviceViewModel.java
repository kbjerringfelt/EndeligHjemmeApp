package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.advice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Advice;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Group;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Repository;

public class AdviceViewModel extends ViewModel {
    private Repository repository;
    LiveData<ArrayList<Advice>> advice;

    public AdviceViewModel(){
        repository = Repository.getInstance();
        //advice = repository.getAdvice();
    }

    public LiveData<ArrayList<Advice>> getAdvice() {
        if(advice == null){
            advice = new MutableLiveData<ArrayList<Advice>>();
        }
        return advice;
    }

    public void setAdvice(LiveData<ArrayList<Advice>> advice) {
        this.advice = advice;
    }
}
