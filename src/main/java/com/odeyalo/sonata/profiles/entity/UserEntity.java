package com.odeyalo.sonata.profiles.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Value
@Table("users")
@SuperBuilder(toBuilder = true)
public class UserEntity extends BasicUserInfo {
    /**
     * Since we're using R2DBC we do not have access to {@link UserProfileEntity} and it is can be nullable.
     * IT IS NEVER NULL AFTER ALL CONVERTERS(or other enhancers for entity) HAVE BEEN APPLIED!
     */
    @Transient
    UserProfileEntity profile;

    @PersistenceCreator
    public UserEntity(@Nullable final Long id,
                      @NotNull final String publicId,
                      @NotNull final String contextUri,
                      @NotNull final  String email) {
        super(id, publicId, contextUri, email);
        this.profile = null;
    }

    public UserEntity(@NotNull final String publicId,
                      @NotNull final String contextUri,
                      @NotNull final String email,
                      @NotNull final UserProfileEntity profile) {
        super(null, publicId, contextUri, email);
        this.profile = profile;
    }

    @Override
    public UserEntity withId(@NotNull final Long id) {
        return Objects.equals(id, super.id) ? this : toBuilder().id(id).build();
    }

    @NotNull
    public UserEntity withProfile(@NotNull final UserProfileEntity profile) {
        return Objects.equals(profile, this.profile) ? this : toBuilder().profile(profile).build();
    }
}
