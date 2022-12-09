package com.hqsoft.esales.LoginJDBC.Model;

import android.text.TextUtils;
import android.util.Patterns;

public class User {
    private int ID;
    private String Name;
    private String email ;
    private String password;

    public User(int ID, String name, String email, String password) {
        this.ID = ID;
        Name = name;
        this.email = email;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
