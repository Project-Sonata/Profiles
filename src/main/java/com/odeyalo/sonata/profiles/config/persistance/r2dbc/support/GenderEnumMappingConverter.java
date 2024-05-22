package com.odeyalo.sonata.profiles.config.persistance.r2dbc.support;

import com.odeyalo.sonata.profiles.model.Gender;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.convert.EnumWriteSupport;

/**
 * Simple converter to force R2DBC use the Postgres enum instead of VARCHAR
 */
@WritingConverter
public final class GenderEnumMappingConverter extends EnumWriteSupport<Gender> {
}
