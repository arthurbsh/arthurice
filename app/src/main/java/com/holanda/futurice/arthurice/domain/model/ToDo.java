package com.holanda.futurice.arthurice.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a task that has been or not done by an User.
 */
public class ToDo {

    @SerializedName("userId")
    private int mUserId;

    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("completed")
    private boolean mCompleted;

    public ToDo(int userId, int id, String title, boolean completed) {
        this.mUserId = userId;
        this.mId = id;
        this.mTitle = title;
        this.mCompleted = completed;
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

    public boolean isCompleted() {
        return mCompleted;
    }

}
