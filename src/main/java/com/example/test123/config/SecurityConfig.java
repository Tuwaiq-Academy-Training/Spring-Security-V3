package com.example.test123.config;


import com.example.test123.service.AuthService;
import com.example.test123.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private final MyUserDetailsService myUserDetailsService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(myUserDetailsService);
            authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
            return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain chain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/register").permitAll()
                .requestMatchers("/api/v1/auth/admin").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }


}
