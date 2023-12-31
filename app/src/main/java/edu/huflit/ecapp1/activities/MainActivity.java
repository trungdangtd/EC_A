package edu.huflit.ecapp1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import edu.huflit.ecapp1.fragments.HomeFragment;
import edu.huflit.ecapp1.R;
import edu.huflit.ecapp1.fragments.ManageFragment;

public class MainActivity extends AppCompatActivity {

    Fragment homeFragment;
    FirebaseAuth auth;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        if( auth.getCurrentUser().getEmail() !=null && auth.getCurrentUser().getEmail().equals("admin@gmail.com")){
            Toast.makeText(this, "Quản lý", Toast.LENGTH_SHORT).show();
            homeFragment = new ManageFragment();
            loadFragment(homeFragment);
        }else{
            Toast.makeText(this, "Khách hàng", Toast.LENGTH_SHORT).show();
            homeFragment = new HomeFragment();
            loadFragment(homeFragment);
        }
    }

    private void loadFragment(Fragment homeFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container,homeFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.menu_logout){
            auth.signOut();
            startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            finish();
        } else
        if (id == R.id.menu_my_cart) {
            startActivity(new Intent(MainActivity.this,CartActivity.class));
        }
        return true;
    }
}