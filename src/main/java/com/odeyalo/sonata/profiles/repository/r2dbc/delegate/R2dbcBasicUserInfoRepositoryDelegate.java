package com.odeyalo.sonata.profiles.repository.r2dbc.delegate;

import com.odeyalo.sonata.profiles.entity.BasicUserInfo;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface R2dbcBasicUserInfoRepositoryDelegate extends R2dbcRepository<BasicUserInfo, Long> {


}
