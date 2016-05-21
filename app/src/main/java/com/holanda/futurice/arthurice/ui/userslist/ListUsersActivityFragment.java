package com.holanda.futurice.arthurice.ui.userslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.holanda.futurice.arthurice.R;
import com.holanda.futurice.arthurice.model.User;
import com.holanda.futurice.arthurice.rest.RestService;
import com.holanda.futurice.arthurice.ui.userdetails.UserDetailsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListUsersActivityFragment extends Fragment implements OnItemClickListener {


    private ListView mUsersListView;

    private ListAdapter mUsersListAdapter;

    private ProgressBar mLoadingView;

    private View mRootView;

    public ListUsersActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_list_users, container, false);;



        initializeComponents();
        requestUsers();



        return mRootView;
    }

    private void requestUsers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestService rest = retrofit.create(RestService.class);

        rest.users().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                hideLoadingView();
                mUsersListAdapter = new UsersListAdapter(getActivity(), users);

                if(mUsersListView != null) {
                    Log.i("LISTUSERS", String.format("Setting adapter with %d users", users.size()));
                    mUsersListView.setAdapter(mUsersListAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i("RETRO", "falha");
            }
        });
    }

    private void hideLoadingView() {
        mLoadingView.setVisibility(View.GONE);
    }


    private void initializeComponents() {
        mUsersListView = (ListView) mRootView.findViewById(R.id.listViewUsers);
        mUsersListView.setOnItemClickListener(this);

        mLoadingView = (ProgressBar) mRootView.findViewById(R.id.progressBar);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startUserDetailsActivity((int)id);
    }

    private void startUserDetailsActivity(int userId) {
        Intent i = new Intent(getContext(), UserDetailsActivity.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }

}

