package com.tohru.springmall.dao;

import com.tohru.springmall.dto.UserRegisterRequest;
import com.tohru.springmall.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserByEmail(String email);

    User getUserById(Integer userId);
}
