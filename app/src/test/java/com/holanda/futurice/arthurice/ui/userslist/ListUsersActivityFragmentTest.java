package com.holanda.futurice.arthurice.ui.userslist;

import android.widget.ListView;

import com.holanda.futurice.arthurice.BuildConfig;
import com.holanda.futurice.arthurice.R;
import com.holanda.futurice.arthurice.domain.model.User;
import com.holanda.futurice.arthurice.domain.repositories.UsersRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowListView;

import static com.holanda.futurice.arthurice.TestUtils.basicUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Holanda on 5/22/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class ListUsersActivityFragmentTest {


    private UsersRepository mRepository = new UsersRepository();
    private User user1;

    @Before
    public void setup() {
        mRepository = new UsersRepository();

        user1 = basicUser(1, "A First User", "Ice of the future", "Coldtown");
        mRepository.including(user1);
        mRepository.including(basicUser(2, "Second User", "Ice of the future", "Coldtown"));
        mRepository.including(basicUser(3, "Third User", "Ice of the future", "Coldtown"));
        mRepository.including(basicUser(4, "Fourth User", "Ice of the future", "Coldtown"));
        mRepository.including(basicUser(5, "Fifth User", "Ice of the future", "Coldtown"));
    }

    @Test
    public void testClickOnListView(){

        ListUsersActivity usersActivity = Robolectric.setupActivity(ListUsersActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(usersActivity);


        ListView lv = (ListView) usersActivity.findViewById(R.id.listViewUsers);

        //checks for the view really existing
        assertNotNull(lv);

        lv.setAdapter(new UsersListAdapter(usersActivity.getBaseContext(), mRepository.all()));

        //checks for the view getting populated...
        assertEquals(5, lv.getChildCount());
        //... with something valid
        assertNotNull(lv.getChildAt(0));

        ShadowListView shadowListView = Shadows.shadowOf(lv);

        //clicks the listview first item, should go to DetailsActivity and pass the clicked user id to it
        shadowListView.performItemClick(0);

        //tests if id was passed
        String key = usersActivity.getString(R.string.intent_extras_user_id);
        int receivedId = shadowActivity.peekNextStartedActivity().getExtras().getInt(key);
        assertEquals(user1.getId(), receivedId);
    }

}