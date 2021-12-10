package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Repository.Repository;

public class HomeViewModel extends ViewModel {

    private Repository repository;
    private LiveData<User> user;

    public HomeViewModel(){
        repository = Repository.getInstance();
        user = repository.getUser();
    }

    public LiveData<User> getUser() {
        if(user == null){
            user= new MutableLiveData<User>();
        }
        return user;
    }
}