package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.profiles.model.core.Birthdate;
import com.odeyalo.sonata.profiles.model.core.Email;
import com.odeyalo.sonata.profiles.model.core.UserId;
import com.odeyalo.sonata.profiles.service.CreateUserInfo;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;

public final class CreateUserInfoFaker {
    private final CreateUserInfo.CreateUserInfoBuilder builder = CreateUserInfo.builder();

    public CreateUserInfoFaker() {
        final Faker faker = Faker.instance();

        final LocalDate birthdate = faker.date().birthday(18, 70)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        builder
                .id(UserId.random())
                .email(Email.of(faker.internet().emailAddress()))
                .countryCode(StringUtils.toRootUpperCase(faker.country().countryCode2()))
                .birthdate(Birthdate.of(birthdate));
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

    public CreateUserInfoFaker withCountryCode(final String countryCode) {
        builder.countryCode(countryCode);
        return this;
    }

    public CreateUserInfo get() {
        return builder.build();
    }

    public CreateUserInfoFaker withBirthdate(final LocalDate birthdate) {
        builder.birthdate(Birthdate.of(birthdate));
        return this;
    }
}
