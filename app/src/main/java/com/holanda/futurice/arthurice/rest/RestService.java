package com.holanda.futurice.arthurice.rest;

import com.holanda.futurice.arthurice.domain.model.Album;
import com.holanda.futurice.arthurice.domain.model.Comment;
import com.holanda.futurice.arthurice.domain.model.Photo;
import com.holanda.futurice.arthurice.domain.model.Post;
import com.holanda.futurice.arthurice.domain.model.ToDo;
import com.holanda.futurice.arthurice.domain.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Abstraction of the RESTful webservice.
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

    @GET("user/{id}/todos")
    Call<List<ToDo>> toDosFromUser(@Path("id") int userId);

    @GET("posts/{id}/comments")
    Call<List<Comment>> commentsFromPost(@Path("id") int postId);

    @GET("albums/{id}/photos")
    Call<List<Photo>> photosFromAlbum(@Path("id") int albumId);

}
