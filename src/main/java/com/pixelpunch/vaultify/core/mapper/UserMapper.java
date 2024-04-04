package com.pixelpunch.vaultify.core.mapper;

import com.pixelpunch.vaultify.core.model.User;
import com.pixelpunch.vaultify.web.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserDto userToDTO(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.isEmailVerified(),
                user.getEmailVerificationCode(),
                user.getEmailVerificationCodeExpiresAt(),
                user.getPassword(),
                user.getPrivateKey(),
                user.getPublicKey(),
                user.getPasswordHint(),
                user.isTwoFactorEnabled(),
                user.getTwoFactorVerificationCode(),
                user.getCreated(),
                user.getLastPasswordChange(),
                user.getCiphers() != null ? user.getCiphers().stream().map(CipherMapper::cipherToDTO).collect(Collectors.toList()) : null,
                user.getEmailChanges() != null ? user.getEmailChanges().stream().map(EmailChangeMapper::emailChangeToDTO).collect(Collectors.toList()) : null,
                user.getCollections() != null ? user.getCollections().stream().map(CollectionsMapper::collectionsToDTO).collect(Collectors.toList()) : null,
                user.getPasswords() != null ? user.getPasswords().stream().map(PasswordMapper::passwordToDTO).collect(Collectors.toList()) : null
        );
    }

    public static User dtoToUser(UserDto userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.isEmailVerified(),
                userDTO.getEmailVerificationCode(),
                userDTO.getEmailVerificationCodeExpiresAt(),
                userDTO.getPassword(),
                userDTO.getPrivateKey(),
                userDTO.getPublicKey(),
                userDTO.getPasswordHint(),
                userDTO.isTwoFactorEnabled(),
                userDTO.getTwoFactorVerificationCode(),
                userDTO.getCreated(),
                userDTO.getLastPasswordChange(),
                userDTO.getCiphers().stream().map(CipherMapper::dtoToCipher)
                        .collect(Collectors.toList()),
                userDTO.getEmailChanges().stream().map(EmailChangeMapper::dtoToEmailChange)
                        .collect(Collectors.toList()),
                userDTO.getPasswords().stream().map(PasswordMapper::dtoToPasswords)
                        .collect(Collectors.toList()),
                userDTO.getCollections().stream().map(CollectionsMapper::dtoToCollections)
                        .collect(Collectors.toList())

        );
    }

    public static List<UserDto> usersToDTO(List<User> users) {
        return users.stream().map(UserMapper::userToDTO).collect(Collectors.toList());
    }
}
