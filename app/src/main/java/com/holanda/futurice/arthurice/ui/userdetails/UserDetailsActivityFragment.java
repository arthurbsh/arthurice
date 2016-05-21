package com.holanda.futurice.arthurice.ui.userdetails;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.holanda.futurice.arthurice.R;
import com.holanda.futurice.arthurice.model.Album;
import com.holanda.futurice.arthurice.model.Comment;
import com.holanda.futurice.arthurice.model.Coordinates;
import com.holanda.futurice.arthurice.model.Photo;
import com.holanda.futurice.arthurice.model.Post;
import com.holanda.futurice.arthurice.model.User;
import com.holanda.futurice.arthurice.rest.RestService;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class UserDetailsActivityFragment extends Fragment implements View.OnClickListener {

    private View mRootView;

    private User mUser;

    private List<Post> mPosts;

    private int mUserId = -1;

    private ImageButton mSmsButton;

    private ImageButton mCallButton;

    private ImageButton mEmailButton;

    private ImageButton mMapButton;

    private TextView mNameTextView;

    private TextView mWebsiteTextView;

    private TextView mPhoneTextView;

    private TextView mStreetTextView;

    private TextView mZipcodeTextView;

    private TextView mCityTextView;

    private LinearLayout mAlbumsLayout;

    private LinearLayout mAlbumsHorizontalList;

    private LinearLayout mCommunicationLayout;

    private ProgressBar mProgressBar;

    private ScrollView mScrollView;

    private Retrofit mRetrofit;

    private RestService mRestService;

    private ExpandableListView mPostsExpandableListView;

    private PostsExpandableListAdapter mExpandablePostAdapter;



    public UserDetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_user_details, container, false);

        mUserId = getActivity().getIntent().getExtras().getInt("userId");

        mRetrofit = new Retrofit.Builder()
                .baseUrl(RestService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRestService = mRetrofit.create(RestService.class);

        initializeComponents();
        requestUserInfo();

        return mRootView;
    }

    private void requestUserInfo() {

        mRestService.user(mUserId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                onUserLoaded(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //notify user of failure
            }
        });
    }

    private void onUserLoaded(User user) {
        mUser = user;

        //swapping visibility of ProgressBars and the layouts
        fillUserInfoViews(mUser);
        mScrollView.setVisibility(View.VISIBLE);
        mCommunicationLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);

        //request more stuff
        requestUserAlbums();
        requestUserToDos();
        requestUserPosts();
    }

    private void fillUserInfoViews(User user) {
        //basic info
        mNameTextView.setText(user.getName());
        mWebsiteTextView.setText(underline(user.getWebsite()));
        mPhoneTextView.setText(formatPhone(user.getPhone()));

        //address
        mStreetTextView.setText(formatStreet(user.getAddress().getStreet(), user.getAddress().getSuite()));
        mZipcodeTextView.setText(mUser.getAddress().getZipcode());
        mCityTextView.setText(mUser.getAddress().getCity());
    }

    private SpannableString underline(String website) {
        SpannableString content = new SpannableString(website);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        return content;
    }

    private String formatStreet(String street, String suite) {
        return street + ", " + suite;
    }

    private String formatPhone(String phone) {
        return phone;
    }

    private void requestUserToDos() {
        //unused for now
    }

    private void requestUserAlbums() {
        mRestService.albumsFromUser(mUser.getId()).enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                onAlbumsReceived(response.body());
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void onAlbumsReceived(List<Album> albums) {
        Log.i("ALBUMS", String.format("%d albums were received from user " + mUser.getName(), albums.size()));

        for (int i = 0; i < albums.size(); i++) {
            View miniAlbum = createMiniFromAlbum(albums.get(i));

            mAlbumsHorizontalList.addView(miniAlbum);
        }

    }

    private View createMiniFromAlbum(Album album) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View miniAlbum = inflater.inflate(R.layout.layout_album_mini, null);

        TextView albumTitle = (TextView) miniAlbum.findViewById(R.id.textViewAlbumTitle);
        albumTitle.setText(album.getTitle());

        requestPhotosFromAlbum(album.getId(), miniAlbum);

        return miniAlbum;
    }

    private void requestPhotosFromAlbum(int albumId, final View miniAlbum) {
        mRestService.photosFromAlbum(albumId).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                fillPhotosInto(response.body(), miniAlbum);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });

    }

    private void fillPhotosInto(List<Photo> photos, View miniAlbum) {
        RelativeLayout imageGrid = (RelativeLayout) miniAlbum.findViewById(R.id.mini_album_grid);

        int imagesToLoad = Math.min(photos.size(), imageGrid.getChildCount());

        for (int i = 0; i < imagesToLoad; i++) {
            ImageView imageView = (ImageView) imageGrid.getChildAt(i);
            String thumbnailUrl = photos.get(i).getThumbnailUrl();

            Glide.with(this).load(thumbnailUrl).override(50, 50).into(imageView);
        }
    }

    private void requestUserPosts() {
        mRestService.postsFromUser(mUser.getId()).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                mPosts = response.body();
                onPostsReceived(mPosts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    private void onPostsReceived(List<Post> posts) {
        Log.i("POSTS", String.format("%d posts were received from user " + mUser.getName(), posts.size()));

        mExpandablePostAdapter = new PostsExpandableListAdapter(posts, getContext());

        mPostsExpandableListView.setAdapter(mExpandablePostAdapter);

        for (int i = 0; i < posts.size(); i++) {
            requestCommentsFromPost(posts.get(i));
        }
    }

    private void requestCommentsFromPost(final Post post) {

        mRestService.commentsFromPost(post.getId()).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                post.setComments(response.body());
                if (finishedGettingComments()) {
                    mPostsExpandableListView.setVisibility(View.VISIBLE);
                    //mPostsExpandableListView.setMinimumHeight(3000);

                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

    private boolean finishedGettingComments() {

        for (int i = 0; i < mPosts.size(); i++) {
            if (mPosts.get(i).getComments() == null) {
                return false;
            }
        }

        return true;
    }

    private void initializeComponents() {

        //ProgressBars
        mProgressBar = (ProgressBar) mRootView.findViewById(R.id.progressBar);

        //buttons
        mSmsButton = (ImageButton) mRootView.findViewById(R.id.buttonSms);
        mCallButton = (ImageButton) mRootView.findViewById(R.id.buttonCall);
        mEmailButton = (ImageButton) mRootView.findViewById(R.id.buttonEmail);
        mMapButton = (ImageButton) mRootView.findViewById(R.id.buttonMap);


        //basic info TextViews
        mNameTextView = (TextView) mRootView.findViewById(R.id.textViewUserName);
        mWebsiteTextView = (TextView) mRootView.findViewById(R.id.textViewWebsite);
        mPhoneTextView = (TextView) mRootView.findViewById(R.id.textViewPhone);

        //address TextView
        mStreetTextView = (TextView) mRootView.findViewById(R.id.textViewStreet);
        mZipcodeTextView = (TextView) mRootView.findViewById(R.id.textViewZipcode);
        mCityTextView = (TextView) mRootView.findViewById(R.id.textViewCity);

        //outer layout
        mScrollView = (ScrollView) mRootView.findViewById(R.id.scrollView);
        mScrollView.setVisibility(View.GONE);

        //horizontal list of albums
        mAlbumsHorizontalList = (LinearLayout) mRootView.findViewById(R.id.albumsList);

        //posts layout
        mPostsExpandableListView = (ExpandableListView) mRootView.findViewById(R.id.expandableListViewPosts);
        mPostsExpandableListView.setVisibility(View.GONE);

        mCommunicationLayout = (LinearLayout) mRootView.findViewById(R.id.layout_communication);
        mCommunicationLayout.setVisibility(View.GONE);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        mSmsButton.setOnClickListener(this);
        mCallButton.setOnClickListener(this);
        mEmailButton.setOnClickListener(this);
        mMapButton.setOnClickListener(this);
        mWebsiteTextView.setOnClickListener(this);
    }


    @Override
    public void onClick(View clickedView) {
        if (clickedView.getId() == mSmsButton.getId()) {
            onSmsButtonClicked();
        } else if (clickedView.getId() == mCallButton.getId()) {
            onCallButtonClicked();
        } else if (clickedView.getId() == mEmailButton.getId()) {
            onEmailButtonClicked();
        } else if (clickedView.getId() == mMapButton.getId()) {
            onMapButtonClicked();
        } else if (clickedView.getId() == mWebsiteTextView.getId()) {
            onWebsiteClicked();
        } else {
            //click event not handled
        }
    }

    private void onWebsiteClicked() {
        startBrowserActivity(mUser.getWebsite());
    }

    private void startBrowserActivity(String url) {

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private void onMapButtonClicked() {
        Coordinates coord = mUser.getAddress().getGeoInfo();

        startMapApplication(coord.getLatitude(), coord.getLongitude());
    }

    private void startMapApplication(float latitude, float longitude) {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        getContext().startActivity(intent);
    }

    private void onEmailButtonClicked() {
        startEmailSendingActivity(mUser.getEmail());
    }

    private void startEmailSendingActivity(String email) {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/rfc822");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});

        getContext().startActivity(Intent.createChooser(emailIntent, "Send mail"));
    }

    private void onCallButtonClicked() {
        startCallMakingActivity(mUser.getPhone());
    }

    private void startCallMakingActivity(String phone) {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL);

            intent.setData(Uri.parse("tel:" + phone));
            getContext().startActivity(intent);
        }

    }

    private void onSmsButtonClicked() {
        startSmsSendingActivity(mUser.getPhone());
    }

    private void startSmsSendingActivity(String phone) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone));
        startActivity(intent);
    }
}
