package com.holanda.futurice.arthurice.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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