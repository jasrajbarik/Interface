package com.kodewala.Interface5;

public class User implements Cleanable {
    String userName;
    String id;
    public User(String _username, String _id) {
        super();
        this.userName=_username;
        this.id=_id;
    }
}
