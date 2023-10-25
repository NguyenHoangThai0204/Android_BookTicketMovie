package com.example.myapplication.Signup_Signin_Main_Home;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

public class ActivityHistoryTickets extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Adapter_History_Ticketed viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_selector);

        // Truy xuất giá trị id từ SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String currentUserId = sharedPref.getString("currentUserId", ""); // giá trị mặc định là ""

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("LỊCH SỬ ĐẶT VÉ "));

        TextView customView = new TextView(this);
        customView.setText(tabLayout.getTabAt(0).getText());
        customView.setTextColor(getResources().getColor(R.color.purple_700));
        customView.setTextSize(28);
        customView.setTypeface(Typeface.create("Roboto", Typeface.BOLD));
        tabLayout.getTabAt(0).setCustomView(customView);

        // thiêt lập
//        viewPagerAdapter = new TabAdapterHome(LayoutInflater.from(this), getApplicationContext());
        viewPagerAdapter = new Adapter_History_Ticketed(LayoutInflater.from(this), getApplicationContext());
        viewPager.setAdapter(viewPagerAdapter);

    }

}
