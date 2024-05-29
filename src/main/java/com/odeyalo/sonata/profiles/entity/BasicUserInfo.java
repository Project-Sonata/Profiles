package com.odeyalo.sonata.profiles.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public sealed class BasicUserInfo permits UserEntity {
    @Id
    @With
    Long id;
    /**
     * Public ID that can be used to access the user's info
     */
    @Column("sonata_id")
    @NotNull
    @With
    String publicId;
    @Column("context_uri")
    @NotNull
    @With
    String contextUri;
}
