package com.sktl.sm.data.entity;

import java.util.List;

/**
 * Created by USER-PC on 20.09.2017.
 */

public class User {
    private String idUser;
    private String username;
    private String password;
    private String imageUser;
    private String  email;
    private String  additionalInformation;
    private List<Pointer> pointerListUser;
    private List<User> friendListUser;

    public List<Pointer> getPointerListUser() {
        return pointerListUser;
    }

    public void setPointerListUser(List<Pointer> pointerListUser) {
        this.pointerListUser = pointerListUser;
    }

    public List<User> getFriendListUser() {
        return friendListUser;
    }

    public void setFriendListUser(List<User> friendListUser) {
        this.friendListUser = friendListUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }


}
