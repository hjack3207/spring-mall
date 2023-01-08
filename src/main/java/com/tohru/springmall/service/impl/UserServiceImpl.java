package com.tohru.springmall.service.impl;

import com.tohru.springmall.dao.UserDao;
import com.tohru.springmall.dto.UserRegisterRequest;
import com.tohru.springmall.model.User;
import com.tohru.springmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
