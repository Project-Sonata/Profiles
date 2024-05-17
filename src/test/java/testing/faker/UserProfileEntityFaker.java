package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.ZoneId;

public final class UserProfileEntityFaker {
    private final UserProfileEntity.UserProfileEntityBuilder builder = UserProfileEntity.builder();
    private final Faker faker = Faker.instance();

    public UserProfileEntityFaker() {
        final LocalDate birthdate = faker.date().birthday(18, 70)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        builder
                .id(faker.random().nextLong(Long.MAX_VALUE))
                .email(faker.internet().emailAddress())
                .publicId(RandomStringUtils.randomAlphanumeric(22))
                .displayName(faker.name().username())
                .country(faker.country().countryCode2())
                .birthdate(birthdate);
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

    public UserProfileEntityFaker withCountry(final String country) {
        builder.country(country);
        return this;
    }

    public UserProfileEntityFaker withBirthdate(final LocalDate birthdate) {
        builder.birthdate(birthdate);
        return this;
    }
}
