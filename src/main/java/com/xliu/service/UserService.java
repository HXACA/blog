package com.xliu.service;

import com.xliu.bean.User;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/6 16:00
 */
public interface UserService {
    User checkUser(String username,String password);
}
