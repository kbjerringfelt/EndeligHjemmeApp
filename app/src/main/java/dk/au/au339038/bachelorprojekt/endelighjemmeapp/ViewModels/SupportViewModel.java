package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Support;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Repository.Repository;

public class SupportViewModel extends ViewModel {

    private LiveData<Support> support;
    private Repository repository;

    public SupportViewModel() {
        repository = Repository.getInstance();
        support = repository.getSupport();
    }

    //Returnerer support livedata
    public LiveData<Support> getSupport() {
        if (support == null) {
            support = new MutableLiveData<Support>();
        }
        return support;
    }

}

