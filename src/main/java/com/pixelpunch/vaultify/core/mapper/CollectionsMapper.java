package com.pixelpunch.vaultify.core.mapper;

import com.pixelpunch.vaultify.core.model.Collections;
import com.pixelpunch.vaultify.web.dto.CollectionsDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CollectionsMapper {

    public static CollectionsDto collectionsToDTO(Collections collections) {
        return new CollectionsDto(
                collections.getId(),
                collections.getName(),
                collections.getCreated(),
                collections.getLastModified(),
                null
        );
    }

    public static Collections dtoToCollections(CollectionsDto collectionsDTO) {
        return new Collections(
                collectionsDTO.getId(),
                collectionsDTO.getName(),
                collectionsDTO.getCreated(),
                collectionsDTO.getLastModified(),
                collectionsDTO.getOwner().stream().map(UserMapper::dtoToUser).collect(Collectors.toList())
        );
    }
}
