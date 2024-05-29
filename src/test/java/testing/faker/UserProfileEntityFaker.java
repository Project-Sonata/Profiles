package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.profiles.entity.BasicUserInfo;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.model.Gender;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.ZoneId;

public final class UserProfileEntityFaker {
    private final UserProfileEntity.UserProfileEntityBuilder builder = UserProfileEntity.builder();
    private final Faker faker = Faker.instance();
    private final BasicUserInfo.BasicUserInfoBuilder<?, ?> userInfoBuilder = BasicUserInfo.builder();

    public UserProfileEntityFaker() {
        final LocalDate birthdate = faker.date().birthday(18, 70)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        final String publicId = RandomStringUtils.randomAlphanumeric(22);

        userInfoBuilder.publicId(publicId)
                .contextUri("sonata:user:" + publicId)
                .email(faker.internet().emailAddress());

        builder
                .id(faker.random().nextLong(Long.MAX_VALUE))
                .userInfo(userInfoBuilder.build())
                .displayName(faker.name().username())
                .country(faker.country().countryCode2())
                .birthdate(birthdate)
                .gender(faker.options().option(Gender.class))
                .userId(faker.random().nextLong(Long.MAX_VALUE));
    }

    public static UserProfileEntityFaker create() {
        return new UserProfileEntityFaker();
    }

    public UserProfileEntityFaker eraseId() {
        builder.id(null);
        return this;
    }

    public UserProfileEntityFaker withPublicId(final String publicId) {
        final BasicUserInfo userInfo = userInfoBuilder.publicId(publicId)
                // Override the context URI because it must be the same
                .contextUri("sonata:user:" + publicId)
                .build();

        builder.userInfo(userInfo);

        return this;
    }

    public UserProfileEntityFaker withDisplayName(final String displayName) {
        builder.displayName(displayName);
        return this;
    }

    public UserProfileEntityFaker withEmail(final String email) {
        final BasicUserInfo userInfo = userInfoBuilder.email(email).build();

        builder.userInfo(userInfo);

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

    public UserProfileEntityFaker withGender(final Gender gender) {
        builder.gender(gender);
        return this;
    }

    public UserProfileEntityFaker withContextUri(final String contextUri) {
        final BasicUserInfo userInfo = userInfoBuilder.contextUri(contextUri).build();

        builder.userInfo(userInfo);

        return this;
    }

    public UserProfileEntityFaker withUserInfo(final BasicUserInfo userInfo) {
        builder.userInfo(userInfo);
        return this;
    }
}
