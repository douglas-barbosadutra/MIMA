package com.virtualpairprogrammers.services;



import com.virtualpairprogrammers.dao.UserDao;
import com.virtualpairprogrammers.domain.User;

import java.util.List;

public class UserService
{
    private UserDao userDao;
    private static UserService reference;

    public static UserService getService() {
        if (reference == null)
            reference = new UserService();
        return reference;
    }



    public UserService()
    {
        this.userDao = new UserDao();
    }

    public List<User> getAllUser ()
    {
        return this.userDao.getAllUser();
    }

    public boolean insertUser (User user) {
        return this.userDao.insertUser(user);
    }

}
