package com.holanda.futurice.arthurice.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents an album of photos.
 */
public class Album {

    @SerializedName("UserId")
    private int mUserId;

    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    public Album(int userId, int id, String title) {
        this.mUserId = userId;
        this.mId = id;
        this.mTitle = title;
    }

    public int getUserId() {
        return mUserId;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

}