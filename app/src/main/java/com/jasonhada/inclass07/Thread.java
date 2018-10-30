package com.jasonhada.inclass07;

public class Thread {
    String user_fname;
    String user_lname;
    String user_id;
    String title;
    String created_at;


    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public void setUser_lname(String user_lname) {
        this.user_lname = user_lname;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Thread(String user_fname, String user_lname, String user_id, String title, String created_at) {

        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.user_id = user_id;
        this.title = title;
        this.created_at = created_at;
    }
}
