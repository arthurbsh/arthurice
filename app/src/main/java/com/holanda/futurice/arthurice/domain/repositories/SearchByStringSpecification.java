package com.holanda.futurice.arthurice.domain.repositories;

import com.holanda.futurice.arthurice.domain.model.User;

/**
 * Created by holanda on 5/22/16.
 */
public class SearchByStringSpecification implements ISpecification<User> {

    private String mSearchString;

    public SearchByStringSpecification(String searchString) {
        mSearchString = searchString.toLowerCase();
    }

    @Override
    public boolean isSatisfiedBy(User user) {

        boolean companyMatches = user.getCompany().getName().toLowerCase().contains(mSearchString);
        boolean cityMatches = user.getAddress().getCity().toLowerCase().contains(mSearchString);
        boolean nameMatches = user.getName().toLowerCase().contains(mSearchString);

        return nameMatches || cityMatches || companyMatches;

    }
}
