package com.holanda.futurice.arthurice.domain.repositories;

import com.holanda.futurice.arthurice.domain.model.User;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.holanda.futurice.arthurice.TestUtils.basicUser;
import static org.junit.Assert.*;

/**
 * Created by Holanda on 5/22/2016.
 */
public class UsersRepositoryTest {

    private UsersRepository repository;
    private User user1;

    @Before
    public void setup(){

        repository = new UsersRepository();

        user1 = basicUser(1, "First User", "Ice of the future", "Coldtown");
        repository.including(user1);
        repository.including(basicUser(2, "Second User", "Ice of the future", "Coldtown"));
        repository.including(basicUser(3, "Third User", "Ice of the future", "Coldtown"));
        repository.including(basicUser(4, "Fourth User", "Ice of the future", "Coldtown"));
        repository.including(basicUser(5, "Fifth User", "Ice of the future", "Coldtown"));
    }

    @Test
    public void testAll() {
        assertEquals(5, repository.all().size());
    }

    @Test
    public void testMatching() throws Exception {
        SearchByStringSpecification spec = new SearchByStringSpecification("third");

        List<User> list = repository.matching(spec);

        assertEquals(1, list.size());
    }

    @Test
    public void testIncluding() throws Exception {
        int previousSize = repository.size();

        repository.including(basicUser(6, "Fifth User", "Ice of the future", "Coldtown"));

        assertEquals(previousSize+1, repository.size());

    }

    @Test
    public void testExcluding() throws Exception {
        int previousSize = repository.size();

        repository.excluding(user1);


        assertEquals(previousSize-1, repository.size());
    }

    @Test
    public void testIncludes() throws Exception {
        assertTrue(repository.includes(user1));
    }

    @Test
    public void testGet() throws Exception {
        User user = repository.get(1);

        assertEquals(user, user1);
    }

    @Test
    public void testClear() throws Exception {
        repository.clear();

        assertEquals(repository.size(), 0);

    }
}