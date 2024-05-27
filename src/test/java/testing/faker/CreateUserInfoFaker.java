package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.profiles.model.core.Email;
import com.odeyalo.sonata.profiles.model.core.UserId;
import com.odeyalo.sonata.profiles.service.CreateUserInfo;

public final class CreateUserInfoFaker {
    private final CreateUserInfo.CreateUserInfoBuilder builder = CreateUserInfo.builder();

    public CreateUserInfoFaker() {
        Faker faker = Faker.instance();

        builder
                .id(UserId.random())
                .email(Email.of(faker.internet().emailAddress()));
    }

    public static CreateUserInfoFaker create() {
        return new CreateUserInfoFaker();
    }

    public CreateUserInfoFaker withId(final String id) {
        builder.id(UserId.fromString(id));
        return this;
    }

    public CreateUserInfoFaker withEmail(final String email) {
        builder.email(Email.of(email));
        return this;
    }

    public CreateUserInfo get() {
        return builder.build();
    }
}
