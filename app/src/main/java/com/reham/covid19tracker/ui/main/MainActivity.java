package com.reham.covid19tracker.ui.main;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.reham.covid19tracker.R;
import com.reham.covid19tracker.ui.home.HomeFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setEnterTransition(new Slide());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }
}
