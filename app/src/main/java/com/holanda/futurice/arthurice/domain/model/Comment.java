package com.holanda.futurice.arthurice.domain.model;

import com.google.gson.annotations.SerializedName;

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

    /**
     *
     * @param id
     * @param body
     * @param email
     * @param name
     * @param postId
     */
    public Comment(int postId, int id, String name, String email, String body) {
        this.mPostId = postId;
        this.mId = id;
        this.mName = name;
        this.mEmail = email;
        this.mBody = body;
    }

    /**
     *
     * @return
     * The mPostId
     */
    public int getPostId() {
        return mPostId;
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
     * The mName
     */
    public String getName() {
        return mName;
    }

    /**
     *
     * @return
     * The mEmail
     */
    public String getEmail() {
        return mEmail;
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