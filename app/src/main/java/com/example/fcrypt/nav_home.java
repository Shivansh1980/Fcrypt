package com.example.fcrypt;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class nav_home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private  NavController navController;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_home);

        toolbar = findViewById(R.id.toolbar_nav_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawer = findViewById(R.id.drawer_layout_for_nav_home);
        navigationView = findViewById(R.id.nav_view_for_nav_home);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_for_nav_home);

        NavigationUI.setupActionBarWithNavController(this, navController, drawer);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, drawer);
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            if(count == 0) super.onBackPressed();
            else {
                getSupportFragmentManager().popBackStack();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dashboardFragment: {
                navController.navigate(R.id.dashboardFragment);
                break;
            }
            case R.id.encryptorFragment: {
                navController.navigate(R.id.encryptorFragment);
            }
        }
        item.setChecked(true);
        drawer.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}