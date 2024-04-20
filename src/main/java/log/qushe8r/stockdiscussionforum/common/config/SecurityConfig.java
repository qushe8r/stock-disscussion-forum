package log.qushe8r.stockdiscussionforum.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import log.qushe8r.stockdiscussionforum.security.config.JwtSecurityDSL;
import log.qushe8r.stockdiscussionforum.security.handler.JwtAuthenticationEntryPoint;
import log.qushe8r.stockdiscussionforum.security.handler.JwtAuthenticationFailureHandler;
import log.qushe8r.stockdiscussionforum.security.handler.JwtLogoutHandler;
import log.qushe8r.stockdiscussionforum.security.jwt.JwtProcessor;
import log.qushe8r.stockdiscussionforum.security.utils.CookieCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final ObjectMapper objectMapper;
    private final JwtProcessor jwtProcessor;
    private final CookieCreator cookieCreator;
    private final JwtLogoutHandler jwtLogoutHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.with(JwtSecurityDSL.initialize(
                        objectMapper,
                        jwtProcessor,
                        cookieCreator,
                        jwtLogoutHandler,
                        jwtAuthenticationEntryPoint,
                        jwtAuthenticationFailureHandler),
                Customizer.withDefaults()
        ).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    static RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("""
                ROLE_ADMIN > ROLE_USER
                ROLE_USER > ROLE_INACTIVE
                """);
        return hierarchy;
    }
}
