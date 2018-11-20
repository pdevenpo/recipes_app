package edu.osu.recipe_app;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.osu.recipe_app.ui.MyAccount.UserData.User;
import edu.osu.recipe_app.ui.MyAccount.UserData.UserRepository;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class UserInstrumentedTest {
    private final String EMAIL = "testName@gmail.com";
    private final String NAME = "John Doe";
    private final String PASSWORD = "password12345";

    private final String UPDATED_NAME = "John Smith";
    private final String UPDATED_PASSWORD = "NewPassword1234";
    private final String UPDATED_EMAIL = "JohnSmith@yahoo.com";

    private UserRepository mUserRepository;

    @Before
    public void setUpUserRepository(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        mUserRepository = new UserRepository(appContext);
    }

    // Testing creation of new User (object) with attributes & retrieving those attributes
    @Test
    public void createUserTest(){
        User mUser = new User();

        mUser.setEmail("testName@gmail.com");
        mUser.setName("John Doe");
        mUser.setPassword("password12345");

        //assert stupidfuckingthing;
    }

    // Testing creation of new User (in database) with attributes & retrieving those attributes
    @Test
    public void addUserToDatabaseTestSuccess(){


        mUserRepository.insertUser(EMAIL, PASSWORD, NAME);

        User mUser = mUserRepository.getUser(NAME);

        /*
        assert (EMAIL.equals(mUser.getEmail()));
        assert (NAME.equals(mUser.getName()));
        assert (PASSWORD.equals(mUser.getPassword()));
        */
    }
}
