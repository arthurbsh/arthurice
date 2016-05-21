package com.holanda.futurice.arthurice.ui.userdetails;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.holanda.futurice.arthurice.R;
import com.holanda.futurice.arthurice.model.Comment;
import com.holanda.futurice.arthurice.model.Post;

import java.util.List;

/**
 * Created by Holanda on 5/20/2016.
 */
public class PostsExpandableListAdapter implements ExpandableListAdapter {

    private List<Post> mPosts;

    private LayoutInflater mInflater;

    public PostsExpandableListAdapter(List<Post> posts, Context context) {
        mPosts = posts;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return mPosts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mPosts.get(groupPosition).commentsCount();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mPosts.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mPosts.get(groupPosition).getComments().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mPosts.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mPosts.get(groupPosition).getComments().get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolderPost holder;

        if (view == null) {
            view = mInflater.inflate(R.layout.item_adapter_post, null);
            holder = new ViewHolderPost();

            holder.title = (TextView) view.findViewById(R.id.textViewPostTitle);
            holder.body = (TextView) view.findViewById(R.id.textViewPostBody);
            holder.commentCount = (TextView) view.findViewById(R.id.textViewCommentsCount);

            view.setTag(holder);
        } else {
            holder = (ViewHolderPost) view.getTag();
        }

        final Post post = mPosts.get(groupPosition);
        final String title = post.getTitle();
        final String body = post.getBody();
        final int commentCount = post.commentsCount();

        String commentCountText = fancyCommentCount(commentCount);

        holder.title.setText(title);
        holder.body.setText(body);
        holder.commentCount.setText(commentCountText);

        return view;
    }

    private String fancyCommentCount(int commentCount) {
        String comment = String.format("%d comment", commentCount);

        if (commentCount > 1) {
            comment = comment + "s";
        }

        return comment;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolderComment holder;

        if (view == null) {
            view = mInflater.inflate(R.layout.item_adapter_comment, null);
            holder = new ViewHolderComment();

            holder.commenter= (TextView) view.findViewById(R.id.textViewCommenter);
            holder.body = (TextView) view.findViewById(R.id.textViewCommentBody);

            view.setTag(holder);
        } else {
            holder = (ViewHolderComment) view.getTag();
        }

        final Post post = mPosts.get(groupPosition);
        final Comment comment = post.getComments().get(childPosition);
        final String commenter = comment.getEmail();
        final String body = comment.getBody();

        holder.commenter.setText(commenter);
        holder.body.setText(body);

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return mPosts.isEmpty();
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    protected static class ViewHolderPost {
        TextView title;

        TextView body;

        TextView commentCount;
    }

    protected static class ViewHolderComment {
        TextView commenter;

        TextView body;
    }
}
