package edu.osu.recipe_app;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class UserRepository {
    private AppDatabase db;

    public UserRepository(Context context){
        db = Room.databaseBuilder(context, AppDatabase.class, "app-database-checkpoint4").allowMainThreadQueries().build();
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

    public void deleteUser(final User user){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.userDao().delete(user);
                return null;
            }
        }.execute();
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
