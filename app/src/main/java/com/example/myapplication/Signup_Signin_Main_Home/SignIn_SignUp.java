package com.example.myapplication.Signup_Signin_Main_Home;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class SignIn_SignUp extends Activity {
    TextView signUp, forget;
    EditText pwd, user;
    Button loGin;
    CheckBox showPass;
    // không cho back về
    @Override
    public void onBackPressed() {
        // do nothing, or show a mes
        // sage, or prompt the user to confirm exit
        // for example:
        Toast.makeText(this, "Không thể trở về", Toast.LENGTH_SHORT).show();
        // super.onBackPressed(); // uncomment this line to allow the back button to function normally
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        signUp = findViewById(R.id.signup);
        showPass = findViewById(R.id.cbShowMK);
        pwd = findViewById(R.id.password);
        loGin = findViewById(R.id.btnLogin);
        user = findViewById(R.id.userName);
        forget = findViewById(R.id.forgetPass);

// Chuyển giao diện đăng nhập
        loGin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = user.getText().toString().trim();
                String pas = pwd.getText().toString().trim();

                // Tạo và mở kết nối đến cơ sở dữ liệu
                SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.myapplication/files/data.db",
                        null, SQLiteDatabase.CREATE_IF_NECESSARY);

                String[] columns = {"id"};
                String selection = "password=? and phone_number=?";
                String[] selectionArgs = {pas, name};
                Cursor cursor = db.query("information_account", columns, selection, selectionArgs, null, null, null);

                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                    Intent in = new Intent(SignIn_SignUp.this, Main.class);
                    in.putExtra("id", id);

                    // Lưu giá trị id vào SharedPreferences
                    SharedPreferences sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("currentUserId", id); // id ở đây là giá trị id của tài khoản hiện tại
                    editor.apply();

                    startActivity(in);

                } else {
                    Toast.makeText(SignIn_SignUp.this, "Tài khoản hoặc mk không đúng", Toast.LENGTH_SHORT).show();
                }


            }
        });

// Chuyển giao diện đăng kí tài khoản
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SignIn_SignUp.this, SignUp_Account.class);
                startActivity(in);
            }
        });
// chuyển giao diện quên mật khẩu
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SignIn_SignUp.this, Forget_Password.class);
                startActivity(in);
            }
        });
// Hiện mật khẩu khi lick checkbox
        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    pwd.setTransformationMethod(null);
                else
                    pwd.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

    }


}
