package com.holanda.futurice.arthurice.domain.model;

import com.google.gson.annotations.SerializedName;
import com.holanda.futurice.arthurice.domain.repositories.Entity;

/**
 * Represents an User.
 */
public class User implements Comparable, Entity {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("username")
    private String mUsername;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("phone")
    private String mPhone;

    @SerializedName("website")
    private String mWebsite;

    @SerializedName("address")
    private Address mAddress;

    @SerializedName("company")
    private Company mCompany;

    public User(int id, String name, String username, String email, String phone, String website, Address address, Company company) {
        this.mId = id;
        this.mName = name;
        this.mUsername = username;
        this.mEmail = email;
        this.mPhone = phone;
        this.mWebsite = website;
        this.mAddress = address;
        this.mCompany = company;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public Company getCompany() {
        return mCompany;
    }

    public Address getAddress() {
        return mAddress;
    }

    @Override
    public int compareTo(Object another) {
        if (User.class.isInstance(another)){
            User anotherUser = (User) another;

            return getName().compareTo((anotherUser.getName()));
        }

        return 0;
    }
}
