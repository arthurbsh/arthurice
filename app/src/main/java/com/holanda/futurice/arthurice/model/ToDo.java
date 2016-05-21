package com.holanda.futurice.arthurice.model;

import com.google.gson.annotations.SerializedName;

public class ToDo {

    @SerializedName("userId")
    private int mUserId;

    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("completed")
    private boolean mCompleted;

    /**
     *
     * @param id
     * @param title
     * @param userId
     * @param completed
     */
    public ToDo(int userId, int id, String title, boolean completed) {
        this.mUserId = userId;
        this.mId = id;
        this.mTitle = title;
        this.mCompleted = completed;
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
     * The mCompleted
     */
    public boolean isCompleted() {
        return mCompleted;
    }

}
