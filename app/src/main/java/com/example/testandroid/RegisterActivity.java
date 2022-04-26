package com.example.testandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Button btn_Register;
    private EditText edt_Regis_Email, edt_Regis_RePass, edt_Regis_Pass, edt_Regis_Phone;
    private FirebaseAuth mAuth;
    String email, password, phone,rePassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edt_Regis_Email = findViewById(R.id.edt_Regis_Email);
        edt_Regis_Pass = findViewById(R.id.edt_Regis_Pass);
        edt_Regis_RePass = findViewById(R.id.edt_Regis_RePass);
        edt_Regis_Phone = findViewById(R.id.edt_Regis_Phone);
        btn_Register = findViewById(R.id.btn_Register);

        mAuth = FirebaseAuth.getInstance();

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }
    private void register(){
        email = edt_Regis_Email.getText().toString();
        password = edt_Regis_Pass.getText().toString();
        phone = edt_Regis_Phone.getText().toString();
        rePassword = edt_Regis_RePass.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(RegisterActivity.this, "enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(RegisterActivity.this, "enter your Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(RegisterActivity.this, "Enter your phone", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(rePassword)){
            Toast.makeText(RegisterActivity.this, "confirm password không giống nhau", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

               if(task.isSuccessful()){
                   Toast.makeText(RegisterActivity.this, "tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                   startActivity(intent);
               }else {
                   Toast.makeText(RegisterActivity.this, "đăng kí không thành công", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}