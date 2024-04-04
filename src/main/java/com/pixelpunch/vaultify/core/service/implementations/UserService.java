package com.pixelpunch.vaultify.core.service.implementations;

import com.pixelpunch.vaultify.core.mapper.UserMapper;
import com.pixelpunch.vaultify.core.model.User;
import com.pixelpunch.vaultify.core.repositories.UserRepository;
import com.pixelpunch.vaultify.core.service.IUserService;
import com.pixelpunch.vaultify.web.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final KeyPairGenerator keyPairGenerator;

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user != null ? userMapper.userToDTO(user) : null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.usersToDTO(users);
    }

    @Override
    public UserDto createUser(UserDto createUserRequest) {
        User user = new User();
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setPasswordHint(createUserRequest.getPasswordHint());

        try {
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            user.setPublicKey(publicKeyString);
            user.setPrivateKey(privateKeyString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        User savedUser = userRepository.save(user);
        return userMapper.userToDTO(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDTO) {
        User user = userMapper.dtoToUser(userDTO);
        User updatedUser = userRepository.save(user);
        return userMapper.userToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? userMapper.userToDTO(user) : null;
    }

    @Override
    public void deleteUnverifiedUsers(Date date) {
        userRepository.deleteUnverifiedUsers(date);
    }

    @Override
    public String login(String email, String password) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Генерация и возврат токена для аутентификации
                return null;
            }
        }
        return null;
    }

    @Override
    public String verify(String email, String code) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (code.equals(user.getTwoFactorVerificationCode())) {
                // Генерация и возврат токена для аутентификации
                return null;
            }
        }
        return null;
    }

    @Override
    public String register(UserDto userDTO) {
        // Создание нового пользователя в базе данных
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        // Дополнительная логика для регистрации, отправки кода подтверждения и т. д.
        userRepository.save(user);
        return user.getEmailVerificationCode();
    }

    @Override
    public void sendPasswordResetCode(String email) {
        // Логика отправки временного кода для сброса пароля на указанную почту
    }

    @Override
    public void resetPassword(String email, String code, String newPassword) {
        // Логика сброса пароля с использованием временного кода
    }

    public List<User> getUsersByEmailVerified(boolean emailVerified) {
        return userRepository.findByEmailVerifiedCustomQuery(emailVerified);
    }

}


