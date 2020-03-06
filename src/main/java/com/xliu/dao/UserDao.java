package com.xliu.dao;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/6 16:02
 */

import com.xliu.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);

}
