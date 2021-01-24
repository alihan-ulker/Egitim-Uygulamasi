package com.example.tez.Activitys.Models;

import com.google.firebase.firestore.Exclude;
import java.io.Serializable;


public class Kullanici implements Serializable {


    @Exclude private String id;

    private String mail;
    private int cskor, boskor;

    public Kullanici() {

    }

    public Kullanici(String mail, int cskor,  int boskor) {
        this.mail = mail;
        this.cskor = cskor;
        this.boskor = boskor;

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getCskor() {
        return cskor;
    }

    public void setCskor(int cskor) {
        this.cskor = cskor;
    }

    public int getBoskor() {
        return boskor;
    }

    public void setBoskor(int boskor) {
        this.boskor = boskor;
    }


}
