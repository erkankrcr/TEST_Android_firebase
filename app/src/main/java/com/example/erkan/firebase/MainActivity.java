package com.example.erkan.firebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.*;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends Activity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mAuth = FirebaseAuth.getInstance();

        final EditText ID = findViewById(R.id.EmailEt);
        final EditText Pass = findViewById(R.id.SifreEt);
        Button LoginSubmit = findViewById(R.id.LoginBtn);
        Button SignSubmit = findViewById(R.id.SigninBtn);
        final AllConvert conv = new AllConvert();
        final FirebaseUser currentUser = mAuth.getCurrentUser();

        LoginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mAuth.signInWithEmailAndPassword(conv.Et2String(ID),conv.Et2String(Pass))
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(MainActivity.this,
                                            LoginCompleteActivity.class);
                                    Staff user = new Staff(conv.Et2String(ID),conv.Et2String(Pass));
                                    intent.putExtra(currentUser.getEmail(),"email");
                                   startActivity(intent);
                                }else{
                                    Toast.makeText(MainActivity.this,
                                            "Hatalı Kullanıcı Adı Ve Şifresi Girdiniz", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        SignSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SigninActivity.class));
            }
        });

    }
}
