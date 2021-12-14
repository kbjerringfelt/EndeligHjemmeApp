package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Pin;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Repository.Repository;

public class LogInViewModel extends ViewModel {

    private Repository repository;
    private LiveData<Pin> pin;

    public LogInViewModel(){
        repository = Repository.getInstance();
        pin = repository.getPin();
    }

    //Returnerer pinkoden som livedata
    public LiveData<Pin> getPin() {
        if(pin == null){
            pin= new MutableLiveData<Pin>();
        }
        return pin;
    }

    //Updaterer pinkoden
    public void updatePin(Pin pin) {
        repository.updatePinAsynch(pin);
    }

    //Sletter pinkode
    public void deletePin (Pin pin) {repository.deletePinAsynch(pin);}

    //Loader data for den bruger, der er logget ind
    public void loadUserData(String userId){
        repository.loadTheUser(userId);
    }

}
