package com.tohru.springmall.service;

import com.tohru.springmall.dto.UserLoginRequest;
import com.tohru.springmall.dto.UserRegisterRequest;
import com.tohru.springmall.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);

}
