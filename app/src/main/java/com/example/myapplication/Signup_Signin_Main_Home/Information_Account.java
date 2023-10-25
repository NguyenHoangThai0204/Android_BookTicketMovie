package com.example.myapplication.Signup_Signin_Main_Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class Information_Account extends AppCompatActivity {
    private TextView user, mail, phon, birthday, gender;
    private ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_account);

        // Truy xuất giá trị id từ SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String currentUserId = sharedPref.getString("currentUserId", ""); // giá trị mặc định là ""


        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.myapplication/files/data.db",
                null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String[] projections={
                "id",
                "user_name",
                "email",
                "password",
                "phone_number",
                "birth_day",
                "gender"
        };
        String sortOrder = "id ASC";
        Cursor cursor = db.query(
                "information_account",
                projections,
                null,
                null,
                null,
                null,
                sortOrder
        );
        while (cursor.moveToNext()){

            user = findViewById(R.id.textUser);
            mail = findViewById(R.id.textEmail);
            phon = findViewById(R.id.textPhone);
            birthday = findViewById(R.id.textBirthday);
            gender = findViewById(R.id.textSex);

            String idInfor = cursor.getString(cursor.getColumnIndexOrThrow("id"));
            if(idInfor.equals(currentUserId)){
                String users = cursor.getString(cursor.getColumnIndexOrThrow("user_name"));
                String emails = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String phones = cursor.getString(cursor.getColumnIndexOrThrow("phone_number"));
                String births = cursor.getString(cursor.getColumnIndexOrThrow("birth_day"));
                String genders = cursor.getString(cursor.getColumnIndexOrThrow("gender"));

                user.setText(users);
                mail.setText(emails);
                phon.setText(phones);
                birthday.setText(births);
                gender.setText(genders);
            }
        }


        back = findViewById(R.id.backMainInAccount);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Information_Account.this, Main.class);
                    startActivity(in);
                }
            });


    }
}
