package com.pixelpunch.vaultify.core.service;

import com.pixelpunch.vaultify.core.model.Collections;
import com.pixelpunch.vaultify.core.service.implementations.CollectionsService;

import java.util.List;
import java.util.Optional;

public interface ICollectionsService {
    List<Collections> getAllCollections();

    Optional<Collections> getCollectionById(Long id);

    Collections createCollection(CollectionsService.CreateCollectionsRequest createRequest);

    Collections updateCollection(Long id, Collections updatedCollection);

    void deleteCollection(Long id);
}
