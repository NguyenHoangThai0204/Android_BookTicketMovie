package com.example.myapplication.Movies_is_playing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.Signup_Signin_Main_Home.*;

import java.util.ArrayList;
import java.util.Calendar;

public class Select_Day extends AppCompatActivity {
    private Button back;
    private ListView list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_day);
        back = findViewById(R.id.butBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Select_Day.this, Main.class);
                startActivity(in);
            }
        });
        list = findViewById(R.id.listVDate);
        // Tạo ArrayList để lưu trữ các giá trị của phương thức getCurrentDate()
        // tạo giá trị các view ngày
        ArrayList<String> dateList = new ArrayList<>();
        for (int i = 0; i < 5; i++) { // lặp qua 2 ngày tiếp theo
            String currentDate = getCurrentDate(i);
            dateList.add(currentDate);
        }

        // nhận lại giá trị intent
        Intent in = getIntent();
        String name = in.getStringExtra("name");
        String time = in.getStringExtra("time");

        // Tạo ArrayAdapter và đặt adapter cho ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dateList);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = (String) parent.getItemAtPosition(position);
                Intent in = new Intent(Select_Day.this, TabAdapterMovies.class);
                in.putExtra("name", name);
                in.putExtra("time", time);
                in.putExtra("date", selectedValue);
                in.putExtra("order_date", getOrderDate());
                startActivity(in);
            }
        });
    }
    private String getCurrentDate(int i ) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH) + i;
        int month = calendar.get(Calendar.MONTH)  + 1 ;
        int year = calendar.get(Calendar.YEAR);
        return  day + " / " + month + " / " + year;
    }

    private String getOrderDate(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH) ;
        int month = calendar.get(Calendar.MONTH)  + 1 ;
        int year = calendar.get(Calendar.YEAR);
        return  day + " / " + month + " / " + year;
    }
}
