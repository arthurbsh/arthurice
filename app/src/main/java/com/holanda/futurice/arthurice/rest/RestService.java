package com.holanda.futurice.arthurice.rest;

import com.holanda.futurice.arthurice.model.Album;
import com.holanda.futurice.arthurice.model.Comment;
import com.holanda.futurice.arthurice.model.Photo;
import com.holanda.futurice.arthurice.model.Post;
import com.holanda.futurice.arthurice.model.ToDo;
import com.holanda.futurice.arthurice.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Holanda on 5/18/2016.
 */
public interface RestService {

    String baseUrl = "http://jsonplaceholder.typicode.com";

    @GET("users")
    Call<List<User>> users();

    @GET("users/{id}")
    Call<User> user(@Path("id") int userId);

    @GET("users/{id}/posts")
    Call<List<Post>> postsFromUser(@Path("id") int userId);

    @GET("users/{id}/albums")
    Call<List<Album>> albumsFromUser(@Path("id") int userId);

    @GET("todos?userId={id}")
    Call<List<ToDo>> toDosFromUser(@Path("id") int userId);

    @GET("posts/{id}/comments")
    Call<List<Comment>> commentsFromPost(@Path("id") int postId);

    @GET("albums/{id}/photos")
    Call<List<Photo>> photosFromAlbum(@Path("id") int albumId);

}
