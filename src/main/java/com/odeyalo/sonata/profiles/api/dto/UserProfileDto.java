package com.odeyalo.sonata.profiles.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeyalo.sonata.profiles.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

@Value
@AllArgsConstructor(onConstructor_ = {@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)})
@Builder
public class UserProfileDto {
    @NotNull
    String id;
    @Nullable
    @JsonProperty("display_name")
    String displayName;
    @NotNull
    String email;
    @NotNull
    @JsonProperty("context_uri")
    String contextUri;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate birthdate;
    @NotNull
    Gender gender;
}
