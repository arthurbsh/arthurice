package com.holanda.futurice.arthurice.domain.repositories;

import com.holanda.futurice.arthurice.domain.model.Address;
import com.holanda.futurice.arthurice.domain.model.Company;
import com.holanda.futurice.arthurice.domain.model.User;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by holanda on 5/22/16.
 */
public class SearchByStringSpecificationTest {

    @Test
    public void testBasics() {
        User user1 = basicUser("Simple User", "Ice of the future", "Coldtown");
        User user2 = basicUser("Taipo User", "Fire of the past", "Hot town");

        SearchByStringSpecification spec1 = new SearchByStringSpecification("first");
    }

    @Test
    public void testIgnoreCase() {
        User user1 = basicUser("First User", "Best Company", "not a capital");
        User user2 = basicUser("Second User", "Best Company", "CAPITAL");
        User user3 = basicUser("Third User", "Best Company", "Xmall");
        User user4 = basicUser("fourth User", "Best Company", "Xmaller");

        SearchByStringSpecification spec1 = new SearchByStringSpecification("first");
        SearchByStringSpecification spec2 = new SearchByStringSpecification("user");
        SearchByStringSpecification spec3 = new SearchByStringSpecification("capital");
        SearchByStringSpecification spec4 = new SearchByStringSpecification("shallnotpass");
        SearchByStringSpecification spec5 = new SearchByStringSpecification("best");

        assertTrue(spec1.isSatisfiedBy(user1));
        assertTrue(spec3.isSatisfiedBy(user2));
    }

    private User basicUser(String name, String company, String city) {
        Company c = new Company(company, "", "");
        Address a = new Address("", "", city, "", null);
        User u = new User(0, name, "", "", "", "", a, c);

        return u;
    }

}