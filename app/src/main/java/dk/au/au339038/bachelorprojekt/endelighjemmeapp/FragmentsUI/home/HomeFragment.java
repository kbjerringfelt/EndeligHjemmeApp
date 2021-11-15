package dk.au.au339038.bachelorprojekt.endelighjemmeapp.FragmentsUI.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.ActivitiesUI.login.LogInViewModel;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel hvm;
    private LiveData<User> user;
    private FragmentHomeBinding binding;
    private TextView textView;
    private ImageView logo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textView = binding.textHome;
        logo = binding.logoimage;

        logo.setImageResource(R.drawable.logo);


        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        hvm = new ViewModelProvider(this).get(HomeViewModel.class);

        user = hvm.getUser();
        user.observe(this.getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User theuser) {
                setUpUI(theuser);
            }
        });

    }

    private void setUpUI(User theuser) {
        textView.setText(getText(R.string.welcome) +" "+ theuser.getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}