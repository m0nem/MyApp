package com.example.testmvp.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user" , indices = @Index(value = "Id",unique = true))
public class User {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "Id")
    int Id;

    @ColumnInfo(name = "username")
    String userName;

    @ColumnInfo(name = "pass")
    String Pass;


    public User() {


    }

    public User( String userName, String pass) {

        this.userName = userName;
        Pass = pass;
    }
    public User(int id, String userName, String pass) {
        Id = id;
        this.userName = userName;
        Pass = pass;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
