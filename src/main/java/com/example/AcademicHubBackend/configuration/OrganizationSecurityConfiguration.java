package com.example.AcademicHubBackend.configuration;

import com.example.AcademicHubBackend.Service.Implementation.AdminStudentInfoServiceImp;
import com.example.AcademicHubBackend.Service.Implementation.OrganizationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class OrganizationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private OrganizationUserService organizationUserService;

    @Autowired
    private AdminStudentInfoServiceImp adminStudentInfoServiceImp;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(organizationUserService);
//        auth.authenticationProvider(authProvider1()).authenticationProvider(authProvider2());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/**")
                .permitAll().anyRequest().authenticated();
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/**")
//                .permitAll()
////                .antMatchers("/api/v1/addItem").hasRole("ADMIN")
////                .antMatchers("/api/v3/addAddress","/api/v1/addItem").hasAuthority("USER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


//    @Bean
//    public DaoAuthenticationProvider authProvider1(){
//        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(organizationUserService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authProvider2(){
//        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(adminStudentInfoServiceImp);
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }
}
