package dk.au.au339038.bachelorprojekt.endelighjemmeapp.FragmentsUI.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Name");
    }

    public LiveData<String> getText() {
        return mText;
    }
}