package com.walterio12345.springwithjwtandtoken.security;

import com.walterio12345.springwithjwtandtoken.filter.CustomAuthorizationFilter;
import com.walterio12345.springwithjwtandtoken.filter.CustonAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService) .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustonAuthenticationFilter custonAuthenticationFilter=new CustonAuthenticationFilter(authenticationManagerBean());
        custonAuthenticationFilter.setFilterProcessesUrl("/api/login");
       http.csrf().disable();
       http.sessionManagement().sessionCreationPolicy(STATELESS);
       http.authorizeRequests().antMatchers("/api/login/**" , "/api/token/refresh/**").permitAll();
       http.authorizeRequests().antMatchers(GET ,"api/Users/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(POST ,"api/User/save/**").hasAnyAuthority("ROLE_ADMIN");
       http.authorizeRequests().anyRequest().authenticated();
       http.addFilter(custonAuthenticationFilter);
       http.addFilterBefore( new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Configuration
    public class Encoder {
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
}

}
