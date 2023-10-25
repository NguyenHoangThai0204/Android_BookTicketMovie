package com.example.myapplication.Signup_Signin_Main_Home;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

public class TabAdapterHomeFr extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    public TabAdapterHomeFr(LayoutInflater layoutInflater, Context context) {
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

                // Tạo và mở kết nối đến cơ sở dữ liệu
                SQLiteDatabase dbsc = SQLiteDatabase.openDatabase("/data/data/com.example.myapplication/files/data.db",
                        null, SQLiteDatabase.CREATE_IF_NECESSARY);

                // Thực hiện truy vấn để lấy thông tin từ bảng "movies"
                String[] projectionsc = {
                        "image",
                        "name",
                        "description",
                        "time",
                        "author",
                        "actor",
                        "date"
                };
                String sortOrdersc = "id ASC";
                Cursor cursorsc = dbsc.query(
                        "moviesNext",
                        projectionsc,
                        null,
                        null,
                        null,
                        null,
                        sortOrdersc
                );
                // Duyệt qua các dòng và hiển thị thông tin lên các ImageView và TextView tương ứng
                while (cursorsc.moveToNext() ) {
                    View row = layoutInflater.inflate(R.layout.one_row_in_home, null);
                    ImageView image = row.findViewById(R.id.movieImage);
                    TextView title = row.findViewById(R.id.movieTitle);
                    TextView time = row.findViewById(R.id.movieTime);

                    // Đọc thông tin từ Cursor
                    String imageName = cursorsc.getString(cursorsc.getColumnIndexOrThrow("image"));
                    String movieTitle = cursorsc.getString(cursorsc.getColumnIndexOrThrow("name"));
                    String movieTime = cursorsc.getString(cursorsc.getColumnIndexOrThrow("time"));
                    String movieDescription = cursorsc.getString(cursorsc.getColumnIndexOrThrow("description"));
                    String movieDate = cursorsc.getString(cursorsc.getColumnIndexOrThrow("date"));
                    String movieAuthor = cursorsc.getString(cursorsc.getColumnIndexOrThrow("author"));
                    String movieActor = cursorsc.getString(cursorsc.getColumnIndexOrThrow("actor"));

                    // Đặt nội dung cho ImageView và TextViews
                    image.setImageResource(context.getResources().getIdentifier(imageName, "drawable",
                            context.getPackageName()));
                    title.setText(movieTitle);
                    time.setText("Ngày khởi chiếu: "+movieDate);

                    row.setId(cursorsc.getPosition());

                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // kết nối qua activity mới và thiết lập các giá trị được lấy
                            // từ database lên cho activity mới
                            Intent intent = new Intent(context, Information_Movies_Dialog_Upcoming.class);
                            intent.putExtra("name",imageName );
                            intent.putExtra("title",movieTitle );
                            intent.putExtra("time",movieTime );
                            intent.putExtra("description",movieDescription );
                            intent.putExtra("date", movieDate);
                            intent.putExtra("author", movieAuthor);
                            intent.putExtra("actor", movieActor);
                            //để báo cho hệ thống rằng khi mở một Activity mới, nó sẽ được khởi
                            // tạo như là một task
                            // mới và chạy độc lập với các task khác trên cùng một stack.
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);

                        }
                    });
                    // Thêm dòng vào ScrollView
                    linearLayoutsc.addView(row);
                }
                cursorsc.close();
                dbsc.close();
                break;
            case 1:
                view = layoutInflater.inflate(R.layout.layout_trang_chu, container, false);
                LinearLayout linearLayout = view.findViewById(R.id.scrlvHome);

                // Tạo và mở kết nối đến cơ sở dữ liệu
                SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.myapplication/files/data.db",
                        null, SQLiteDatabase.CREATE_IF_NECESSARY);

                // Thực hiện truy vấn để lấy thông tin từ bảng "movies"
                String[] projection = {
                        "name",
                        "date",
                        "author",
                        "actor",
                        "time",
                        "description",
                        "image_name"
                };
                String sortOrder = "id ASC";
                Cursor cursor =
                        db.query(
                                "movies",
                                projection,
                                null,
                                null,
                                null,
                                null,
                                sortOrder
                        );

                // Duyệt qua các dòng và hiển thị thông tin lên các ImageView và TextView tương ứng
                while (cursor.moveToNext() ) {
                    View row = layoutInflater.inflate(R.layout.one_row_in_home, null);

                    ImageView image = row.findViewById(R.id.movieImage);
                    TextView title = row.findViewById(R.id.movieTitle);
                    TextView time = row.findViewById(R.id.movieTime);

                    // Đọc thông tin từ Cursor
                    String imageName = cursor.getString(cursor.getColumnIndexOrThrow("image_name"));
                    String movieTitle = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String movieTime = cursor.getString(cursor.getColumnIndexOrThrow("time"));
                    String movieDate = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                    String movieAuthor = cursor.getString(cursor.getColumnIndexOrThrow("author"));
                    String movieActor =cursor.getString(cursor.getColumnIndexOrThrow("actor"));
                    String movieDescription = cursor.getString(cursor.getColumnIndexOrThrow("description"));

                    // Đặt nội dung cho ImageView và TextViews
                    image.setImageResource(context.getResources().getIdentifier(imageName,"drawable", context.getPackageName()));
                    title.setText(movieTitle);
                    time.setText("Thời lượng chiếu: "+movieTime);

                    // bắt sự kiện click row
                    row.setId(cursor.getPosition());
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, Information_Movies_Dialog_Playing.class);
                            intent.putExtra("name",imageName );
                            intent.putExtra("title",movieTitle );
                            intent.putExtra("time",movieTime );
                            intent.putExtra("description",movieDescription );
                            intent.putExtra("date", movieDate);
                            intent.putExtra("author", movieAuthor);
                            intent.putExtra("actor", movieActor);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    });

                    // Thêm dòng vào ScrollView
                    linearLayout.addView(row);
                }
                cursor.close();
                db.close();
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
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

}
