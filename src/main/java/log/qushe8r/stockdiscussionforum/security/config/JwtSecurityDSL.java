package log.qushe8r.stockdiscussionforum.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import log.qushe8r.stockdiscussionforum.security.filter.JwtAuthenticationFilter;
import log.qushe8r.stockdiscussionforum.security.filter.JwtVerificationFilter;
import log.qushe8r.stockdiscussionforum.security.handler.JwtAuthenticationEntryPoint;
import log.qushe8r.stockdiscussionforum.security.handler.JwtAuthenticationFailureHandler;
import log.qushe8r.stockdiscussionforum.security.handler.JwtLogoutHandler;
import log.qushe8r.stockdiscussionforum.security.jwt.JwtProcessor;
import log.qushe8r.stockdiscussionforum.security.redis.TokenService;
import log.qushe8r.stockdiscussionforum.security.utils.CookieCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityDSL extends AbstractHttpConfigurer<JwtSecurityDSL, HttpSecurity> {
    private final ObjectMapper objectMapper;
    private final JwtProcessor jwtProcessor;
    private final CookieCreator cookieCreator;
    private final TokenService tokenService;
    private final JwtLogoutHandler jwtLogoutHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;

    @Override
    public void init(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll())
                .csrf(CsrfConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin))
                .logout(logout -> logout.logoutSuccessHandler(jwtLogoutHandler).logoutUrl("/api/logout"))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(handler -> handler.authenticationEntryPoint(jwtAuthenticationEntryPoint));
    }

    @Override
    public void configure(HttpSecurity builder) {
        AuthenticationManager authenticationManager =
                builder.getSharedObject(AuthenticationManager.class);
        JwtAuthenticationFilter jwtAuthenticationFilter =
                new JwtAuthenticationFilter(objectMapper, jwtProcessor, cookieCreator, tokenService);
        JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtProcessor);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/sign-in");
        jwtAuthenticationFilter.setAuthenticationFailureHandler(jwtAuthenticationFailureHandler);

        builder.addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(jwtVerificationFilter, JwtAuthenticationFilter.class);
    }

    public static JwtSecurityDSL initialize(
            ObjectMapper objectMapper,
            JwtProcessor jwtProcessor,
            CookieCreator cookieCreator,
            TokenService tokenService,
            JwtLogoutHandler jwtLogoutHandler,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler) {
        return new JwtSecurityDSL(objectMapper, jwtProcessor, cookieCreator, tokenService, jwtLogoutHandler,
                jwtAuthenticationEntryPoint, jwtAuthenticationFailureHandler);
    }


}
