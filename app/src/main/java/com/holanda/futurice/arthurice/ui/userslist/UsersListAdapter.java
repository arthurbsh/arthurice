package com.holanda.futurice.arthurice.ui.userslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.holanda.futurice.arthurice.R;
import com.holanda.futurice.arthurice.domain.model.User;

import java.util.Collections;
import java.util.List;

/**
 * Created by Holanda on 5/18/2016.
 */
public class UsersListAdapter extends BaseAdapter{

    private List<User> mListUsers;


    private LayoutInflater mInflater;

    public UsersListAdapter(final Context context, final List<User> users) {
        mInflater = LayoutInflater.from(context);
        setSource(users);
    }

    public void setSource(List<User> newSource) {
        Collections.sort(newSource);
        mListUsers = newSource;
    }

    @Override
    public int getCount() {
        return mListUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return mListUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((User)getItem(position)).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = mInflater.inflate(R.layout.item_adapter_user, null);
            holder = new ViewHolder();

            holder.name = (TextView) view.findViewById(R.id.user_adapter_text_name);
            holder.company = (TextView) view.findViewById(R.id.user_adapter_text_company);
            holder.city = (TextView) view.findViewById(R.id.user_adapter_text_city);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final User user = mListUsers.get(position);
        final String name = user.getName();
        final String company = user.getCompany().getName();
        final String city = user.getAddress().getCity();

        holder.name.setText(name);
        holder.company.setText(company);
        holder.city.setText(city);

        return view;
    }

    protected static class ViewHolder {

        /**
         * The name of the user.
         */
        private TextView name;

        /**
         * The name of the company.
         */
        private TextView company;

        /**
         * The city of the user.
         */
        private TextView city;
    }
}
