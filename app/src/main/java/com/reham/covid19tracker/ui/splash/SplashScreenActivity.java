package com.reham.covid19tracker.ui.splash;

import android.Manifest;
import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.reham.covid19tracker.R;
import com.reham.covid19tracker.ui.main.MainActivity;

import java.io.File;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long SPLASH_DISPLAY_LENGTH = 700;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Fade().setDuration(SPLASH_DISPLAY_LENGTH));
        getWindow().setExitTransition(new Fade().setDuration(SPLASH_DISPLAY_LENGTH));
        setContentView(R.layout.activity_splash_screen);

        if (isWorkingInternetPersent()) {
            splash();
        } else {
            runTask();
        }

    }


    public void splash() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    public void runTask() {
        if (isWorkingInternetPersent()) {
            splash();
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Internet Connection");
            alertDialog.setMessage("Sorry there was an error getting data from the Internet.\nNetwork Unavailable!");

            alertDialog.setPositiveButton("Retry", (dialog, which) -> {
                dialog.dismiss();
                runTask();
            });
            AlertDialog dialog = alertDialog.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    public boolean isWorkingInternetPersent() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getBaseContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }


    //fullscreen setup
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


}
