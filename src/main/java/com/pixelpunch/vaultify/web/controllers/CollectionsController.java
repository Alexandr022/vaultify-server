package com.pixelpunch.vaultify.web.controllers;

import com.pixelpunch.vaultify.core.mapper.CollectionsMapper;
import com.pixelpunch.vaultify.core.model.Collections;
import com.pixelpunch.vaultify.core.model.User;
import com.pixelpunch.vaultify.core.repositories.CollectionsRepository;
import com.pixelpunch.vaultify.core.repositories.UserRepository;
import com.pixelpunch.vaultify.core.service.ICollectionsService;
import com.pixelpunch.vaultify.core.service.implementations.CollectionsService;
import com.pixelpunch.vaultify.core.service.implementations.UserService;
import com.pixelpunch.vaultify.web.dto.CollectionsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/collections")
public class CollectionsController {

    private final ICollectionsService collectionsService;

    private final CollectionsRepository collectionsRepository;

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<CollectionsDto>> getAllCollections() {
        List<Collections> collections = collectionsService.getAllCollections();
        List<CollectionsDto> collectionDTOs = collections.stream()
                .map(CollectionsMapper::collectionsToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(collectionDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionsDto> getCollectionById(@PathVariable Long id) {
        Optional<Collections> collection = collectionsService.getCollectionById(id);
        return collection.map(c -> ResponseEntity.ok(CollectionsMapper.collectionsToDTO(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Collections createCollection(@RequestBody CollectionsService.CreateCollectionsRequest collectionsDto) {
        User user = userRepository.findById(collectionsDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + collectionsDto.getUserId()));

        Collections collection = new Collections();
        collection.setName(collectionsDto.getName());

        List<User> owners = new ArrayList<>();
        owners.add(user);

        collection.setOwner(owners);

        Date now = new Date();
        collection.setCreated(now);
        collection.setLastModified(now);

        Collections savedCollection = collectionsRepository.save(collection);

        user.getCollections().add(savedCollection);
        userRepository.save(user);

        return savedCollection;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionsDto> updateCollection(@PathVariable Long id, @RequestBody CollectionsDto updatedCollectionDTO) {
        Collections updatedCollection = CollectionsMapper.dtoToCollections(updatedCollectionDTO);
        Collections collection = collectionsService.updateCollection(id, updatedCollection);
        return ResponseEntity.ok(CollectionsMapper.collectionsToDTO(collection));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable Long id) {
        collectionsService.deleteCollection(id);
        return ResponseEntity.noContent().build();
    }
}
