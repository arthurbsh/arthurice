package com.holanda.futurice.arthurice.model;

import com.google.gson.annotations.SerializedName;

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

    /**
     *
     * @return
     * The mUserId
     */
    public int getUserId() {
        return mUserId;
    }

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return mId;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return mTitle;
    }

}