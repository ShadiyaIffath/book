package com.bookstore.book.utils;

import com.bookstore.book.utils.security.jwt.AuthEntryPointJwt;
import com.bookstore.book.utils.security.jwt.AuthTokenFilter;
import com.bookstore.book.utils.security.payload.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity //gives spring access to apply the class to security layer
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
 {

     @Autowired
     private UserDetailsServiceImpl userDetailsService;
     @Autowired
     private AuthEntryPointJwt unauthorizedHandler;
     @Autowired
     private AuthTokenFilter filter;
    //a password encoder bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

     @Override
     public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
         authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
     }
     @Bean
     @Override
     public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
     }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers(
                "/register**","/**","**/error**","/books**", "/resources**","/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

}
