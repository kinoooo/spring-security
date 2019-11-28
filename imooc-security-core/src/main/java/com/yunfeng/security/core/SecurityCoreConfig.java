package com.yunfeng.security.core;

import com.yunfeng.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-12
 */

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
