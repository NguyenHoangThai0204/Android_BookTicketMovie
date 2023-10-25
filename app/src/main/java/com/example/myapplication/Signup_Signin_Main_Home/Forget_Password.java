package com.example.myapplication.Signup_Signin_Main_Home;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Forget_Password extends AppCompatActivity {
    private Button up, back;
    private EditText sdt, mk, mksgain;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_forget_password);
        sdt = findViewById(R.id.upSDT);
        mk = findViewById(R.id.upPassNew);
        mksgain = findViewById(R.id.upPassAgain);

        up = findViewById(R.id.butUpdate);
            up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String n = sdt.getText().toString();
                    String m = mk.getText().toString();
                    String mg = mksgain.getText().toString();
                    if( m.equals(mg)){
                        SQLiteDatabase database = SQLiteDatabase.openDatabase("/data/data/com.example.myapplication/files/data.db",
                                null, SQLiteDatabase.OPEN_READWRITE);
                        // Điều kiện WHERE để chỉ định bản ghi được cập nhật
                        String whereClause = "phone_number = ?";
                        String[] whereArgs = new String[] {n}; // Thay đổi số điện thoại ở đây

                        // Giá trị mới của mật khẩu
                        String newPassword = m;

                        // Thực hiện câu lệnh cập nhật
                        ContentValues values = new ContentValues();
                        values.put("password", newPassword); // Thay đổi tên cột password nếu cần
                        int rowsUpdated = database.update("information_account", values, whereClause, whereArgs);
                        Toast.makeText(Forget_Password.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                        mk.setText("");
                        mksgain.setText("");
                        sdt.setText("");

                    }else{
                        Toast.makeText(Forget_Password.this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        back = findViewById(R.id.butBackUp);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Forget_Password.this, SignIn_SignUp.class);
                    startActivity(in);
                }
            });

    }
}
