package com.example.anhki.tradingbook.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhki.tradingbook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecoveryPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edEmailRecovery;
    Button btnRecovery;
    TextView tvRegisterRecover;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_forgotpassword);

        firebaseAuth = FirebaseAuth.getInstance();

        edEmailRecovery = (EditText) findViewById(R.id.edRecoveryEmail);
        tvRegisterRecover = (TextView) findViewById(R.id.tvRegisterRecover);
        btnRecovery = (Button) findViewById(R.id.btnRecovery);

        btnRecovery.setOnClickListener(this);
        tvRegisterRecover.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnRecovery:
                String email = edEmailRecovery.getText().toString();
                boolean checkmail = CheckEmail(email);

                if (checkmail){
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RecoveryPasswordActivity.this, getString(R.string.thanhcong), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(RecoveryPasswordActivity.this, getString(R.string.emailkhonghople), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tvRegisterRecover:
                break;
        }
    }

    private boolean CheckEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
