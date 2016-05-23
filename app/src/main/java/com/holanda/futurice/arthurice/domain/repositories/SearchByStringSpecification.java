package com.holanda.futurice.arthurice.domain.repositories;

import com.holanda.futurice.arthurice.domain.model.User;

/**
 * Specification to filter Users by a given string.
 */
public class SearchByStringSpecification implements ISpecification<User> {

    private String mSearchString;

    public SearchByStringSpecification(String searchString) {
        mSearchString = searchString.toLowerCase();
    }

    /**
     * Returns if the given user has the given substring in one his name, company name or city.
     * @param user The given user.
     * @return true if the user's name, company o city contains the substring, false otherwise.
     */
    @Override
    public boolean isSatisfiedBy(User user) {

        boolean companyMatches = user.getCompany().getName().toLowerCase().contains(mSearchString);
        boolean cityMatches = user.getAddress().getCity().toLowerCase().contains(mSearchString);
        boolean nameMatches = user.getName().toLowerCase().contains(mSearchString);

        return nameMatches || cityMatches || companyMatches;

    }
}
