package testing;

import com.odeyalo.sonata.profiles.api.dto.CreateUserInfoDto;
import com.odeyalo.sonata.profiles.api.dto.UserProfileDto;

import java.net.URI;

/**
 * Simple contract to use endpoints to work with user endpoint
 */
public interface ProfileHttpOperations {
    /**
     * Create a user from provided info
     * @param body - info to send
     * @return - {@link URI} to access the created resource
     */
    URI createUser(CreateUserInfoDto body);

    /**
     * Fetch the user's profile by user's ID
     * @param userId - user's ID to fetch by
     * @return - {@link UserProfileDto} if user exists, or {@link RuntimeException} if not exist
     */
    UserProfileDto getUserProfile(String userId);

}
