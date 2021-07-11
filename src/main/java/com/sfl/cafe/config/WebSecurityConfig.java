package com.sfl.cafe.config;


import com.sfl.cafe.handler.CustomAccessDeniedHandler;
import com.sfl.cafe.model.enums.UserType;
import com.sfl.cafe.security.CurrentUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CurrentUserDetailServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/manager/*").hasAnyAuthority(UserType.MANAGER.name())
                .antMatchers("/waiter/*").hasAnyAuthority(UserType.WAITER.name())

                .anyRequest().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/403.jsp")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())

                .and()
                .formLogin()
                .loginPage("/home")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/login-success")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/home")
                .and()
                .rememberMe()
                .tokenValiditySeconds(2*604800) // 1 week
                .and();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
}
