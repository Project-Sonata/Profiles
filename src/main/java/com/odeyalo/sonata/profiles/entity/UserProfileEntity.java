package com.odeyalo.sonata.profiles.entity;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Value
@With
@Builder
@Table("profiles")
public class UserProfileEntity {
    @Id
    Long id;
    /**
     * Public ID that can be used to access the user's info
     */
    @Column("public_id")
    @NotNull
    String publicId;
    @Column("display_name")
    @NotNull
    String displayName;
    @Column("email")
    @NotNull
    String email;
}
