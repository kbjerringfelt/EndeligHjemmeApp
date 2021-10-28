package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.ui.mood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoodViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MoodViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is mood fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}