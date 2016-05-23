package com.holanda.futurice.arthurice.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a Comment on a Post.
 */
public class Comment {

    @SerializedName("postId")
    private int mPostId;

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("body")
    private String mBody;

    public Comment(int postId, int id, String name, String email, String body) {
        this.mPostId = postId;
        this.mId = id;
        this.mName = name;
        this.mEmail = email;
        this.mBody = body;
    }

    public int getPostId() {
        return mPostId;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getBody() {
        return mBody;
    }

}