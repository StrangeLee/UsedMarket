package com.wedontanything.usedmarket.Request;

public class UserRequest {
    private String id;

    private String userId;

    private String name;

    private String password;

    private String email;

    private String phone_number;

    private String school_name;

//    public UserRequest() {
//
//    }

    public UserRequest (String userId, String name, String password, String email, String phone_number, String school_name) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.school_name = school_name;
}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }
}
