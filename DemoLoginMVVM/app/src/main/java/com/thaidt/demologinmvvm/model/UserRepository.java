package com.thaidt.demologinmvvm.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(Application application){
        UserDatabase database = UserDatabase.getInstance(application);
        userDao = database.userDao();
    }

    public void insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(User user){
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void delete(User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void deleteAllUser(){
        new DeleteAllUserAsyncTask(userDao).execute();
    }

    public User login(String username, String password){
        return userDao.login(username, password);
    }


    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUserAsyncTask extends AsyncTask<Void, Void, Void>{

        private UserDao userDao;

        private DeleteAllUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUser();
            return null;
        }
    }
}
