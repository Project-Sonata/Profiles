package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import org.apache.commons.lang3.RandomStringUtils;

public final class UserProfileEntityFaker {
    private final UserProfileEntity.UserProfileEntityBuilder builder = UserProfileEntity.builder();
    private final Faker faker = Faker.instance();

    public UserProfileEntityFaker() {
        builder
                .id(faker.random().nextLong(Long.MAX_VALUE))
                .email(faker.internet().emailAddress())
                .publicId(RandomStringUtils.randomAlphanumeric(22))
                .displayName(faker.name().username());
    }

    public static UserProfileEntityFaker create() {
        return new UserProfileEntityFaker();
    }

    public UserProfileEntityFaker eraseId() {
        builder.id(null);
        return this;
    }

    public UserProfileEntityFaker withPublicId(final String publicId) {
        builder.publicId(publicId);
        return this;
    }

    public UserProfileEntityFaker withDisplayName(final String displayName) {
        builder.displayName(displayName);
        return this;
    }

    public UserProfileEntityFaker withEmail(final String email) {
        builder.email(email);
        return this;
    }

    public UserProfileEntity get() {
        return builder.build();
    }
}
