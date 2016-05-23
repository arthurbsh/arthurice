package com.holanda.futurice.arthurice.domain.repositories;

import com.holanda.futurice.arthurice.domain.model.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Repository for User.
 */
public class UsersRepository implements IRepository<User > {

    private HashMap<Integer, User> mUsers;

    public UsersRepository() {
        mUsers = new HashMap<>();
    }

    @Override
    public List<User> all() {
        return new LinkedList<>(mUsers.values());
    }

    @Override
    public List<User> matching(ISpecification<User> specification) {
        List<User> usersMatching = new LinkedList<>();

        for (User u : mUsers.values()) {
            if (specification.isSatisfiedBy(u)) {
                usersMatching.add(u);
            }
        }

        return usersMatching;
    }



    @Override
    public IRepository<User> including(User user) {
        mUsers.put(user.getId(), user);
        return this;
    }

    @Override
    public IRepository<User> excluding(User user) {
        mUsers.remove(user.getId());
        return this;
    }

    @Override
    public int size() {
        return mUsers.size();
    }

    @Override
    public boolean includes(User user) {
        return mUsers.containsKey(user.getId());
    }

    @Override
    public User get(int id) {
        if (!mUsers.containsKey(id)) {
            return null;
        }

        return mUsers.get(id);
    }

    @Override
    public void clear() {
        mUsers.clear();
    }
}
