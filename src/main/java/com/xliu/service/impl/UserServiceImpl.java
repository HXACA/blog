package com.xliu.service.impl;

import com.xliu.bean.User;
import com.xliu.dao.UserDao;
import com.xliu.service.UserService;
import com.xliu.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/6 16:00
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, MD5Util.code(password));
        return user;
    }
}
