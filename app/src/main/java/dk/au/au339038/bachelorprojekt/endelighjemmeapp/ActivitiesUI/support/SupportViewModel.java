package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.support;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Support;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Repository;

public class SupportViewModel extends ViewModel {

    private LiveData<Support> support;
    private Repository repository;

    public SupportViewModel() {
        repository = Repository.getInstance();
        support = repository.getSupport();
    }

    public LiveData<Support> getSupport() {
        if (support == null) {
            support = new MutableLiveData<Support>();
        }
        return support;
    }

    public void setSupport(LiveData<Support> support) {
        this.support = support;
    }
}
