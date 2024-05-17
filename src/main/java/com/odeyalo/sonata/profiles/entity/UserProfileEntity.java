package com.odeyalo.sonata.profiles.entity;

import com.odeyalo.sonata.profiles.model.Gender;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

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
}
