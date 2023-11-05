package com.example.menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout,menu);

        return true;
    }

   @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.mnkhachHang){
            return true;
        }else if(id == R.id.mnNhanVien){
            return true;
        }else if(id == R.id.mnsanPham){
           return true;
        }else if(id == R.id.mnTimKiem){
            if(id == R.id.mntimKhachHang){
                return true;
            }else if(id == R.id.mntimNhanvien){
                return true;
            }else{
                return true;
            }
        }
        return true;
    }

}