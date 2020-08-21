package com.bookstore.book.utils;

import com.bookstore.book.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//
//@Configuration
//@EnableWebSecurity
public class SecurityConfiguration  //extends WebSecurityConfigurerAdapter
 {

//    @Autowired
//    private UserService userService;
//    //a password encoder bean
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    //integrate jpa hibernate and the database with spring boot security
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider  auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(userService);
//        auth.setPasswordEncoder(passwordEncoder());
//        return auth;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers(
//                "/register**",
//                "/js/**",
//                "/css/**",
//                "/img/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/index")
//                .permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/Book/login?logout")
//                .permitAll();
//    }

}
