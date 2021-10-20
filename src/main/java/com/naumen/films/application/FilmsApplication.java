package com.naumen.films.application;

import com.naumen.films.application.controller.AuthenticationController;
import com.naumen.films.application.controller.FilmController;
import com.naumen.films.application.controller.GenreController;
import com.naumen.films.application.dto.AuthenticationRequestDto;
import com.naumen.films.application.repository.FilmRepositoryImpl;
import com.naumen.films.application.repository.GenreRepositoryImpl;
import com.naumen.films.application.repository.RoleRepository;
import com.naumen.films.application.repository.UserRepository;
import com.naumen.films.application.security.JwtUserDetailService;
import com.naumen.films.application.security.config.SecurityConfig;
import com.naumen.films.application.security.jwt.JwtTokenProvider;
import com.naumen.films.application.security.jwt.JwtUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication(
		scanBasePackages={
				"com.naumen.films.application.repository",
				"com.naumen.films.application.security.jwt"
		}
)
@ComponentScan(basePackageClasses = {
		AuthenticationController.class,
		FilmController.class,
		GenreController.class,
		SecurityConfig.class,
		JwtTokenProvider.class,
		UserRepository.class,
		RoleRepository.class,
		JwtUserDetailService.class,
		GenreRepositoryImpl.class,
		FilmRepositoryImpl.class,
		AuthenticationRequestDto.class
} )
@EnableJpaRepositories("com.naumen")
public class FilmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmsApplication.class, args);
	}

}
