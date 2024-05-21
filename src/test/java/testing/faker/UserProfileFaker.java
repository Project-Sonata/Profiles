package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.profiles.model.UserProfile;
import org.apache.commons.lang3.RandomStringUtils;

public final class UserProfileFaker {
    private final UserProfile.UserProfileBuilder builder = UserProfile.builder();

    public UserProfileFaker() {
        final Faker faker = Faker.instance();

        builder
                .id(RandomStringUtils.randomAlphanumeric(22))
                .displayName(faker.name().username())
                .country(faker.country().countryCode2())
                .email(faker.internet().emailAddress());
    }

    public static UserProfileFaker create() {
        return new UserProfileFaker();
    }

    public UserProfileFaker withPublicId(final String publicId) {
        builder.id(publicId);
        return this;
    }

    public UserProfileFaker withDisplayName(final String displayName) {
        builder.displayName(displayName);
        return this;
    }

    public UserProfileFaker withCountry(final String country) {
        builder.country(country);
        return this;
    }

    public UserProfileFaker withEmail(final String email) {
        builder.email(email);
        return this;
    }

    public UserProfile get() {
        return builder.build();
    }
}
