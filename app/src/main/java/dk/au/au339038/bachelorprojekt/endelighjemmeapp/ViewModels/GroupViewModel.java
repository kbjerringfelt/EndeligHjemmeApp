package dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Group;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Repository.Repository;

public class GroupViewModel extends ViewModel {
    private Repository repository;
    MutableLiveData<ArrayList<Group>> groupsForArea;
    LiveData<ArrayList<Group>> groups;
    LiveData<ArrayList<String>> communities;
    LiveData<User> user;

    public GroupViewModel(){
        repository = Repository.getInstance();
        groups = repository.getGroups();
        user = repository.getUser();
        communities = repository.getCommunities();
    }

    public LiveData<ArrayList<Group>> getGroupsForArea(String area) {
        groupsForArea = new MutableLiveData<ArrayList<Group>>();
        ArrayList<Group> newGroups = new ArrayList<Group>();
        for (Group p : groups.getValue())
        {
            if (p.getArea().equals(area)){
                newGroups.add(p);
            }
            groupsForArea.setValue(newGroups);
        }
        return groupsForArea;
    }

    public LiveData<User> getUser(){
        if (user == null){
            user = new MutableLiveData<User>();
        }
        return user;
    }

    public LiveData<ArrayList<String>> getCommunities(){
        if (communities == null){
            communities = new MutableLiveData<ArrayList<String>>();
        }
        return communities;
    }
}
