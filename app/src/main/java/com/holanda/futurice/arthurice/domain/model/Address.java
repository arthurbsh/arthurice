package com.holanda.futurice.arthurice.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a residential Address of an User.
 */
public class Address {

    @SerializedName("street")
    private String mStreet;

    @SerializedName("suite")
    private String mSuite;

    @SerializedName("city")
    private String mCity;

    @SerializedName("zipcode")
    private String mZipcode;

    @SerializedName("geo")
    private Coordinates mGeoInfo;

    public Address(String street, String suite, String city, String zipcode, Coordinates geoInfo) {
        this.mStreet = street;
        this.mSuite = suite;
        this.mCity = city;
        this.mZipcode = zipcode;
        this.mGeoInfo = geoInfo;
    }

    public String getStreet() {
        return mStreet;
    }

    public String getSuite() {
        return mSuite;
    }

    public String getCity() {
        return mCity;
    }

    public String getZipcode() {
        return mZipcode;
    }

    public Coordinates getGeoInfo() {
        return mGeoInfo;
    }
}
