package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.group;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Group;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Repository;

public class GroupViewModel extends ViewModel {
    private Repository repository;
    LiveData<ArrayList<Group>> groups;
    LiveData<User> user;

    public GroupViewModel(){
        repository = Repository.getInstance();
        groups = repository.getGroups();
        user = repository.getUser();
    }

    public LiveData<ArrayList<Group>> getGroups() {
        if(groups == null){
            groups = new MutableLiveData<ArrayList<Group>>();
        }
        return groups;
    }

    public void setPsychologists(LiveData<ArrayList<Group>> groups) {
        this.groups = groups;
    }

    LiveData<User> getUser(){
        if (user == null){
            user = new MutableLiveData<User>();
        }
        return user;
    }
}
