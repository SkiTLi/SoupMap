package com.sktl.sm.domain.entity;

import java.util.List;

/**
 * Created by USER-PC on 20.09.2017.
 */

public class UserDomain {
    private String idUser;
    private String username;
    private String password;
    private String imageUser;
    private String  email;
    private String  additionalInformation;
    private List<PointerDomain> pointerDomainListUser;
    private List<UserDomain> friendListUserDomain;

    public List<PointerDomain> getPointerDomainListUser() {
        return pointerDomainListUser;
    }

    public void setPointerDomainListUser(List<PointerDomain> pointerDomainListUser) {
        this.pointerDomainListUser = pointerDomainListUser;
    }

    public List<UserDomain> getFriendListUserDomain() {
        return friendListUserDomain;
    }

    public void setFriendListUserDomain(List<UserDomain> friendListUserDomain) {
        this.friendListUserDomain = friendListUserDomain;
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
