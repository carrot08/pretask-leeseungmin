package com.musinsa.admin.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan({"com.musinsa.core", "com.musinsa.admin"})
@EnableJpaRepositories({"com.musinsa.core", "com.musinsa.admin"})
@EnableJpaAuditing
public class JpaConfig {
}
