package com.koen.exam.views.Impl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.koen.exam.DataSingleton;
import com.koen.exam.MainActivity;
import com.koen.exam.R;
import com.koen.exam.views.CoursesView;
import com.koen.exam.views.dialogs.SheetsCreateGroup;

import java.util.Objects;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

public class NavigationActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    BottomSheetBehavior bottomSheetBehavior;
    BottomAppBar bottomAppBar;
    FrameLayout frameLayout, backgroundNavigation;
    MainFragment main;
    EditFragment store;
    FloatingActionButton floatingActionButton;
    Boolean stateBottom = false;
    DataSingleton dataSingleton;
    SheetsCreateGroup sheetsCreateGroup;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.GONE);
        main = new MainFragment();
        store = new EditFragment();
        transaction.replace(R.id.scrim, main);
        transaction.commit();
        bottomSheetBehavior = BottomSheetBehavior.from(navigationView);
        bottomAppBar = (BottomAppBar)findViewById(R.id.bottomAppBar);
        bottomSheetBehavior.setState(STATE_HIDDEN);
        frameLayout = (FrameLayout)findViewById(R.id.scrim);
        backgroundNavigation = (FrameLayout)findViewById(R.id.backgroundNavigation);
        bottomSheetBehavior. setPeekHeight(700);
        bottomAppBar.setNavigationOnClickListener(v -> {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            backgroundNavigation.setVisibility(View.VISIBLE);
        });
        backgroundNavigation.setOnClickListener(v -> {
            bottomSheetBehavior.setState(STATE_HIDDEN);
            backgroundNavigation.setVisibility(View.INVISIBLE);
            frameLayout.setClickable(true);
            bottomAppBar.setHideOnScroll(false);
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetsCreateGroup = new SheetsCreateGroup(store);
                sheetsCreateGroup.show(getSupportFragmentManager(), "TAG");
                backgroundNavigation.setVisibility(View.INVISIBLE);
            }
        });
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == STATE_HIDDEN){
                    bottomSheetBehavior.setState(STATE_HIDDEN);
                    backgroundNavigation.setVisibility(View.INVISIBLE);
                    frameLayout.setClickable(true);
                    bottomAppBar.setHideOnScroll(stateBottom);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()) {
            case R.id.nav_main:
                getSupportActionBar().setTitle("Мои курсы");
                ft.replace(R.id.scrim, main);
                bottomSheetBehavior.setState(STATE_HIDDEN);
                floatingActionButton.hide();
                backgroundNavigation.setVisibility(View.INVISIBLE);
                frameLayout.setClickable(false);
                stateBottom = false;
                break;
            case R.id.nav_edit:
                ft.replace(R.id.scrim, store);
                getSupportActionBar().setTitle("Редактор");
                bottomSheetBehavior.setState(STATE_HIDDEN);
                floatingActionButton.show();
                bottomAppBar.setHideOnScroll(true);
                stateBottom = true;
                backgroundNavigation.setVisibility(View.INVISIBLE);
                frameLayout.setClickable(false);
                break;
            case R.id.nav_manage:
                dataSingleton = DataSingleton.getInstance();
                dataSingleton.jwtToken="";
                dataSingleton.saveInSharedPreferencesString("jwtToken", "");
                startActivity(new Intent(NavigationActivity.this, MainActivity.class));
                finish();
        }

        ft.commit();
        return true;
    }
}