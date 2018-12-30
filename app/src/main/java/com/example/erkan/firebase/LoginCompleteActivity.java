package com.example.erkan.firebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;


public class LoginCompleteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logincomplete);
        TextView NAS = findViewById(R.id.NASTv);
        TextView Email = findViewById(R.id.EmaiTv);
        TextView Pass = findViewById(R.id.PassTv);
        Intent intent = getIntent();
        Staff user = new Staff(intent.getStringExtra("email"));
        NAS.setText(user.Name());
        Email.setText(user.Email());
        Pass.setText(user.Password());
    }
}
