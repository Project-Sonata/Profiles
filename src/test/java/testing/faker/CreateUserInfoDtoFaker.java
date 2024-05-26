package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.profiles.api.dto.CreateUserInfoDto;
import com.odeyalo.sonata.profiles.model.Gender;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;

public final class CreateUserInfoDtoFaker {
    private final CreateUserInfoDto.CreateUserInfoDtoBuilder builder = CreateUserInfoDto.builder();

    private CreateUserInfoDtoFaker() {
        final Faker faker = Faker.instance();

        final LocalDate birthdate = faker.date().birthday(18, 70)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        builder
                .id(RandomStringUtils.randomAlphanumeric(22))
                .email(faker.internet().emailAddress())
                .birthdate(birthdate)
                .username(faker.name().username())
                .gender(faker.options().option(Gender.class))
                // faker returns country code in lower case, by spec we require uppercase code
                .countryCode(StringUtils.toRootUpperCase(faker.country().countryCode2()));
    }

    public static CreateUserInfoDtoFaker create() {
        return new CreateUserInfoDtoFaker();
    }

    public CreateUserInfoDtoFaker withId(final String id) {
        builder.id(id);
        return this;
    }

    public CreateUserInfoDtoFaker withEmail(final String email) {
        builder.email(email);
        return this;
    }

    public CreateUserInfoDtoFaker withBirthdate(final LocalDate birthdate) {
        builder.birthdate(birthdate);
        return this;
    }

    public CreateUserInfoDtoFaker withUsername(final String username) {
        builder.username(username);
        return this;
    }

    public CreateUserInfoDtoFaker withGender(final Gender gender) {
        builder.gender(gender);
        return this;
    }

    public CreateUserInfoDtoFaker withCountry(final String isoCode) {
        builder.countryCode(isoCode);
        return this;
    }

    public CreateUserInfoDto get() {
        return builder.build();
    }
}
