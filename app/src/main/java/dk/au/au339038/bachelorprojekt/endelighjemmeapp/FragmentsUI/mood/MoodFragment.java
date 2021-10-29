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
import android.widget.TextView;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.databinding.MoodFragmentBinding;

public class MoodFragment extends Fragment {


    private MoodViewModel moodViewModel;
    private MoodFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        moodViewModel =
                new ViewModelProvider(this).get(MoodViewModel.class);

        binding = MoodFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMood;
        moodViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}