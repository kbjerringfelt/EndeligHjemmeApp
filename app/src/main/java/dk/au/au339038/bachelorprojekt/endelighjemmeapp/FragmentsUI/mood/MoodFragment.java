package dk.au.au339038.bachelorprojekt.endelighjemmeapp.FragmentsUI.mood;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Mood;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.FHApplication;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.databinding.MoodFragmentBinding;

public class MoodFragment extends Fragment {


    private MoodViewModel mvm;
    private MoodFragmentBinding binding;
    private ImageView moodImage;
    private int n;
    private int im;
    private String date;
    private LiveData<Mood> thisMood;
    private Mood moodToday;
    private SeekBar moodSkb;
    private TextView moodNumber;
    private String dateOnly;
    private Button saveBtn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = MoodFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        moodNumber = binding.resultTxt;
        moodImage = binding.smileyView;
        saveBtn = binding.moodSaveBtn;
        moodSkb = binding.seekBarMood;
        final TextView moodDate = binding.moodDate;
        final TextView moodGuide = binding.textViewGuide;


        //copied from: https://www.codegrepper.com/code-examples/java/java+get+current+date+without+time
        Date currentDate = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
        dateOnly = dateFormat.format(currentDate);
        moodDate.setText(dateOnly);
        moodGuide.setText(R.string.moodHowTo);


        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mvm = new ViewModelProvider(this).get(MoodViewModel.class);
        thisMood = mvm.getMood();
        thisMood.observe(this.getViewLifecycleOwner(), new Observer<Mood>() {
            @Override
            public void onChanged(@Nullable Mood m) {
                moodToday = m;
                im = moodToday.getMood();
                if(im !=11) {
                    updateUI(im);
                }
                else {
                    int i = 1;
                    updateUI(i);
                }
            }
        });



        moodSkb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                n = progress + 1;
                setImage(n);
                moodNumber.setText("" + n);
                mvm.getMood().getValue().setMood(n);
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
                int mt = moodToday.getMood();
                int nm = moodSkb.getProgress() + 1;
                Mood newMood = new Mood(nm);
                if(mt !=11) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                            .setMessage(R.string.saveDialog)
                            .setTitle(R.string.saveMood)
                            .setPositiveButton(R.string.saveAnyway, (dialogInterface, i) -> updateMood(newMood))
                            .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {});
                    builder.create().show();
                }
                else {
                    saveMood(newMood);
                    moodToday = newMood;
                }
            }
        });
    }


    private void saveMood(Mood m) {
        mvm.saveMood(dateOnly, m);
        im = moodSkb.getProgress();
        Toast.makeText(FHApplication.getAppContext(),getText(R.string.moodSaved),Toast.LENGTH_SHORT).show();
    }

    private void updateMood(Mood m) {
        mvm.updateMood(dateOnly, m);
        im = moodSkb.getProgress();
        Toast.makeText(FHApplication.getAppContext(),getText(R.string.moodSaved),Toast.LENGTH_SHORT).show();
    }

    private void updateUI(int i) {
        int mi = (i-1);
        moodSkb.setProgress(mi);
        moodNumber.setText("" + i);
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