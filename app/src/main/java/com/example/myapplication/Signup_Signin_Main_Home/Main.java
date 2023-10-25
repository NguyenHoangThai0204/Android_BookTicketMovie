package com.example.myapplication.Signup_Signin_Main_Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapterHomeFr viewPagerAdapter;
    private ImageButton back;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_selector);

        Intent in = getIntent();
        id = in.getStringExtra("id");

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setTabTextColors(Color.BLACK, Color.RED);
        viewPager = findViewById(R.id.view_pager);


        // Thiết lập màu chữ cho Tab được chọn
        tabLayout.setSelectedTabIndicatorColor(Color.RED);

        // thêm các tab vào layout chính
        tabLayout.addTab(tabLayout.newTab().setText("Sắp chiếu"));
        tabLayout.addTab(tabLayout.newTab().setText("Đang chiếu"));

        // thiêt lập
//        viewPagerAdapter = new TabAdapterHome(LayoutInflater.from(this), getApplicationContext());
        viewPagerAdapter = new TabAdapterHomeFr(LayoutInflater.from(this), getApplicationContext());
        viewPager.setAdapter(viewPagerAdapter);

        // gọi đến các tab
        viewPager.addOnPageChangeListener(new TabLayout
                .TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout
                .ViewPagerOnTabSelectedListener(viewPager));
        // sự kiện chọn tab
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Thiết lập màu chữ cho Tab được chọn
                tabLayout.setSelectedTabIndicatorColor(Color.RED);
                tabLayout.setTabTextColors(Color.BLACK, Color.RED);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Thiết lập màu chữ cho các Tab chưa được chọn
                tabLayout.setTabTextColors(Color.BLACK, Color.GRAY);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);


        return true;
    }
    @Override
    public void onBackPressed() {
        // do nothing, or show a message, or prompt the user to confirm exit
        // for example:
        Toast.makeText(this, "Không thể trở về", Toast.LENGTH_SHORT).show();
        // super.onBackPressed(); // uncomment this line to allow the back button to function normally
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_1:
                Intent in1 = new Intent(Main.this, Information_Account.class);
                in1.putExtra("id",id);
                startActivity(in1);
                return true;
            case R.id.nav_item_2:
                Intent in2 = new Intent(Main.this, ActivityHistoryTickets.class);
                startActivity(in2);
                return true;
            case R.id.nav_item_3:
                Toast.makeText(Main.this, "Chưa hoàn thiện", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_item_4:
                Intent in = new Intent(Main.this, SignIn_SignUp.class);
                startActivity(in);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
