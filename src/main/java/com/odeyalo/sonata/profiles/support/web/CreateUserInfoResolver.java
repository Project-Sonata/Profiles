package com.odeyalo.sonata.profiles.support.web;

import com.odeyalo.sonata.profiles.api.dto.CreateUserInfoDto;
import com.odeyalo.sonata.profiles.model.core.Birthdate;
import com.odeyalo.sonata.profiles.model.core.Email;
import com.odeyalo.sonata.profiles.model.core.UserId;
import com.odeyalo.sonata.profiles.model.core.Username;
import com.odeyalo.sonata.profiles.service.CreateUserInfo;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.annotation.AbstractMessageReaderArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.reflect.Parameter;
import java.util.List;

@Component
public final class CreateUserInfoResolver extends AbstractMessageReaderArgumentResolver {

    public CreateUserInfoResolver(final List<HttpMessageReader<?>> readers) {
        super(readers);
    }

    @Override
    public boolean supportsParameter(@NotNull final MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(CreateUserInfo.class);
    }

    @Override
    @NotNull
    public Mono<Object> resolveArgument(@NotNull final MethodParameter parameter,
                                        @NotNull final BindingContext bindingContext,
                                        @NotNull final ServerWebExchange exchange) {

        final Parameter bodyTypeParameter = resolveBodyTypeParameter();

        return readBody(MethodParameter.forParameter(bodyTypeParameter), parameter, true, bindingContext, exchange)
                .cast(CreateUserInfoDto.class)
                .map(CreateUserInfoResolver::toCreateUserInfo);
    }

    private static CreateUserInfo toCreateUserInfo(final CreateUserInfoDto body) {
        return CreateUserInfo.builder()
                .id(UserId.fromString(body.getId()))
                .email(Email.of(body.getEmail()))
                .username(Username.of(body.getUsername()))
                .birthdate(Birthdate.of(body.getBirthdate()))
                .gender(body.getGender())
                .countryCode(body.getCountryCode())
                .build();
    }

    private Parameter resolveBodyTypeParameter() {
        //noinspection DataFlowIssue never be null because we always have method in this class
        return ReflectionUtils
                .findMethod(this.getClass(), "methodParameterDescriptorSupport", CreateUserInfoDto.class)
                .getParameters()[0];
    }

    /**
     * Support method to resolve {@link java.lang.reflect.Parameter} to set in {@link org.springframework.core.MethodParameter}
     * DO NOT DELETE IT IN ANY CASE, ONLY IF YOU FOUND A BETTER SOLUTION
     *
     * @param body - body class to read content to
     */
    @SuppressWarnings("unused")
    private void methodParameterDescriptorSupport(@Valid CreateUserInfoDto body) {

    }
}
