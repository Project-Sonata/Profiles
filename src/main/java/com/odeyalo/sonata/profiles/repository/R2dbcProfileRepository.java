package com.odeyalo.sonata.profiles.repository;

import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface R2dbcProfileRepository extends R2dbcRepository<UserProfileEntity, Long> {
}
