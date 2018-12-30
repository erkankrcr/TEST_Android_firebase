package com.example.erkan.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Staff {
    private String Name;
    private String Email;
    private String Password;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    public Staff(){
        this.Name="Anonymous";
        this.Email="Anonymous";
        this.Password="Anonymous";
    }
    public Staff(String name,String email,String password){
        this.Name = name;
        this.Email = email;
        this.Password = password;
    }
    public Staff(String email,String password){
        this.Name = get(email,"Name");
        this.Email = email;
        this.Password = password;
    }
    public Staff(String email){
        this.Name = get(email,"Name");
        this.Email = email;
        this.Password = get(email,"Password");
    }
    public String get(String data,String type){
        switch (type){
            case "Name":
                return "erkan";
            case "Password":
                return "123erkan";
            default:
                return "Null";
        }
    }
    public String Email(){
        return this.Email;
    }
    public String Name(){
        return this.Name;
    }
    public String Password(){
        return this.Password;
    }
}
