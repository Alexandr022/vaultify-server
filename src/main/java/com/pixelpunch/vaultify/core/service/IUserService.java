package com.pixelpunch.vaultify.core.service;

import com.pixelpunch.vaultify.core.service.implementations.UserService;
import com.pixelpunch.vaultify.web.dto.UserDto;

import java.util.Date;
import java.util.List;

public interface IUserService {
    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto createUserRequest);

    UserDto updateUser(UserDto userDTO);

    void deleteUser(Long userId);

    UserDto getUserByEmail(String email);

    void deleteUnverifiedUsers(Date date);

    String login(String email, String password);

    String verify(String email, String code);

    String register(UserDto userDTO);

    void sendPasswordResetCode(String email);

    void resetPassword(String email, String code, String newPassword);
}

