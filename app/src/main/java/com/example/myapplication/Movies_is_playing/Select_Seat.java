package com.example.myapplication.Movies_is_playing;

import com.example.myapplication.R;
import com.example.myapplication.Signup_Signin_Main_Home.*;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Select_Seat extends Activity {

    private Button back, bookTich;
    private GridLayout gridLayout;
    private RadioGroup group;
    private String text = null;
        @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_seat);
        gridLayout = findViewById(R.id.gridSeat);
        group = findViewById(R.id.radiFood);
            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton but = group.findViewById(checkedId);
                    if (but != null) {
                        // Lấy giá trị của RadioButton được chọn
                        text = but.getText().toString();
                    }

                }
            });

// lấy giá trị từ bảng cũ
            Intent in = getIntent();
            String name = in.getStringExtra("name");
            String time = in.getStringExtra("time");
            String date = in.getStringExtra("date");
            String hour = in.getStringExtra("hour");
            String address = in.getStringExtra("address");
            String order_date = in.getStringExtra("order_date");


        // ánh xạ các nút
        back = findViewById(R.id.backDatVe);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Select_Seat.this, Main.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                }
            });
            ArrayList<String> seats = new ArrayList<String>();

            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.myapplication/files/data.db",
                    null, SQLiteDatabase.CREATE_IF_NECESSARY);

            // Thực hiện truy vấn để lấy thông tin từ bảng "movies"
            String[] projection = {
                    "seat",
                    "date",
                    "hour",
                    "rap",
                   "name"
            };
            String sortOrder = "id ASC";
            Cursor cursor =
                    db.query(
                            "seats",
                            projection,
                            null,
                            null,
                            null,
                            null,
                            sortOrder
                    );

            // Duyệt qua các dòng và hiển thị thông tin lên các ImageView và TextView tương ứng
            while (cursor.moveToNext() ) {
                // Đọc thông tin từ Cursor
                String seatS = cursor.getString(cursor.getColumnIndexOrThrow("seat"));
                String dateS = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String hourS = cursor.getString(cursor.getColumnIndexOrThrow("hour"));
                String rapS = cursor.getString(cursor.getColumnIndexOrThrow("rap"));
                String nameS = cursor.getString(cursor.getColumnIndexOrThrow("name"));

                if( name.equals(nameS) && date.equals(dateS) && hour.equals(hourS) && address.equals(rapS )){
                    for (int i = 0; i < gridLayout.getChildCount(); i++) {
                        final Button but = (Button) gridLayout.getChildAt(i);
                        if(seatS.equals(but.getText().toString())){
                            but.setBackgroundColor(Color.parseColor("#EA5449"));
                            but.setEnabled(false);
                        }
                    }
                }
            }
            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                final Button but = (Button) gridLayout.getChildAt(i);
                but.setOnClickListener(new View.OnClickListener() {
                    boolean isSelected = false;
                    @Override
                    public void onClick(View v) {
                        if (!isSelected) {
                            but.setBackgroundColor(Color.parseColor("#B5E47D"));
                            isSelected = true;
                            seats.add(but.getText().toString());
                        } else {
                            but.setBackgroundColor(Color.TRANSPARENT);
                            isSelected = false;
                            // remove the unselected seat from the selectedSeats string
                            seats.remove(but.getText().toString());
                        }
                        but.invalidate();
                    }
                });
            }

            bookTich = findViewById(R.id.chonDatVe);
            bookTich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( seats == null || seats.size() == 0 ){
                        Toast.makeText(Select_Seat.this, "Vui lòng chọn chỗ", Toast.LENGTH_SHORT).show();
                    } else if (text == null) {
                        Toast.makeText(Select_Seat.this, "Vui lòng chọn món", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent in = new Intent(Select_Seat.this, Bill.class);
                        in.putExtra("name", name);
                        in.putExtra("time", time);
                        in.putExtra("date", date);
                        in.putExtra("address", address);
                        in.putExtra("order_date", order_date);
                        in.putExtra("hour", hour);
                        in.putStringArrayListExtra("seat", seats);
                        in.putExtra("food", text);
                        startActivity(in);
                    }
                }
            });

        }
}
