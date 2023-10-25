package com.example.myapplication.Signup_Signin_Main_Home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;

public class History_Ticketed extends Activity {
    private TextView rap, name, hour, date, seat, money;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ticket_history);

        rap = findViewById(R.id.textRapMovie);
        name = findViewById(R.id.textMovieName);
        hour = findViewById(R.id.textMovieTime);
        date = findViewById(R.id.textMovieDate);
        seat = findViewById(R.id.textSeat);
        money= findViewById(R.id.textMoney);

        // nhận các giá trị được truyền từ intent
        Intent intent = getIntent();
        String rapIn = intent.getStringExtra("rap");
        String nameIn = intent.getStringExtra("name");
        String hourIn = intent.getStringExtra("hour");
        String dateIn = intent.getStringExtra("date");
        String seatIn = intent.getStringExtra("seat");
        String moneyIn = intent.getStringExtra("money");
        String orderIn = intent.getStringExtra("order_date");

        rap.setText(rapIn);
        name.setText(nameIn);
        hour.setText(hourIn);
        date.setText(dateIn);
        seat.setText(seatIn);
        money.setText(moneyIn);
    }



//        // Truy xuất giá trị id từ SharedPreferences
//        SharedPreferences sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        String currentUserId = sharedPref.getString("currentUserId", ""); // giá trị mặc định là ""
//
//
//        SQLiteDatabase database = SQLiteDatabase.openDatabase("/data/data/com.example.myapplication/files/data.db",
//                null, SQLiteDatabase.OPEN_READWRITE);
//        String[] projectionsc = {
//                "name",
//                "hour",
//                "time",
//                "date",
//                "seat",
//                "food",
//                "money",
//                "id_account",
//                "rap"
//        };
//        String selection = "id_account=?";
//        String[] selectionArg = {currentUserId};
//        Cursor cursor = database.query("bills", projectionsc, selection, selectionArg, null, null, null);
//        if (cursor != null && cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            String rapIn = cursor.getString(cursor.getColumnIndexOrThrow("rap"));
//            String nameIn = cursor.getString(cursor.getColumnIndexOrThrow("name"));
//            String hourIn = cursor.getString(cursor.getColumnIndexOrThrow("hour"));
//            String dateIn = cursor.getString(cursor.getColumnIndexOrThrow("date"));
//            String seatIn = cursor.getString(cursor.getColumnIndexOrThrow("seat"));
//            String moneyIn = cursor.getString(cursor.getColumnIndexOrThrow("money"));
//
//            rap.setText(rapIn);
//            name.setText(nameIn);
//            hour.setText(hourIn);
//            date.setText(dateIn);
//            seat.setText(seatIn);
//            money.setText(moneyIn);
//
//        } else {
//            Toast.makeText(History_Ticketed.this, "Chưa bao giờ đặt vé", Toast.LENGTH_SHORT).show();
//        }

}
