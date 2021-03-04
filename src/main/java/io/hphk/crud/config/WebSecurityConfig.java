package io.hphk.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/", "/posts", "/users/signup").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/users/login")
//            .loginProcessingUrl("/users/login") // 기본적으로 loginPage와 같은 URL 사용하도록 설정됨
            .defaultSuccessUrl("/posts")
            .permitAll()
            .and()
            .logout()
            .permitAll();
    }

}
