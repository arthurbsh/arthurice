package com.holanda.futurice.arthurice.ui.userdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.holanda.futurice.arthurice.R;
import com.holanda.futurice.arthurice.domain.model.Comment;
import com.holanda.futurice.arthurice.domain.model.Post;

public class CommentsListAdapter extends BaseAdapter {

    private Post mPost;

    private LayoutInflater mInflater;

    public CommentsListAdapter(Post post, Context context) {
        mPost = post;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mPost.commentsCount();
    }

    @Override
    public Object getItem(int position) {
        return mPost.getComments().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = mInflater.inflate(R.layout.item_adapter_comment, null);
            holder = new ViewHolder();

            holder.commenter = (TextView) view.findViewById(R.id.textViewCommenter);
            holder.body = (TextView) view.findViewById(R.id.textViewCommentBody);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final Comment comment = mPost.getComments().get(position);
        final String commenter = comment.getEmail();
        final String body = comment.getBody();

        holder.commenter.setText(commenter);
        holder.body.setText(body);

        return view;
    }

    protected static class ViewHolder {
        TextView commenter;

        TextView body;
    }
}
