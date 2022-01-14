package rob.netflix2app.Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import rob.netflix2app.R;
import rob.netflix2app.SearchFragment;

public class NavigationDrawerMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = NavigationDrawerMainActivity.class.getSimpleName();
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naviagtion_drawer);




        initDrawerAndToolbar();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DisplayViewFragment()).commit();
             navigationView.setCheckedItem(R.id.nav_profile_drawer);

        }

        initBottomNavigationBarFunction();
    }


    private void initDrawerAndToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        //get hamburger
        toggle.syncState();


        //Resize photo
        Drawable dr = getResources().getDrawable(R.drawable.avatar_photo);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d2 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 40, 40, true));
        toolbar.setNavigationIcon(d2);



        View header = navigationView.getHeaderView(0);
        ImageView imageAvatar = header.findViewById(R.id.img_profile);
        imageAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NavigationDrawerMainActivity.this, "Image Avatar Pressed", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });





    }

    private void initBottomNavigationBarFunction() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_nav_bottom:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new DisplayViewFragment()).commit();
                        break;
                    case R.id.search_nav_bottom:

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new SearchFragment()).commit();
                        break;
                    case R.id.notification_nav_bottom:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new NotificationsFragment()).commit();
                        break;
                    case R.id.private_nav_bottom:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new PrivateMessageFragment()).commit();
                        break;

                }
                return true;
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile_drawer:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_lists_drawer:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DisplayViewFragment()).commit();
                Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_bookmarks_drawer:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}