package com.wedontanything.usedmarket.User;

import java.util.List;

public class UserInfo {
    private String id;

    private String name;

    private String password;

    private String email;

    private String phoneNumber;

    private String schoolName;

    private String permission;

    private List<ProfileImage> ProfileImages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<ProfileImage> getProfileImages() {
        return ProfileImages;
    }

    public void setProfileImages(List<ProfileImage> profileImages) {
        ProfileImages = profileImages;
    }
}
