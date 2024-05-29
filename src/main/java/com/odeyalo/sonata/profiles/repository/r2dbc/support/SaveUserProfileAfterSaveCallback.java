package com.odeyalo.sonata.profiles.repository.r2dbc.support;

import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.repository.r2dbc.delegate.R2dbcProfileRepositoryDelegate;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.AfterSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public final class SaveUserProfileAfterSaveCallback implements AfterSaveCallback<UserEntity> {
    private final R2dbcProfileRepositoryDelegate profileRepository;

    public SaveUserProfileAfterSaveCallback(@Lazy final R2dbcProfileRepositoryDelegate profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    @NotNull
    public Publisher<UserEntity> onAfterSave(@NotNull final UserEntity entity,
                                             @NotNull final OutboundRow outboundRow,
                                             @NotNull final SqlIdentifier table) {
        final UserProfileEntity userProfile = entity.getProfile();
        Assert.notNull(userProfile, () -> "A user must have profile!");

        final UserProfileEntity savingTarget = userProfile.withUserId(entity.getId());

        return profileRepository.save(savingTarget)
                .map(entity::withProfile);
    }
}
