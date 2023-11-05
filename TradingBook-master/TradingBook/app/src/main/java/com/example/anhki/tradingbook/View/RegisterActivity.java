package com.example.anhki.tradingbook.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anhki.tradingbook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister;
    FirebaseAuth firebaseAuth;
    EditText edRegisterEmail, edRegisterPass, edRegisterRePass;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        edRegisterEmail = (EditText) findViewById(R.id.edRegisterEmail);
        edRegisterPass = (EditText) findViewById(R.id.edRegisterPassword);
        edRegisterRePass = (EditText) findViewById(R.id.edRePassword);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        progressDialog.setMessage(getString(R.string.dangxuly));
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        String email = edRegisterEmail.getText().toString();
        String password = edRegisterPass.getText().toString();
        String repassword = edRegisterRePass.getText().toString();
        String error = getString(R.string.thongbaoloi);

        if (email.trim().length() == 0){
            error += " email";
            Toast.makeText(this,error, Toast.LENGTH_SHORT).show();

        }else if (password.trim().length() == 0){
            error += " password";
            Toast.makeText(this,error, Toast.LENGTH_SHORT).show();
        }else if (!repassword.equals(password)){
            Toast.makeText(this,getString(R.string.matkhaukhongkhop), Toast.LENGTH_SHORT).show();
        }else {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this,getString(R.string.taotaikhoanthanhcong), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
