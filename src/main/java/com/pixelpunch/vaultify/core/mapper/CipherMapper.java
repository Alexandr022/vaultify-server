package com.pixelpunch.vaultify.core.mapper;

import com.pixelpunch.vaultify.core.model.Cipher;
import com.pixelpunch.vaultify.web.dto.CipherDto;
import org.springframework.stereotype.Component;

@Component
public class CipherMapper {

    public static CipherDto cipherToDTO(Cipher cipher) {
        return new CipherDto(
                cipher.getId(),
                cipher.getOwner(),
                cipher.getData(),
                cipher.isFavorite(),
                cipher.getCollection(),
                cipher.isRePrompt(),
                cipher.getCreated(),
                cipher.getLastModified(),
                cipher.getLastServerSync()
        );
    }

    public static Cipher dtoToCipher(CipherDto cipherDTO) {
        return new Cipher(
                cipherDTO.getId(),
                cipherDTO.getOwner(),
                cipherDTO.getData(),
                cipherDTO.isFavorite(),
                cipherDTO.getCollection(),
                cipherDTO.isRePrompt(),
                cipherDTO.getCreated(),
                cipherDTO.getLastModified(),
                cipherDTO.getLastServerSync()
        );
    }

}
