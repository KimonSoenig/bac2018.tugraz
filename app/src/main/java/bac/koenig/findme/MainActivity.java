package bac.koenig.findme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AlertHandler alertHandler;
    MediaPlayer clickSound;
    Vibrator vibrate;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final ImageButton ibtn_speaker_aktivity = findViewById(R.id.ibtn_speaker_aktivity);



        //generate sound onclick
        clickSound = MediaPlayer.create(this, R.raw.speakersound);
        vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //set OnTouch to listen in Speaker
        ibtn_speaker_aktivity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Toast.makeText(getApplicationContext(), "Speaker ON", Toast.LENGTH_SHORT).show();
                    clickSound.start();
                    //check device SDK app is currently running on
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrate.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vibrate.vibrate(500);
                    }
                    //Start Speaker input
                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    //EndSpeaker input
                }
                return true;
            }
        });


        //Onclicklistener für camerabutton
        ImageButton ibtn_camera_activity = findViewById(R.id.ibtn_camera_activity);
        ibtn_camera_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRScan();
            }

            private void openQRScan() {
                Intent intent = new Intent(getApplicationContext(), QRScan.class);
                startActivity(intent);
            }
        });




        //ruft alertHandler class bei settingsbtn druck auf Übergebener Kontext: MainAct
        alertHandler = new AlertHandler(this);


        View.OnClickListener settingClicks = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertHandler.showSettingsAlert();
            }
        };

        ImageButton ibtn_settings = findViewById(R.id.ibtn_settings);
        Button btn_settings = findViewById(R.id.btn_settings);
        btn_settings.setOnClickListener(settingClicks);
        ibtn_settings.setOnClickListener(settingClicks);


        //Onclicklistener zum öffnen der Liste via BurgerButton
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ImageButton ibtn_open_drawer;
        ibtn_open_drawer = findViewById(R.id.ibtn_open_drawer);

        ibtn_open_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_room1:
                Toast.makeText(getApplicationContext(), "room1", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_room2:
                Toast.makeText(getApplicationContext(), "room2", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_room3:
                Toast.makeText(getApplicationContext(), "room3", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_biblio:
                Toast.makeText(getApplicationContext(), "biblio", Toast.LENGTH_LONG).show();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
