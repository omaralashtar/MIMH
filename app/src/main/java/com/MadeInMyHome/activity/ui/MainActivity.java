package com.MadeInMyHome.activity.ui;

import static com.MadeInMyHome.utilities.General.getToken;
import static com.MadeInMyHome.utilities.General.removeSharedPreference;
import static com.MadeInMyHome.utilities.constants.ISVISITOR;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.search.SearchAppActivity;
import com.MadeInMyHome.activity.user.userProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.activity.welcom.WelcomeActivity;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.databinding.ActivityMainBinding;
import com.MadeInMyHome.model.User;
import com.MadeInMyHome.utilities.constants;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.logger.ChatLogLevel;
import io.getstream.chat.android.livedata.ChatDomain;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    ShowUserProfileViewModel showUserProfileViewModel;
    TextView name;
    ImageView image;
    ChatClient client;
    private AppBarConfiguration drawerAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        if (!ISVISITOR) {
            showUserProfileViewModel = new ViewModelProvider(this).get(ShowUserProfileViewModel.class);

            client = new ChatClient.Builder(getString(R.string.apikey), this)
                    .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
                    .build();
            new ChatDomain.Builder(client, this).build();

            drawerAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home,
                    R.id.navigation_products,
                    R.id.navigation_categoryProducts,
                    R.id.navigation_favorite,
                    R.id.navigation_myProduct,
                    R.id.navigation_courses,
                    R.id.navigation_categoryCourse,
                    R.id.navigation_myCourse,
                    R.id.nav_chat,
                    R.id.navigation_userActivity,
                    R.id.navigation_logout
            )
                    .setOpenableLayout(binding.drawerLayout)
                    .build();
            binding.navViewDrawer.inflateMenu(R.menu.activity_main_drawer);
        } else {
            drawerAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home,
                    R.id.navigation_products,
                    R.id.navigation_categoryProducts,
                    R.id.navigation_courses,
                    R.id.navigation_categoryCourse
            )
                    .setOpenableLayout(binding.drawerLayout)
                    .build();
            binding.navViewDrawer.inflateMenu(R.menu.activity_main_drawer_visitor);

        }
        NavController navControllerDrawer = Navigation.findNavController(this,
                R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navControllerDrawer, drawerAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navViewDrawer, navControllerDrawer);

        if (!ISVISITOR) {
            View header = binding.navViewDrawer.getHeaderView(0);
            name = header.findViewById(R.id.name);
            image = header.findViewById(R.id.imageNav);

            showUserProfileViewModel.getUserProfile(this, getToken(this)).observe(this, new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    name.setText(user.getF_name() + " " + user.getL_name());
                    new GlideImage(MainActivity.this, constants.BASE_HOST + constants.IMAGE_USER + user.getImage(), image);

                    io.getstream.chat.android.client.models.User userChat = new io.getstream.chat.android.client.models.User();
                    userChat.setId(user.getId());
                    userChat.setRole("admin");
                    userChat.setName(user.getF_name() + " " + user.getL_name());
                    userChat.setImage(constants.BASE_HOST + constants.IMAGE_USER + user.getImage());

                    String token = client.devToken(user.getId());

                    client.connectUser(
                            userChat,
                            token
                    ).enqueue(result -> {
                        if (!result.isSuccess()) {
                            Toast.makeText(MainActivity.this, result.error().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            binding.navViewDrawer.getMenu().findItem(R.id.navigation_logout).setOnMenuItemClickListener(menuItem -> {
                AlertDialog.Builder a = new AlertDialog.Builder(this);
                a.setTitle(getResources().getString(R.string.logout));
                a.setMessage(getResources().getString(R.string.dialog_msg));
                a.setCancelable(false);
                a.setPositiveButton(getResources().getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainViewModel.deleteToken(MainActivity.this, getToken(MainActivity.this)).observe(MainActivity.this, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                                removeSharedPreference(MainActivity.this, "token");
                                finish();
                            }
                        });
                    }
                });
                a.setNegativeButton(getResources().getString(R.string.dialog_No), null);
                a.show();
                return true;
            });
        } else {
            binding.navViewDrawer.getMenu().findItem(R.id.navigation_login).setOnMenuItemClickListener(menuItem -> {
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                finish();
                return true;
            });
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setNeutralButton(getResources().getString(R.string.dialog_cancel), null);
        a.setCancelable(false);
        a.setIcon(R.drawable.logout);
        a.setNegativeButton(getResources().getString(R.string.dialog_No), null);
        a.setPositiveButton(getResources().getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        a.setTitle(R.string.dialog_Title);
        a.setMessage(getResources().getString(R.string.dialog_msg));
        a.show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ISVISITOR) {
            getMenuInflater().inflate(R.menu.appbar_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.profile) {
            Intent intent = new Intent(MainActivity.this, SearchAppActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.navigateUp(navController, drawerAppBarConfiguration)
                || super.onSupportNavigateUp();

    }
}