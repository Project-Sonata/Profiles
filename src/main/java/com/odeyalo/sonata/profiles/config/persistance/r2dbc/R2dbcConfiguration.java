package com.odeyalo.sonata.profiles.config.persistance.r2dbc;

import com.odeyalo.sonata.profiles.config.persistance.r2dbc.support.GenderEnumMappingConverter;
import com.odeyalo.sonata.profiles.model.Gender;
import io.r2dbc.postgresql.codec.EnumCodec;
import io.r2dbc.spi.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.r2dbc.ConnectionFactoryOptionsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.DialectResolver;
import org.springframework.data.r2dbc.dialect.R2dbcDialect;
import org.springframework.r2dbc.core.DatabaseClient;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class R2dbcConfiguration {
    private final Logger logger = LoggerFactory.getLogger(R2dbcConfiguration.class);

    @Bean
    public ConnectionFactoryOptionsBuilderCustomizer connectionFactoryOptionsBuilderCustomizer() {

        return builder -> {
            builder.option(Option.valueOf("extensions"),
                    List.of(
                            EnumCodec.builder()
                                    .withEnum("gender", Gender.class)
                                    .withRegistrationPriority(EnumCodec.Builder.RegistrationPriority.FIRST)
                                    .build()
                    )
            );

            logger.info("Registered custom EnumCodec for: {}", List.of(
                    Gender.class.getSimpleName())
            );
        };
    }

    @Bean
    public R2dbcCustomConversions r2dbcCustomConversions(DatabaseClient databaseClient) {
        logger.info("Apply R2DBC custom conversions");
        R2dbcDialect dialect = DialectResolver.getDialect(databaseClient.getConnectionFactory());
        List<Object> converters = new ArrayList<>(dialect.getConverters());
        converters.addAll(R2dbcCustomConversions.STORE_CONVERTERS);
        return new R2dbcCustomConversions(
                CustomConversions.StoreConversions.of(dialect.getSimpleTypeHolder(), converters),
                List.of(
                        new GenderEnumMappingConverter()
                ));
    }
}
