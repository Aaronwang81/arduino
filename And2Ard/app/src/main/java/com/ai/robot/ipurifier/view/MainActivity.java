package com.ai.robot.ipurifier.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ai.robot.ipurifier.R;
import com.ai.robot.ipurifier.manager.UIManager;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener
, MovementFragment.OnFragmentInteractionListener
, VoiceAssisantFragment.OnFragmentInteractionListener{


    @Override
    public void onBackPressed() {
        if(isMainPage()){
            super.onBackPressed();
        }else {
            showMainPage();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIManager.getInstance().setMainActivity(this);

        showMainPage();
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                UIManager.getInstance().showMainPage();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void showMovementController(){
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeholder_homePage, new MovementFragment());
        fragmentTransaction.commit();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.movement_title);
    }

    public void showVoiceAssisant(){
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeholder_homePage, new VoiceAssisantFragment());
        fragmentTransaction.commit();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.voiceassisant_title);
    }

    public void showMainPage(){
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeholder_homePage, new MainFragment());
        fragmentTransaction.commit();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(R.string.main_page);
    }

    private boolean isMainPage(){
        Fragment fragment = getFragmentManager().findFragmentById(R.id.placeholder_homePage);
        if(null != fragment){
            if(fragment instanceof MainFragment){
                return true;
            }
        }
        return false;
    }
}
