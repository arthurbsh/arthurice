package com.holanda.futurice.arthurice.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Holanda on 5/18/2016.
 */
public class Company {

    @SerializedName("name")
    private String mName;

    @SerializedName("catchPhrase")
    private String mCatchPhrase;

    @SerializedName("bs")
    private String mBs;


    public Company(String name, String catchPhrase, String bs) {
        this.mName = name;
        this.mCatchPhrase = catchPhrase;
        this.mBs = bs;
    }

    public String getName() {
        return mName;
    }

    public String getCatchPhrase() {
        return mCatchPhrase;
    }

    public String getBs() {
        return mBs;
    }
}
