package com.naumen.films.application.security.config;

import com.naumen.films.application.controller.service.user_service.UserService;
import com.naumen.films.application.security.JwtUserDetailService;
import com.naumen.films.application.security.jwt.JwtConfigurer;
import com.naumen.films.application.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final DataSource dataSource;

    private static final String ADMIN_ENDPOINT = "/film_api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/film_api/v1/auth/login";
    private static final String REGISTER_ENDPOINT = "/film_api/v1/auth/register";
    private static final String START_URL = "/film_api/v1/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, DataSource dataSource) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.dataSource = dataSource;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
//        return new MyAuthenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT name, password, enabled FROM users WHERE name=?")
                .authoritiesByUsernameQuery(
                        "SELECT name, 'ROLE_USER' FROM users WHERE name=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(START_URL).permitAll()
                .antMatchers(REGISTER_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
