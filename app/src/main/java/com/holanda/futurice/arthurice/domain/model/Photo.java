package com.holanda.futurice.arthurice.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a photography.
 */
public class Photo {

    @SerializedName("albumId")
    private int mAlbumId;

    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("thumbnailUrl")
    private String mThumbnailUrl;

    public Photo(int albumId, int id, String title, String url, String thumbnailUrl) {
        this.mAlbumId = albumId;
        this.mId = id;
        this.mTitle = title;
        this.mUrl = url;
        this.mThumbnailUrl = thumbnailUrl;
    }

    public int getAlbumId() {
        return mAlbumId;
    }

    public int getId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

}