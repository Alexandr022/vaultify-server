package com.pixelpunch.vaultify.core.service.implementations;

import com.pixelpunch.vaultify.core.model.Collections;
import com.pixelpunch.vaultify.core.model.User;
import com.pixelpunch.vaultify.core.repositories.CollectionsRepository;
import com.pixelpunch.vaultify.core.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CollectionsService implements com.pixelpunch.vaultify.core.service.ICollectionsService {

    private final CollectionsRepository collectionsRepository;

    private final UserRepository userRepository;

    @Override
    public List<Collections> getAllCollections() {
        return collectionsRepository.findAll();
    }

    @Override
    public Optional<Collections> getCollectionById(Long id) {
        return collectionsRepository.findById(id);
    }

    @Override
    public Collections createCollection(CreateCollectionsRequest createRequest) {
        User user = userRepository.findById(createRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + createRequest.getUserId()));

        Collections collection = new Collections();
        collection.setName(createRequest.getName());
        collection.setOwner((List<User>) user);
        Date now = new Date();
        collection.setCreated(now);
        collection.setLastModified(now);

        return collectionsRepository.save(collection);
    }

    @Override
    public Collections updateCollection(Long id, Collections updatedCollection) {
        Collections existingCollection = collectionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Collection not found with id: " + id));
        existingCollection.setName(updatedCollection.getName());
        return collectionsRepository.save(existingCollection);
    }

    @Override
    public void deleteCollection(Long id) {
        collectionsRepository.deleteById(id);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class CreateCollectionsRequest {
        private String name;
        private Long userId;
    }

}
