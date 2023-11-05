package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class AuthorActivity extends AppCompatActivity {
    EditText editText_id, editText_name, editText_address,editText_email;
    Button bt_save, bt_select,bt_delete,bt_update;
    GridView gv_display;
    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        editText_id = (EditText) findViewById(R.id.ed_textID);
        editText_name = (EditText) findViewById(R.id.ed_textName);
        editText_address = (EditText) findViewById(R.id.ed_textAddress);
        editText_email = (EditText) findViewById(R.id.ed_textMail);
        bt_save = (Button) findViewById(R.id.bt_savve);
        bt_select = (Button) findViewById(R.id.bt_select);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_update = (Button) findViewById(R.id.bt_update);
        gv_display = (GridView) findViewById(R.id.gridView);
        database = new Database(this);
        //
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Author author = new Author();
                author.setId_author(Integer.parseInt(editText_id.getText().toString()));
                author.setName(editText_name.getText().toString());
                author.setAddress(editText_address.getText().toString());
                author.setEmail(editText_email.getText().toString());
                if(database.insertAuthor(author)>0)
                    Toast.makeText(getApplicationContext(),"luu thanh cong",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"luu khong thanh cong",Toast.LENGTH_LONG).show();
            }
        });
        //
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Author> listAu;
                ArrayList<String> listString = new ArrayList<>();
                listAu = database.getAllAuthor();
                for (Author author: listAu) {
                    listString.add(author.getId_author()+ " ");
                    listString.add(author.getName());
                    listString.add(author.getAddress());
                    listString.add(author.getEmail());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AuthorActivity.this, android.R.layout.simple_list_item_1,listString);
                gv_display.setAdapter(adapter);
            }
        });

        //
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //
       bt_delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
    }


}