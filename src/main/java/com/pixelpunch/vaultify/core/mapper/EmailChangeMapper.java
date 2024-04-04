package com.pixelpunch.vaultify.core.mapper;

import com.pixelpunch.vaultify.core.model.EmailChange;
import com.pixelpunch.vaultify.web.dto.EmailChangeDto;
import org.springframework.stereotype.Component;

@Component
public class EmailChangeMapper {

    public static EmailChangeDto emailChangeToDTO(EmailChange emailChange) {
        return new EmailChangeDto(
                emailChange.getId(),
                emailChange.getNewEmail(),
                emailChange.getCode(),
                emailChange.getCodeExpiresAt(),
                emailChange.getNewCiphers(),
                emailChange.getNewPublicKey(),
                emailChange.getOwner()
        );
    }

    public static EmailChange dtoToEmailChange(EmailChangeDto emailChangeDTO) {
        return new EmailChange(
                emailChangeDTO.getId(),
                emailChangeDTO.getNewEmail(),
                emailChangeDTO.getCode(),
                emailChangeDTO.getCodeExpiresAt(),
                emailChangeDTO.getNewCiphers(),
                emailChangeDTO.getNewPublicKey(),
                emailChangeDTO.getUser()
        );
    }
}
