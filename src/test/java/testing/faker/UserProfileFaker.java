package testing.faker;

import com.odeyalo.sonata.profiles.model.UserProfile;
import org.apache.commons.lang3.RandomStringUtils;

public final class UserProfileFaker {
    private final UserProfile.UserProfileBuilder builder = UserProfile.builder();

    public UserProfileFaker() {
        builder
                .id(RandomStringUtils.randomAlphanumeric(22));
    }

    public static UserProfileFaker create() {
        return new UserProfileFaker();
    }

    public UserProfileFaker withPublicId(final String publicId) {
        builder.id(publicId);
        return this;
    }

    public UserProfile get() {
        return builder.build();
    }
}
