package com.holanda.futurice.arthurice.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents a Post made by the User.
 */
public class Post implements Comparable{

    @SerializedName("userId")
    private int mUserId;

    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("body")
    private String mBody;

    private List<Comment> mComments;

    public Post(int userId, int id, String title, String body) {
        this.mUserId = userId;
        this.mId = id;
        this.mTitle = title;
        this.mBody = body;
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

    public String getBody() {
        return mBody;
    }

    public List<Comment> getComments() {
        return mComments;
    }

    public void setComments(List<Comment> comments) {
        this.mComments = comments;
    }

    public int commentsCount() {
        if (mComments == null) {
            return 0;
        }

        return mComments.size();
    }

    @Override
    public int compareTo(Object another) {
        if (Post.class.isInstance(another)) {
            Post anotherPost = (Post) another;
            return getId() - anotherPost.getId();
        }

        return 0;
    }
}