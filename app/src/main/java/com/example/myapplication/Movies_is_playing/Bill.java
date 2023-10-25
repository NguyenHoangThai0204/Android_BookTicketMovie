package com.example.myapplication.Movies_is_playing;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.Signup_Signin_Main_Home.*;

import java.util.ArrayList;

public class Bill extends AppCompatActivity {

    private Button delete, bookTick;
    private TextView name, hour, time, date, address, seat, food, money;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bill);

        Intent in = getIntent();
        String nameIn = in.getStringExtra("name");
        String timeIn = in.getStringExtra("time");
        String dateIn = in.getStringExtra("date");
        String hourIn = in.getStringExtra("hour");
        String addressIn = in.getStringExtra("address");
        String order_date = in.getStringExtra("order_date");
        ArrayList<String> seatIn = in.getStringArrayListExtra("seat");
        String seatText = String.join(", ", seatIn);
        String foodIn = in.getStringExtra("food");

        int dem = 0;
        for (int i = 0; i < seatIn.size(); i++ ){
            dem++;
        }

        double moeneyIn = dem * 50000;

        if( foodIn.equals("Bắp rang bơ - 60.000vnd")){
            moeneyIn = moeneyIn + 60000;
        }else if(foodIn.equals("Nước giải khát - 30.000vnd")){
            moeneyIn = moeneyIn + 30000;
        }else if(foodIn.equals("Combo - 80.000vnd")){
            moeneyIn = moeneyIn + 80000;
        }else{
            moeneyIn = moeneyIn;
        }
        String tien = String.valueOf(moeneyIn);
        // ánh xạ
        name = findViewById(R.id.textMovieName);
        name.setText(nameIn);
        time = findViewById(R.id.textMovieThoiLuong);
        time.setText(timeIn);
        hour = findViewById(R.id.textMovieTime);
        hour.setText(hourIn);
        date = findViewById(R.id.textMovieDate);
        date.setText(dateIn);
        address = findViewById(R.id.textRapMovie);
        address.setText(addressIn);
        seat = findViewById(R.id.textSeat);
        seat.setText(seatText);
        food = findViewById(R.id.textFood);
        food.setText(foodIn);
        money = findViewById(R.id.textMoney);
        money.setText(tien);



        delete = findViewById(R.id.butDelete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Bill.this, Main.class);
                    startActivity(in);
                }
            });

        bookTick = findViewById(R.id.butAccept);
//            bookTick.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Truy xuất giá trị id từ SharedPreferences
//                    SharedPreferences sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                    String currentUserId = sharedPref.getString("currentUserId", ""); // giá trị mặc định là ""
//
//                    SQLiteDatabase database = SQLiteDatabase.openDatabase("/data/data/com.example.myapplication/files/data.db",
//                            null, SQLiteDatabase.OPEN_READWRITE);
//                    try {
//                        // kết nối và lưu vào bảng dữ liệu account
//                        ContentValues contentValues = new ContentValues();
//                        contentValues.put("name", nameIn);
//                        contentValues.put("hour", hourIn);
//                        contentValues.put("time", timeIn);
//                        contentValues.put("date", dateIn);
//                        contentValues.put("seat",seatText );
//                        contentValues.put("food", foodIn);
//                        contentValues.put("money",tien);
//                        contentValues.put("id_account", currentUserId);
//                        contentValues.put("rap", addressIn);
//                        contentValues.put("order_date", order_date);
//                        long ketqua = database.insert("bills", null,contentValues);
//
//                        ContentValues contentValuesSeat = new ContentValues();
//                        contentValuesSeat.put("seat", seatText);
//                        contentValuesSeat.put("date", dateIn);
//                        contentValuesSeat.put("hour", hourIn);
//                        contentValuesSeat.put("rap", addressIn);
//                        contentValuesSeat.put("name", nameIn);
//                        long ketqua1 = database.insert("seats", null,contentValuesSeat);
//
//                        if( ketqua != -1 && ketqua1 != -1){
//                            Toast.makeText(Bill.this, "Đặt vé thành công", Toast.LENGTH_SHORT).show();
//                        }
//                        Intent in = new Intent(Bill.this, Main.class);
//                        startActivity(in);
//                    }catch(Exception e){
//                        e.printStackTrace();
//                    }finally {
//                        if (database != null) {
//                            database.close();
//                        }
//                    }
//                }
//            });
        bookTick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Truy xuất giá trị id từ SharedPreferences
                SharedPreferences sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                String currentUserId = sharedPref.getString("currentUserId", ""); // giá trị mặc định là ""

                SQLiteDatabase database = SQLiteDatabase.openDatabase("/data/data/com.example.myapplication/files/data.db",
                        null, SQLiteDatabase.OPEN_READWRITE);
                long ketqua = -1;
                boolean seatsInserted = true;
                try {
                    // kết nối và lưu vào bảng dữ liệu account
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", nameIn);
                    contentValues.put("hour", hourIn);
                    contentValues.put("time", timeIn);
                    contentValues.put("date", dateIn);
                    contentValues.put("seat",seatText );
                    contentValues.put("food", foodIn);
                    contentValues.put("money",tien);
                    contentValues.put("id_account", currentUserId);
                    contentValues.put("rap", addressIn);
                    contentValues.put("order_date", order_date);
                    ketqua = database.insert("bills", null,contentValues);
                    for(int i = 0; i < seatIn.size() ; i++){
                        ContentValues contentValuesSeat = new ContentValues();
                        contentValuesSeat.put("seat", seatIn.get(i));
                        contentValuesSeat.put("date", dateIn);
                        contentValuesSeat.put("hour", hourIn);
                        contentValuesSeat.put("rap", addressIn);
                        contentValuesSeat.put("name", nameIn);
                        long seatInsertResult = database.insert("seats", null,contentValuesSeat);
                        if(seatInsertResult == -1){
                            seatsInserted = false;
                            break;
                        }
                    }

                    if( ketqua != -1 && seatsInserted){
                        Toast.makeText(Bill.this, "Đặt vé thành công", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Bill.this, "Có lỗi xảy ra khi đặt vé", Toast.LENGTH_SHORT).show();
                    }
                    Intent in = new Intent(Bill.this, Main.class);
                    startActivity(in);
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    if (database != null) {
                        database.close();
                    }
                }
            }
        });

    }
}
