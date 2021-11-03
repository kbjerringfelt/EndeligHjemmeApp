package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Repository;

public class LogInViewModel extends ViewModel {

    private Repository repository;
    private LiveData<User> user;

    public LogInViewModel(){
        repository = Repository.getInstance();
        user = repository.getUser();
    }

    public LiveData<User> getUser() {
        if(user == null){
            user= new MutableLiveData<User>();
        }
        return user;
    }

    public void setUser(User user) {
        repository.setUserAsynch(user);
    }

    public void updateUser(User user){

    }

    public void loadUserData(User user){
        repository.loadUserData(user);
    }

}
