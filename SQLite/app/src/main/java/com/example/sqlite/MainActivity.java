package com.example.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
// không hiện menu coi lại trong file manifest
//   android:theme="@style/Theme.AppCompat.Light.DarkActionBar"

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_author,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if( item.getItemId() == R.id.mnAuthor) {
            Intent intent = new Intent(MainActivity.this, AuthorActivity.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.mnBook){
            Intent intent = new Intent(MainActivity.this, AuthorActivity.class);
            startActivity(intent);
            return true;

        }
        return super.onCreateOptionsMenu((Menu) item);
    }
}