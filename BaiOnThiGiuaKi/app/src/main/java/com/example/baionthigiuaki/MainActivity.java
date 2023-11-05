package com.example.baionthigiuaki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText ed_UserName = (EditText) findViewById(R.id.idUserName);
        EditText ed_Pass = (EditText) findViewById(R.id.idPass);
        Button bt_DangNhap = (Button) findViewById(R.id.buttonDN);
        TextView tv_taoTK = (TextView) findViewById(R.id.tvTaoMoi);

        tv_taoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DangKy.class);
                startActivity(intent);
            }
        });

        bt_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ EditText
                String username = ed_UserName.getText().toString().trim();
                String password = ed_Pass.getText().toString().trim();

                // Kiểm tra dữ liệu đăng nhập
                if (checkLogin(username, password)) {
                    // Nếu thông tin đăng nhập đúng, chuyển đến màn hình chính hoặc hành động khác
                    // Ví dụ: Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    // startActivity(intent);
                     Intent intent = new Intent(MainActivity.this, index.class);
                     startActivity(intent);
                } else {
                    // Nếu thông tin đăng nhập không đúng, hiển thị thông báo lỗi
                    Toast.makeText(MainActivity.this, "Đăng nhập không thành công. Vui lòng kiểm tra lại thông tin.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Hàm kiểm tra thông tin đăng nhập
    private boolean checkLogin(String username, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyFr", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", username);
        String savedPassword = sharedPreferences.getString("password", password);
        // Kiểm tra xem thông tin đăng nhập có khớp với dữ liệu lưu trữ trong SharedPreferences không
        return username.equals(savedUsername) && password.equals(savedPassword);
    }
}
