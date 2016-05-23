package com.holanda.futurice.arthurice;

import com.holanda.futurice.arthurice.domain.model.Address;
import com.holanda.futurice.arthurice.domain.model.Company;
import com.holanda.futurice.arthurice.domain.model.User;

/**
 * Created by Holanda on 5/22/2016.
 */
public class TestUtils {

    public static User basicUser(int id, String name, String company, String city) {
        Company c = new Company(company, "", "");
        Address a = new Address("", "", city, "", null);
        User u = new User(id, name, "", "", "", "", a, c);

        return u;
    }

}

