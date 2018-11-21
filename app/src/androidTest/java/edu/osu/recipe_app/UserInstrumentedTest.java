package edu.osu.recipe_app;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.osu.recipe_app.ui.MyAccount.UserData.User;
import edu.osu.recipe_app.ui.MyAccount.UserData.UserRepository;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class UserInstrumentedTest {
    private final static String EMAIL_1 = "testName@gmail.com";
    private final static String NAME_1 = "John Doe";
    private final static String PASSWORD_1 = "password12345";

    private final static String EMAIL_2 = "tim@gmail.com";
    private final static String NAME_2 = "Tim Lake";
    private final static String PASSWORD_2 = "mypassword";

    private final static String EMAIL_3 = "email@gmail.com";
    private final static String NAME_3 = "Old Name";
    private final static String PASSWORD_3 = "oldPassword";

    private final static String UPDATED_NAME = "John Smith";
    private final static String UPDATED_PASSWORD = "NewPassword1234";

    private UserRepository mUserRepository;

    @Before
    public void setUpUserRepository(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        mUserRepository = new UserRepository(appContext);

        if(mUserRepository.findUserByEmail(EMAIL_2) == null){
            mUserRepository.insertUser(EMAIL_2, PASSWORD_2, NAME_2);
        }

        if(mUserRepository.findUserByEmail(EMAIL_3) == null){
            mUserRepository.insertUser(EMAIL_3, PASSWORD_3, NAME_3);
        }
    }

    // Testing creation of new User (object) with attributes & retrieving those attributes
    @Test
    public void getUserAttributes(){
        User mUser = new User();

        mUser.setEmail("testName@gmail.com");
        mUser.setName("John Doe");
        mUser.setPassword("password12345");

        Assert.assertEquals(EMAIL_1, mUser.getEmail());
        Assert.assertEquals(NAME_1, mUser.getName());
        Assert.assertEquals(PASSWORD_1, mUser.getPassword());
    }

    // Testing creation of new User (in database) with attributes & retrieving those attributes
    @Test
    public void retrieveUserNameFromDatabaseTest(){
        User user = mUserRepository.findUserByEmail(EMAIL_2);

        Assert.assertEquals(user.getName(), NAME_2);
    }

    @Test
    public void retrieveUserEmailFromDatabaseTest(){
        User user = mUserRepository.findUserByEmail(EMAIL_2);

        Assert.assertEquals(user.getEmail(), EMAIL_2);
    }

    @Test
    public void retrieveUserPasswordFromDatabaseTest(){
        User user = mUserRepository.findUserByEmail(EMAIL_2);

        Assert.assertEquals(user.getPassword(), PASSWORD_2);
    }

    // Testing updates of User
    @Test
    public void updateUserTest(){
        User userToUpdate = new User();

        // Update User's name & password (Email is primary key, cannot be changed)
        userToUpdate.setEmail(EMAIL_3);
        userToUpdate.setName(UPDATED_NAME);
        userToUpdate.setPassword(UPDATED_PASSWORD);

        // Update user in database
        mUserRepository.updateUser(userToUpdate);

        User updatedUser = mUserRepository.findUserByEmail(EMAIL_3);

        // Assert user is updated
        Assert.assertEquals(EMAIL_3, updatedUser.getEmail());
        Assert.assertEquals(UPDATED_NAME, updatedUser.getName());
        Assert.assertEquals(UPDATED_PASSWORD, updatedUser.getPassword());
    }
}
