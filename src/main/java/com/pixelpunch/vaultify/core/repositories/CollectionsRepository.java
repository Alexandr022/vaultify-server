package com.pixelpunch.vaultify.core.repositories;

import com.pixelpunch.vaultify.core.model.Collections;
import com.pixelpunch.vaultify.core.model.Password;
import com.pixelpunch.vaultify.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CollectionsRepository extends JpaRepository<Collections, Long> {
    List<Collections> findAllByOwner(User owner);

}
