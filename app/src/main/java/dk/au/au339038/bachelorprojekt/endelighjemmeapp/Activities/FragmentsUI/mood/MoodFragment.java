package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.FragmentsUI.mood;

import static android.app.Activity.RESULT_OK;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.NotDone.MoodGraphActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Mood;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ViewModels.MoodViewModel;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Other.FHApplication;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.databinding.MoodFragmentBinding;

public class MoodFragment extends Fragment {


    private MoodViewModel mvm;
    private MoodFragmentBinding binding;
    private ImageView moodImage;
    private int im;
    private LiveData<Mood> thisMood;
    private SeekBar moodSkb;
    private TextView moodNumber, moodGuide, enteredMood;
    private String dateOnly;
    private Button saveBtn, overviewBtn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = MoodFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        moodNumber = binding.resultTxt;
        moodImage = binding.smileyView;
        saveBtn = binding.moodSaveBtn;
        moodSkb = binding.seekBarMood;
        overviewBtn = binding.moodOverviewBtn;
        final TextView moodDate = binding.moodDate;
        moodGuide = binding.textViewGuide;
        enteredMood = binding.textEnteredMood;


        //copied from: https://www.codegrepper.com/code-examples/java/java+get+current+date+without+time
        Date currentDate = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
        dateOnly = dateFormat.format(currentDate);
        moodDate.setText(dateOnly);


        mvm = new ViewModelProvider(this).get(MoodViewModel.class);


        moodSkb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int n = progress + 1;
                setImage(n);
                moodNumber.setText("" + n);
                mvm.setMood(n);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nm = moodSkb.getProgress() + 1;
                Mood newMood = new Mood(nm);
                if(im !=11) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                            .setMessage(R.string.saveDialog)
                            .setTitle(R.string.saveMood)
                            .setPositiveButton(R.string.saveAnyway, (dialogInterface, i) -> saveMood(newMood))
                            .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {});
                    builder.create().show();
                }
                else {
                    saveMood(newMood);
                }
            }
        });

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();

                        }
                    }
                });

        overviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MoodGraphActivity.class);
                launcher.launch(i);
            }
        });

        thisMood = mvm.getMood();
        thisMood.observe(getActivity(), new Observer<Mood>() {
            @Override
            public void onChanged(@Nullable Mood m) {
                im = m.getMood();
                if(im !=11) {
                    updateUI(im, "" + getText(R.string.alreadySavedMood));
                }
                else {
                    int i = 1;
                    updateUI(i, "");
                }
            }
        });



        return root;
    }


    private void saveMood(Mood m) {
        im = m.getMood();
        mvm.saveMood(dateOnly, m);
        Toast.makeText(FHApplication.getAppContext(),getText(R.string.moodSaved),Toast.LENGTH_SHORT).show();
        updateUI(m.getMood(), getText(R.string.alreadySavedMood).toString());
    }

    /*private void updateMood(Mood m) {
        im = m.getMood();
        mvm.updateMood(dateOnly, m);
        Toast.makeText(FHApplication.getAppContext(),getText(R.string.moodSaved),Toast.LENGTH_SHORT).show();
        updateUI(m.getMood(), getText(R.string.alreadySavedMood).toString());
    }*/

    private void updateUI(int i, String alreadyMood) {
        int mi = (i-1);
        moodSkb.setProgress(mi);
        moodNumber.setText("" + i);
        moodGuide.setText(getText(R.string.moodHowTo));
        enteredMood.setText(alreadyMood);
        setImage(i);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setImage(int i){
        if (i == 1){
            moodImage.setImageResource(R.mipmap.ic_mood_1);
        }
        if (i == 2){
            moodImage.setImageResource(R.mipmap.ic_mood_2);
        }
        if (i == 3){
            moodImage.setImageResource(R.mipmap.ic_mood_3);
        }
        if (i == 4){
            moodImage.setImageResource(R.mipmap.ic_mood_4);
        }
        if (i == 5){
            moodImage.setImageResource(R.mipmap.ic_mood_5);
        }
        if (i == 6){
            moodImage.setImageResource(R.mipmap.ic_mood_6);
        }
        if (i == 7){
            moodImage.setImageResource(R.mipmap.ic_mood_7);
        }
        if (i == 8){
            moodImage.setImageResource(R.mipmap.ic_mood_8);
        }
        if (i == 9){
            moodImage.setImageResource(R.mipmap.ic_mood_9);
        }
        if (i == 10){
            moodImage.setImageResource(R.mipmap.ic_mood_10);
        }
    }
}