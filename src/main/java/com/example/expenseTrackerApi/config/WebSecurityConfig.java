package com.example.expenseTrackerApi.config;

import com.example.expenseTrackerApi.security.CustomUserDetailsService;
import com.example.expenseTrackerApi.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig{

    @Autowired
    private CustomUserDetailsService userDetailsService;

//    	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http
//			.csrf().disable()
//			.authorizeRequests()
//			.antMatchers("/login", "/register").permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//		http.httpBasic();
//
//	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//		.csrf().disable()
//		.authorizeHttpRequests()
//		.requestMatchers("/login", "/register").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//		http.httpBasic();
//		return http.build();

//        If you don't disable CSRF (Cross-Site Request Forgery) protection in a Spring Security configuration for a stateless API, it can lead to issues, as CSRF protection is typically designed for stateful web applications
//        CSRF protection helps prevent malicious attacks where unauthorized commands are transmitted from a user that the web application trusts. This protection mechanism is typically used to secure stateful sessions where cookies are used for authentication.
//        if you are using csrf then verytime making req you need to provide the csrf token otherwise req will be rejected
        return http.csrf(csrf -> csrf.disable()).
//       The requestMatchers method specifies URL patterns (/login, /register, and /) that are accessible to all users without authentication (permitAll).
//       All other requests (anyRequest()) require authentication (authenticated).
                authorizeHttpRequests(x -> x.requestMatchers("/login", "/register", "/").
                        permitAll().anyRequest().authenticated()).
//      This setting ensures that the server does not store any session information.
//      Each request from the client must include all necessary information for authentication and authorization i.e everytime you should give jwt token
                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
//      By adding the custom JWT authentication filter before the UsernamePasswordAuthenticationFilter,
//      you ensure that JWT tokens are checked and validated early in the request processing pipeline.
//      This setup allows authenticated requests to proceed without needing standard username/password authentication,
//      If the token is invalid or missing, the request is either rejected or proceeds to the standard authentication mechanisms (e.g., username/password).
                addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class).
//      Basic HTTP authentication is enabled with default settings. This is a simple way to authenticate users by prompting for a username and password.
//      if you don't write Basic authentication and have (such as JWT, OAuth2, etc.) configured, the application will rely solely on those for authenticating requests.
                httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

//    	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}
//
//
//
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//
//		return super.authenticationManagerBean();
//	}
}