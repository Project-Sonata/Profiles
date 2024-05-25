package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.profiles.api.dto.CreateUserInfoDto;
import org.apache.commons.lang3.RandomStringUtils;

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
                .username(faker.name().username());
    }

    public static CreateUserInfoDtoFaker create() {
        return new CreateUserInfoDtoFaker();
    }

    public CreateUserInfoDtoFaker withId(final String id) {
        builder.id(id);
        return this;
    }

    public CreateUserInfoDto get() {
        return builder.build();
    }
}