package com.MadeInMyHome.activity.ui;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.MadeInMyHome.R;
import com.MadeInMyHome.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration drawerAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        binding.toolbarTitle.setText(getString(R.string.app_name));

        binding.drawerLayout.addDrawerListener(
                new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close));
//nav
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        ).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        drawerAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_myCourse,
                R.id.navigation_categoryCourse,
                R.id.nav_slideshow,
                R.id.navigation_products,
                R.id.navigation_courses,
                R.id.navigation_favorite,
                R.id.navigation_myProduct,
                R.id.navigation_categoryProducts)
                .setOpenableLayout(binding.drawerLayout)
                .build();
        NavController navControllerDrawer = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navViewDrawer, navControllerDrawer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.navigateUp(navController, drawerAppBarConfiguration)
                || super.onSupportNavigateUp();

    }
}