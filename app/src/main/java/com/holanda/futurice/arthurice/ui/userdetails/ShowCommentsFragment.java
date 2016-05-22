package com.holanda.futurice.arthurice.ui.userdetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.holanda.futurice.arthurice.R;
import com.holanda.futurice.arthurice.model.Post;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowCommentsFragment extends Fragment implements View.OnClickListener {


    private View mRootView;

    private Post mPost;

    private ListView mCommentsListView;

    private RelativeLayout mBackgroundLayout;

    public ShowCommentsFragment() {
        // Required empty public constructor
    }

    public void setPost(Post post) {
        mPost = post;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mRootView = inflater.inflate(R.layout.fragment_show_comments, container, false);

        initializeComponents();

        return mRootView;
    }

    private void initializeComponents() {
        mCommentsListView = (ListView) mRootView.findViewById(R.id.listViewComments);
        CommentsListAdapter adapter = new CommentsListAdapter(mPost, getContext());
        mCommentsListView.setAdapter(adapter);

        mBackgroundLayout = (RelativeLayout) mRootView.findViewById(R.id.relativeLayoutComments);
        mBackgroundLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mBackgroundLayout.getId()) {
            getActivity().onBackPressed();
        }

    }
}
