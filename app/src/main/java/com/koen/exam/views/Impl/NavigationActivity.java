package com.koen.exam.views.Impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.koen.exam.DataSingleton;
import com.koen.exam.MainActivity;
import com.koen.exam.R;
import com.koen.exam.views.dialogs.SheetsCreateCourse;

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
    SheetsCreateCourse sheetsCreateCourse;
    Toolbar toolbar;
    MenuItem menuItem;
    MenuItem item1, itemEdit;
    Menu appBarMenu;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.GONE);
        main = new MainFragment();
        store = new EditFragment();

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
        transaction.replace(R.id.scrim, main);
        transaction.commit();
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

    void setVisibleSaveBtn(Boolean flag){
        item1.setVisible(flag);
    }

    void setVisibleEditBtn(Boolean flag){
        itemEdit.setVisible(flag);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        item1 = appBarMenu.findItem(R.id.save_ques);

        switch (item.getItemId()) {
            case R.id.nav_main:
                getSupportActionBar().setTitle("Мои курсы");
                ft.replace(R.id.scrim, main);
                bottomSheetBehavior.setState(STATE_HIDDEN);
                floatingActionButton.hide();
                backgroundNavigation.setVisibility(View.INVISIBLE);
                frameLayout.setClickable(false);
                stateBottom = false;
                menuItem.setVisible(true);
                item1.setVisible(false);
                unVisibleArrow();
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
                menuItem.setVisible(false);
                item1.setVisible(false);
                unVisibleArrow();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void visibleArrow(){
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    public void unVisibleArrow(){
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_ques:{

                return false;
            }
            case android.R.id.home:{
                onBackPressed();
                return true;
            }
        }

        return false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        appBarMenu = menu;
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu, menu);
        item1 = appBarMenu.findItem(R.id.save_ques);
        item1.setVisible(false);
        itemEdit = appBarMenu.findItem(R.id.edit_test);
        itemEdit.setVisible(false);
        menuItem = menu.findItem(R.id.search_menu);
        searchView = (SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                main.findByName(query);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                searchView.setQuery("", false);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}