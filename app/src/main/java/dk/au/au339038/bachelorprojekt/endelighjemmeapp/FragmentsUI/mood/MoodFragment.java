package dk.au.au339038.bachelorprojekt.endelighjemmeapp.FragmentsUI.mood;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.databinding.MoodFragmentBinding;

public class MoodFragment extends Fragment {


    private MoodViewModel mvm;
    private MoodFragmentBinding binding;
    private ImageView moodImage;
    private int i;
    private String date;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mvm = new ViewModelProvider(this).get(MoodViewModel.class);

        binding = MoodFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView moodNumber = binding.resultTxt;
        moodImage = binding.smileyView;
        final Button saveBtn = binding.moodSaveBtn;
        final SeekBar moodSkb = binding.seekBarMood;
        final TextView moodDate = binding.moodDate;

        //copied from: https://www.codegrepper.com/code-examples/java/java+get+current+date+without+time
        Date currentDate = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
        String dateOnly = dateFormat.format(currentDate);
        moodDate.setText(dateOnly);


        moodSkb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                i = progress + 1;
                setImage(i);
                moodNumber.setText("" + i);
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

            }
        });


        return root;
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