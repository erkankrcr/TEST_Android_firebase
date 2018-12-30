package com.example.erkan.firebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SigninActivity extends Activity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signinactivity);
        final EditText NAS = findViewById(R.id.SigninNameAndSurnameEt);
        final EditText Email = findViewById(R.id.SigninEmailEt);
        final EditText Pass = findViewById(R.id.SigninSifreEt);
        final Button SingCompleteBtn = findViewById(R.id.SigninCompleteBtn);
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        final AllConvert conv = new AllConvert();

        SingCompleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mAuth.createUserWithEmailAndPassword(conv.Et2String(Email),

                        conv.Et2String(Pass)).addOnCompleteListener(SigninActivity.this,

                        new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    Intent intent = new Intent(SigninActivity.this,
                                            LoginCompleteActivity.class);

                                    Staff user = new Staff(conv.Et2String(NAS),conv.Et2String(Email)
                                            ,conv.Et2String(Pass));

                                    intent.putExtra(currentUser.getEmail(),"email");

                                    myRef.child("User").child(currentUser.getEmail()).setValue(user);

                                    startActivity(intent);

                                }else{

                                    Toast.makeText(SigninActivity.this,
                                            "Böyle Bir Kullanıcı Mevcuttur",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}
