package com.odeyalo.sonata.profiles.entity;

import com.odeyalo.sonata.profiles.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Value
@With
@Builder
@Table("profiles")
@AllArgsConstructor
public class UserProfileEntity {
    @Id
    Long id;
    @Column("display_name")
    @NotNull
    String displayName;
    @Column("email")
    @NotNull
    String email;
    // ENHANCE: Not sure about moving it to separated class called Country. I don't want to introduce additional complexity to code
    // ENHANCE: Any way if there is any need it will be easily decoupled
    @Column("country")
    @NotNull
    String country;
    @Column("birthdate")
    @NotNull
    LocalDate birthdate;
    @Column("gender")
    @NotNull
    Gender gender;
    @Transient
    BasicUserInfo userInfo;
    @Column("user_id")
    @NotNull
    Long userId;

    @PersistenceCreator
    public UserProfileEntity(final Long id, @NotNull final String displayName, @NotNull final String email, @NotNull final String country, @NotNull final LocalDate birthdate, @NotNull final Gender gender, @NotNull final Long userId) {
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.country = country;
        this.birthdate = birthdate;
        this.gender = gender;
        this.userId = userId;
        this.userInfo = null;
    }
}
