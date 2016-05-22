package com.holanda.futurice.arthurice.domain.model;

import com.google.gson.annotations.SerializedName;

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

    /**
     *
     * @param id
     * @param title
     * @param albumId
     * @param thumbnailUrl
     * @param url
     */
    public Photo(int albumId, int id, String title, String url, String thumbnailUrl) {
        this.mAlbumId = albumId;
        this.mId = id;
        this.mTitle = title;
        this.mUrl = url;
        this.mThumbnailUrl = thumbnailUrl;
    }

    /**
     *
     * @return
     * The mAlbumId
     */
    public int getAlbumId() {
        return mAlbumId;
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
    public String getmTitle() {
        return mTitle;
    }


    /**
     *
     * @return
     * The mUrl
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     *
     * @return
     * The mThumbnailUrl
     */
    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

}