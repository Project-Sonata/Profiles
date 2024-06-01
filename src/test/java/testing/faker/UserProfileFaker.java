package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.profiles.model.Gender;
import com.odeyalo.sonata.profiles.model.UserProfile;
import com.odeyalo.sonata.profiles.model.core.Birthdate;
import com.odeyalo.sonata.profiles.model.core.Email;
import com.odeyalo.sonata.profiles.model.core.UserId;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.ZoneId;

public final class UserProfileFaker {
    private final UserProfile.UserProfileBuilder builder = UserProfile.builder();

    public UserProfileFaker() {
        final Faker faker = Faker.instance();

        final LocalDate birthdate = faker.date().birthday(18, 70)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        final String id = RandomStringUtils.randomAlphanumeric(22);

        builder
                .id(UserId.fromString(id))
                .displayName(faker.name().username())
                .country(faker.country().countryCode2())
                .email(Email.of(faker.internet().emailAddress()))
                .birthdate(Birthdate.of(birthdate))
                .contextUri("sonata:user:" + id)
                .gender(faker.options().option(Gender.class));
    }

    public static UserProfileFaker create() {
        return new UserProfileFaker();
    }

    public UserProfileFaker withPublicId(final String publicId) {
        builder.id(UserId.fromString(publicId));
        builder.contextUri("sonata:user:" + publicId);
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
        builder.email(Email.of(email));
        return this;
    }

    public UserProfileFaker withBirthdate(final LocalDate birthdate) {
        builder.birthdate(Birthdate.of(birthdate));
        return this;
    }

    public UserProfileFaker withGender(final Gender gender) {
        builder.gender(gender);
        return this;
    }

    public UserProfile get() {
        return builder.build();
    }
}
