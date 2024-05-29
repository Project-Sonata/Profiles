package com.odeyalo.sonata.profiles.repository.r2dbc.support;

import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.repository.r2dbc.delegate.R2dbcBasicUserInfoRepositoryDelegate;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.event.AfterConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

/**
 * Enhance a {@link UserProfileEntity} with a {@link com.odeyalo.sonata.profiles.entity.BasicUserInfo}
 */
@Component
public final class UserInfoEnhancerAfterConvertCallback implements AfterConvertCallback<UserProfileEntity> {
    private final R2dbcBasicUserInfoRepositoryDelegate delegate;

    public UserInfoEnhancerAfterConvertCallback(@Lazy final R2dbcBasicUserInfoRepositoryDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    @NotNull
    public Publisher<UserProfileEntity> onAfterConvert(@NotNull final UserProfileEntity entity,
                                                       @NotNull final SqlIdentifier table) {
        return delegate.findById(entity.getUserId())
                .map(entity::withUserInfo);
    }
}
