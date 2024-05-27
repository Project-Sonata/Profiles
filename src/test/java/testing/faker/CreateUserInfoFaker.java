package testing.faker;

import com.odeyalo.sonata.profiles.model.core.UserId;
import com.odeyalo.sonata.profiles.service.CreateUserInfo;

public final class CreateUserInfoFaker {
    private final CreateUserInfo.CreateUserInfoBuilder builder = CreateUserInfo.builder();

    public CreateUserInfoFaker() {
        builder
                .id(UserId.random());
    }

    public static CreateUserInfoFaker create() {
        return new CreateUserInfoFaker();
    }

    public CreateUserInfoFaker withId(final String id) {
        builder.id(UserId.fromString(id));
        return this;
    }

    public CreateUserInfo get() {
        return builder.build();
    }
}
