package edu.osu.recipe_app.ui.MyAccount.UserData;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import edu.osu.recipe_app.AppDatabase;

public class UserRepository {
    private AppDatabase db;

    public UserRepository(Context context){
        db = Room.databaseBuilder(context, AppDatabase.class, "AppDatabaseCheckpoint4ForGrader").allowMainThreadQueries().build();
    }

    public void insertUser(String email, String password, String name){
        User u = new User();

        u.setEmail(email);
        u.setPassword(password);
        u.setName(name);

        insertUserHelper(u);

    }

    public void insertUserHelper(final User user){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                Log.d("AsyncInsert", "user: " + user.getName() + " added");
                db.userDao().insert(user);
                return null;
            }
        }.execute();
    }

    public User getUser(final String name){
        return db.userDao().findByName(name);
    }

    public List<User> listUsers(){
        return db.userDao().getAll();
    }

    public void deleteUser(final String email){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.userDao().delete(db.userDao().findByEmail(email));
                return null;
            }
        }.execute();
    }

    public User findUserByEmail(final String email){
        return db.userDao().findByEmail(email);
    }

    public void updateUser(final User user){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.userDao().update(user);
                return null;
            }
        }.execute();
    }

    public void deleteTable(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.userDao().deleteTable();
                return null;
            }
        }.execute();
    }
}
