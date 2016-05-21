package com.holanda.futurice.arthurice.model;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("userId")
    private int mUserId;

    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("body")
    private String mBody;

    /**
     *
     * @param id
     * @param body
     * @param title
     * @param userId
     */
    public Post(int userId, int id, String title, String body) {
        this.mUserId = userId;
        this.mId = id;
        this.mTitle = title;
        this.mBody = body;
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
     * The mId
     */
    public int getId() {
        return mId;
    }

    /**
     *
     * @return
     * The mTitle
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     *
     * @return
     * The mBody
     */
    public String getBody() {
        return mBody;
    }

}