package com.holanda.futurice.arthurice.domain.repositories;

import com.holanda.futurice.arthurice.domain.model.Address;
import com.holanda.futurice.arthurice.domain.model.Company;
import com.holanda.futurice.arthurice.domain.model.User;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by holanda on 5/22/16.
 */
public class SearchByStringSpecificationTest {

    @Test
    public void testBasics() {
        User user1 = basicUser("Simple User", "Ice of the future", "Coldtown");
        User user2 = basicUser("Taipo Usre", "Fire of the past", "Hot town");

        SearchByStringSpecification spec1 = new SearchByStringSpecification("User");
        SearchByStringSpecification spec2 = new SearchByStringSpecification("Ice");
        SearchByStringSpecification spec3 = new SearchByStringSpecification("Cold");

        assertTrue(spec1.isSatisfiedBy(user1));
        assertTrue(spec2.isSatisfiedBy(user1));
        assertTrue(spec3.isSatisfiedBy(user1));

        assertFalse(spec1.isSatisfiedBy(user2));
        assertFalse(spec2.isSatisfiedBy(user2));
        assertFalse(spec3.isSatisfiedBy(user2));
    }

    @Test
    public void testIgnoreCase() {
        User user1 = basicUser("CAPSLOCK", "Ice of the future", "capital");

        SearchByStringSpecification spec1 = new SearchByStringSpecification("capslock");
        SearchByStringSpecification spec2 = new SearchByStringSpecification("CAPITAL");

        assertTrue(spec1.isSatisfiedBy(user1));
        assertTrue(spec2.isSatisfiedBy(user1));
    }

    private User basicUser(String name, String company, String city) {
        Company c = new Company(company, "", "");
        Address a = new Address("", "", city, "", null);
        User u = new User(0, name, "", "", "", "", a, c);

        return u;
    }

}