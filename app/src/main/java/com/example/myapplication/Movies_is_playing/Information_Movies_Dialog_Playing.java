package com.example.myapplication.Movies_is_playing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.Signup_Signin_Main_Home.Main;

public class Information_Movies_Dialog_Playing extends Activity {
    private ImageView imageView;
    private Button back, bookTick;

    private TextView title, time, description, date, author, actor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_imfor_movies);

        // nhận các giá trị được truyền từ intent
        Intent intent = getIntent();
        String imageName = intent.getStringExtra("name");
        String movieTitle = intent.getStringExtra("title");
        String movieTime = intent.getStringExtra("time");
        String movieDescription = intent.getStringExtra("description");
        String movieDate = intent.getStringExtra("date");
        String movieAuthor = intent.getStringExtra("author");
        String movieActor = intent.getStringExtra("actor");

        // ánh xạ các phần tử
        imageView = findViewById(R.id.movieImageDL);
        title = findViewById(R.id.movieTitleDL);
        time = findViewById(R.id.movieTimeDl);
        description = findViewById(R.id.movieDiscriptionDL);
        date = findViewById(R.id.movieDayDL);
        author = findViewById(R.id.movieAuthorDL);
        actor = findViewById(R.id.movieActorDl);

        back = findViewById(R.id.buttonBackHomeInDialog);
        bookTick = findViewById(R.id.buttonBookingTickInDialog);

        // set các thông tin
        imageView.setImageResource(getResources().getIdentifier(imageName, "drawable", getPackageName()));
        title.setText(movieTitle);
        date.setText("Ngày khỏi chiếu: "+movieDate);
        description.setText(movieDescription);
        time.setText("Thời lượng: " +movieTime);
        author.setText("\t Đạo diễn: "+movieAuthor);
        actor.setText( "\t Diễn viên: "+movieActor);

        // trở về
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Information_Movies_Dialog_Playing.this, Main.class );

                startActivity(in);
            }
        });

        // sự kiện khi nut đặt vé được click;
        bookTick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Information_Movies_Dialog_Playing.this, Select_Day.class );
                in.putExtra("name", movieTitle);
                in.putExtra("time", movieTime);
                startActivity(in);
            }
        });

    }
}
