package com.holanda.futurice.arthurice.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Holanda on 5/18/2016.
 */
public class Coordinates {

    @SerializedName("lat")
    private float mLatitude;

    @SerializedName("lng")
    private float mLongitude;

    public Coordinates(float latitude, float longitude) {
        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }

    public float getLatitude() {
        return mLatitude;
    }

    public float getLongitude() {
        return mLongitude;
    }


}
