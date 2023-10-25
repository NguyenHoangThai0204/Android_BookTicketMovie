package com.example.myapplication.Signup_Signin_Main_Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.R;
import com.example.myapplication.Movies_is_playing.*;

public class TabAdapterMovies extends AppCompatActivity {
    private Spinner spin ;
    private ListView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_in_day);

        spin = findViewById(R.id.spinerBeta);
        list = findViewById(R.id.listVKhungGioChieuPhim);

        Intent in = getIntent();
        String name = in.getStringExtra("name");
        String time = in.getStringExtra("time");
        String date = in.getStringExtra("date");
        String order_date = in.getStringExtra("order_date");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(TabAdapterMovies.this,
                R.array.spinerBeta, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        // bắt sự kiện cho từng item địa chỉ beta
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // lấy thông tin của rạp
                String address = parent.getItemAtPosition(position).toString();
                Toast.makeText(TabAdapterMovies.this, address, Toast.LENGTH_SHORT).show();

                // Khai báo một mảng lưu resource id của từng địa điểm
                int[] gioChieuResIds = {R.array.gioChieuBetaQuangTrung, R.array.gioChieuBetaNguyenVanBao, R.array.gioChieuBetaBinhDuong};


                // Lấy vị trí của địa điểm được chọn trong danh sách
                int selectedPosition = spin.getSelectedItemPosition();

                // Lấy resource id của mảng giờ chiếu tương ứng với địa điểm được chọn
                int gioChieuResId = gioChieuResIds[selectedPosition];

                // Tạo adapter cho list view
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(TabAdapterMovies.this, gioChieuResId, android.R.layout.simple_list_item_1);
                adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                list.setAdapter(adapter);

                // Bắt sự kiện click item trên list view
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String hour = parent.getItemAtPosition(position).toString();
                        Intent in = new Intent(TabAdapterMovies.this, Select_Seat.class);
                        in.putExtra("name", name);
                        in.putExtra("time", time);
                        in.putExtra("date", date);
                        in.putExtra("address", address);
                        in.putExtra("order_date", order_date);
                        in.putExtra("hour", hour);
                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(in);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        }

}

