package com.example.myapplication.Signup_Signin_Main_Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.Calendar;
import com.example.myapplication.*;
public class SignUp_Account extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText ngay, ten, email, phone, pass, passAgain;
    String name, em, phon, pa, gioiTinh, bir ;
    Button dk, exit;
    TableRow clickNgay;
    CheckBox checkAccept;
    RadioButton raGenderDk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);
        ngay = findViewById(R.id.dkNgaySinh);
        clickNgay = findViewById(R.id.NgaySinh);

        ten = findViewById(R.id.dkName);
        email = findViewById(R.id.dkEmail);
        pass = findViewById(R.id.dkPass);
        passAgain = findViewById(R.id.dkPassAgain);
        phone = findViewById(R.id.dkSDT);
        checkAccept = findViewById(R.id.cboxDK);

        dk = findViewById(R.id.btDK);
        exit = findViewById(R.id.btBackLogin);
        clickNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoNgay();
            }
        });
        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( checkAccept.isChecked()){

                    // xét các điều kiện
                    if( ten.getText().toString().isEmpty() ){
                        Toast.makeText(SignUp_Account.this, "Tên buộc nhập", Toast.LENGTH_SHORT).show();

                        ten.requestFocus();
                        return;
                    }
                    if( email.getText().toString().isEmpty() ) {
                        Toast.makeText(SignUp_Account.this, "Email buộc nhập", Toast.LENGTH_SHORT).show();

                        email.requestFocus();
                        return;
                    }
                    if( pass.getText().toString().isEmpty() ){
                        Toast.makeText(SignUp_Account.this, "Pass buộc nhập", Toast.LENGTH_SHORT).show();

                        pass.requestFocus();
                        return;
                    }
                    if( passAgain.getText().toString().isEmpty() || !passAgain.getText().toString().equals(pass.getText().toString()) ){
                        Toast.makeText(SignUp_Account.this, "Mật khẩu phải giống nhau", Toast.LENGTH_SHORT).show();
                        passAgain.requestFocus();
                        return;
                    }
                    if( phone.getText().toString().isEmpty() ){
                        Toast.makeText(SignUp_Account.this, "Phone buộc nhập", Toast.LENGTH_SHORT).show();

                        phone.requestFocus();
                        return;
                    }
                    RadioGroup radioGroup = findViewById(R.id.grGioiTinh);
                    int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    phon = phone.getText().toString();
                    pa = pass.getText().toString();
                    em =email.getText().toString();
                    bir = ngay.getText().toString();
                    name = ten.getText().toString();
                    if (selectedRadioButton != null) {
                        gioiTinh = selectedRadioButton.getText().toString();
                    } else {
                        gioiTinh = "null";
                    }
                    if (TextUtils.isEmpty(bir)) {
                        bir = "null";
                    }
                    SQLiteDatabase database = SQLiteDatabase.openDatabase("/data/data/com.example.myapplication/files/data.db",
                            null, SQLiteDatabase.OPEN_READWRITE);
                    try {
                        // kết nối và lưu vào bảng dữ liệu account

                        ContentValues contentValues = new ContentValues();

                        contentValues.put("user_name", name);
                        contentValues.put("email", em);
                        contentValues.put("password", pa);
                        contentValues.put("phone_number", phon);
                        contentValues.put("birth_day",bir );
                        contentValues.put("gender", gioiTinh);
                        long ketqua = database.insert("information_account", null,contentValues);
                        if( ketqua != -1 ){
                            Toast.makeText(SignUp_Account.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            ten.setText("");
                            email.setText("");
                            pass.setText("");
                            passAgain.setText("");
                            phone.setText("");
                            ngay.setText("");

                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }finally {
                        if (database != null) {
                            database.close();
                        }
                    }
                }
                else
                    Toast.makeText(SignUp_Account.this, "Bạn cần phải đồng ý các điều khoảng mới có thể đăng kí!", Toast.LENGTH_SHORT).show();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backLogin();
            }
        });

    }
    private void backLogin(){
        Intent in = new Intent(SignUp_Account.this, SignIn_SignUp.class);
        startActivity(in);
    }
    private void shoNgay(){
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, y, month, day);
        datePickerDialog.show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        int monthNow = calendar.get(Calendar.MONTH);
        int dayNow = calendar.get(Calendar.DAY_OF_MONTH);

        if( yearNow - year >= 18 ){
            if(monthNow - (month) > 0){
                ngay.setText( dayOfMonth + " / " + (month + 1 ) + " / " + year);
                return;
            }
            else if( monthNow == (month) ){
                if( dayNow - dayOfMonth >= 0 ){
                    ngay.setText( dayOfMonth + " / " + (month + 1 ) + " / " + year);
                    return;
                }
            }
        }
        else
            Toast.makeText(SignUp_Account.this, "Phải từ đủ 18 tuổi", Toast.LENGTH_SHORT).show();

    }
}