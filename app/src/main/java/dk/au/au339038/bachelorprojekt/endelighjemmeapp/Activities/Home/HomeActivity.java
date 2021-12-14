package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Activities.MenuActivity;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.Other.FHApplication;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Menuen i bunden af aktiviteten
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,R.id.navigation_calendar, R.id.navigation_chat, R.id.navigation_mood)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    //Menuen i toppen af aktiviteten
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_top,menu);
        return true;
    }

    //Menuen i toppen af aktiviteten
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.bar_menu) {
            Intent i = new Intent(HomeActivity.this, MenuActivity.class);
            launcher.launch(i);
        }

            return super.onOptionsItemSelected(item);
    }


    //Launcher nye aktiviteter
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        Bundle b = data.getExtras();
                        int j = b.getInt("int");
                        if (j == 1){
                            finish();
                        }

                    }

                }
            });

    //Sørger for, at man ikke kan gå tilbage fra hjemmeskærmen, og at brugeren får en besked om dette
    @Override
    public void onBackPressed() {
        Toast.makeText(FHApplication.getAppContext(),getText(R.string.cantgoback),Toast.LENGTH_SHORT).show();
    }

}