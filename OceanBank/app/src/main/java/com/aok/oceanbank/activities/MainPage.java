package com.aok.oceanbank.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aok.oceanbank.R;
import com.aok.oceanbank.databinding.ActivityMainPageBinding;
import com.aok.oceanbank.fragments.CardFragment;
import com.aok.oceanbank.fragments.CashbackFragment;
import com.aok.oceanbank.fragments.HomeFragment;
import com.aok.oceanbank.fragments.InfoFragment;
import com.aok.oceanbank.fragments.PayBillFragment;
import com.aok.oceanbank.fragments.SendFragment;
import com.aok.oceanbank.models.FirstNameModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.Map;

public class MainPage extends AppCompatActivity implements OnNavigationItemSelectedListener {
    ActivityMainPageBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    FirebaseUser firebaseUser;
    DocumentReference docRef;
    String userID;
    private DrawerLayout drawerLayout;
    private  BottomNavigationView bottomNavigationView;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.bottom_nav_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
                } else if (itemId == R.id.bottom_nav_send) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new SendFragment()).commit();
                } else if (itemId == R.id.bottom_nav_card) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new CardFragment()).commit();
                }

                return true;
            }
        });

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.bottom_nav_home);
        }


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.side_bar_nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
        } else if (itemId == R.id.side_bar_nav_cashback) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new CashbackFragment()).commit();
        } else if (itemId == R.id.side_bar_nav_pay_bill) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new PayBillFragment()).commit();
        } else if (itemId == R.id.side_bar_nav_info) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new InfoFragment()).commit();
        } else if (itemId == R.id.side_bar_nav_log_out) {
            Toast.makeText(MainPage.this, "Çıkış Yapıldı", Toast.LENGTH_SHORT).show();
            auth.signOut();
            Intent intent = new Intent(MainPage.this, LogInScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void getUserFirstName(){



    }
}