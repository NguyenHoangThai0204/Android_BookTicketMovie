package com.example.myapplication.Signup_Signin_Main_Home;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.R;
import com.example.myapplication.Upcoming_movies.Information_Movies_Dialog_Upcoming;
import com.example.myapplication.Movies_is_playing.*;

public class Adapter_History_Ticketed extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    public Adapter_History_Ticketed(LayoutInflater layoutInflater, Context context) {
        this.layoutInflater = layoutInflater;
        this.context = context;
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view;
        switch (position) {
            case 0:
                view = layoutInflater.inflate(R.layout.layout_trang_chu, container, false);
                LinearLayout linearLayoutsc = view.findViewById(R.id.scrlvHome);


                // Get user's id from SharedPreferences
                SharedPreferences sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String currentUserId = sharedPref.getString("currentUserId", "");

                SQLiteDatabase database = SQLiteDatabase.openDatabase("/data/data/com.example.myapplication/files/data.db",
                        null, SQLiteDatabase.OPEN_READWRITE);
                String[] projectionsc = {
                        "name",
                        "hour",
                        "time",
                        "date",
                        "seat",
                        "order_date",
                        "food",
                        "money",
                        "id_account",
                        "rap"
                };
                String selection = "id_account=?";
                String[] selectionArg = {currentUserId};
                Cursor cursor = database.query("bills", projectionsc, selection, selectionArg, null, null, null);
//        // Duyệt qua các dòng và hiển thị thông tin lên các ImageView và TextView tương ứng
                while (cursor.moveToNext() ) {
                    View row = layoutInflater.inflate(R.layout.one_row_in_home, null);
                    ImageView image = row.findViewById(R.id.movieImage);
                    TextView title = row.findViewById(R.id.movieTitle);
                    TextView time = row.findViewById(R.id.movieTime);

                    // Đọc thông tin từ Cursor
                    String imageName = "ticket";

                    String rapIn = cursor.getString(cursor.getColumnIndexOrThrow("rap"));
                    String nameIn = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String hourIn = cursor.getString(cursor.getColumnIndexOrThrow("hour"));
                    String dateIn = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                    String seatIn = cursor.getString(cursor.getColumnIndexOrThrow("seat"));
                    String moneyIn = cursor.getString(cursor.getColumnIndexOrThrow("money"));
                    String orderdateIn = cursor.getString(cursor.getColumnIndexOrThrow("order_date"));
                    // Đặt nội dung cho ImageView và TextViews
                    image.setImageResource(context.getResources().getIdentifier(imageName, "drawable",
                            context.getPackageName()));
                    title.setText(nameIn);
                    time.setText("Ngày đặt vé: "+ orderdateIn);

                    row.setId(cursor.getPosition());

                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, History_Ticketed.class);
                            intent.putExtra("name", nameIn);
                            intent.putExtra("hour", hourIn);
                            intent.putExtra("date", dateIn);
                            intent.putExtra("seat", seatIn);
                            intent.putExtra("money", moneyIn);
                            intent.putExtra("rap", rapIn);
                            intent.putExtra("order_date", orderdateIn);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    });


                    // Thêm dòng vào ScrollView
                    linearLayoutsc.addView(row);
                }
                cursor.close();
                database.close();
                break;

            default:
                view = layoutInflater.inflate(R.layout.layout_trang_chu, container, false);
                break;
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

}


//package com.example.myapplication.Signup_Signin_Main_Home;
//
//import static android.content.Context.MODE_PRIVATE;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.viewpager.widget.PagerAdapter;
//
//import com.example.myapplication.Movies_is_playing.Information_Movies_Dialog_Playing;
//import com.example.myapplication.R;
//import com.example.myapplication.Upcoming_movies.Information_Movies_Dialog_Upcoming;
//
//public class Adapter_History_Ticketed extends PagerAdapter {
//    private LayoutInflater layoutInflater;
//    private Context context;
//    private TextView rap, name, hour, date, seat, money;
//    public Adapter_History_Ticketed(LayoutInflater layoutInflater, Context context) {
//        this.layoutInflater = layoutInflater;
//        this.context = context;
//    }
//    @SuppressLint("UseCompatLoadingForDrawables")
//    @NonNull
//    public Object instantiateItem(@NonNull ViewGroup container) {
//        View view;
//        view = layoutInflater.inflate(R.layout.layout_trang_chu, container, false);
//        LinearLayout linearLayoutsc = view.findViewById(R.id.scrlvHome);
//
//        SharedPreferences sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        String currentUserId = sharedPref.getString("currentUserId", ""); // gi&aacute; tr&#x1ECB; m&#x1EB7;c &#x111;&#x1ECB;nh l&agrave; ""
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
//        // Duyệt qua các dòng và hiển thị thông tin lên các ImageView và TextView tương ứng
//        while (cursor.moveToNext() ) {
//            View row = layoutInflater.inflate(R.layout.one_row_in_home, null);
//            ImageView image = row.findViewById(R.id.movieImage);
//            TextView title = row.findViewById(R.id.movieTitle);
//            TextView time = row.findViewById(R.id.movieTime);
//
//            // Đọc thông tin từ Cursor
//            String imageName = "phim1_sc.jpg";
//
//            String rapIn = cursor.getString(cursor.getColumnIndexOrThrow("rap"));
//            String nameIn = cursor.getString(cursor.getColumnIndexOrThrow("name"));
//            String hourIn = cursor.getString(cursor.getColumnIndexOrThrow("hour"));
//            String dateIn = cursor.getString(cursor.getColumnIndexOrThrow("date"));
//            String seatIn = cursor.getString(cursor.getColumnIndexOrThrow("seat"));
//            String moneyIn = cursor.getString(cursor.getColumnIndexOrThrow("money"));
//
//            // Đặt nội dung cho ImageView và TextViews
//            image.setImageResource(context.getResources().getIdentifier(imageName, "drawable",
//                    context.getPackageName()));
//            title.setText(nameIn);
//            time.setText("Ngày đặt vé: "+dateIn);
//
//            row.setId(cursor.getPosition());
//
//            row.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // kết nối qua activity mới và thiết lập các giá trị được lấy
//                    // từ database lên cho activity mới
//                    Intent intent = new Intent(context, History_Ticketed.class);
//                    intent.putExtra("name",nameIn );
//                    intent.putExtra("rap",rapIn );
//                    intent.putExtra("hour",hourIn );
//                    intent.putExtra("date",dateIn );
//                    intent.putExtra("seat", seatIn);
//                    intent.putExtra("money", moneyIn);
//                    //để báo cho hệ thống rằng khi mở một Activity mới, nó sẽ được khởi
//                    // tạo như là một task
//                    // mới và chạy độc lập với các task khác trên cùng một stack.
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//
//                }
//            });
//
//            // Thêm dòng vào ScrollView
//            linearLayoutsc.addView(row);
//
//            cursor.close();
//            database.close();
//
//        } if(cursor.getCount()<=0) {
//            Toast.makeText(context, "Chưa bao giờ đặt vé", Toast.LENGTH_SHORT).show();
//        }
//        container.addView(view);
//        return view;
//    }
//
//    private SharedPreferences getSharedPreferences(String myPrefs, int modePrivate) {
//        return null;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//    }
//
//    @Override
//    public int getCount() {
//        return 2;
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//}