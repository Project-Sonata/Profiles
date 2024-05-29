package com.odeyalo.sonata.profiles.repository.r2dbc.support;

import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.repository.r2dbc.delegate.R2dbcProfileRepositoryDelegate;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.event.AfterConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

/**
 * R2DBC converter that invoked after {@link UserEntity} has been converted and used to set a {@link com.odeyalo.sonata.profiles.entity.UserProfileEntity} associated with retrieved user
 */
@Component
public final class AssociateUserProfileAfterConvertCallback implements AfterConvertCallback<UserEntity> {
    private final R2dbcProfileRepositoryDelegate userProfileRepository;

    public AssociateUserProfileAfterConvertCallback(@Lazy final R2dbcProfileRepositoryDelegate userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    @NotNull
    public Publisher<UserEntity> onAfterConvert(@NotNull final UserEntity user,
                                                @NotNull final SqlIdentifier table) {

        return userProfileRepository.findByUserId(user.getId())
                .map(user::withProfile);
    }
}
