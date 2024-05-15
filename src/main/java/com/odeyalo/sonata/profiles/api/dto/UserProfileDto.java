package com.odeyalo.sonata.profiles.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
}
