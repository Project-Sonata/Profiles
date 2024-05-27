package testing;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enable auto configuration fpr {@link ProfileHttpOperations}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ProfileHttpOperationsConfiguration.class)
public @interface AutoConfigureProfileHttpOperations {
}
