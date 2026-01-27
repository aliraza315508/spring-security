package net.javaguides.spring_security.config;

import net.javaguides.spring_security.exception.CustomAccessDeniedHandler;
import net.javaguides.spring_security.exception.CustomAuthenticationEntryPoint;
import net.javaguides.spring_security.security.JwtAuthenticationEntryPoint;
import net.javaguides.spring_security.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService ;
    private JwtAuthenticationFilter jwtAuthenticationFilter ;


    public SpringSecurityConfig(UserDetailsService userDetailsService,
                                JwtAuthenticationFilter jwtAuthenticationFilter

    ) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter ;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager() ;
    }

    //Spring basic authentication and role based authentication form based authentication
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.GET, "/user", "/admin")
                .authenticated()
                .requestMatchers(HttpMethod.POST , "/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/welcome")
                .permitAll()
        );

        http.formLogin(form -> form.disable());
        http.httpBasic(basic ->
                basic.authenticationEntryPoint(new JwtAuthenticationEntryPoint()));

        http.addFilterBefore(jwtAuthenticationFilter , UsernamePasswordAuthenticationFilter.class) ;

        http.exceptionHandling
                (exception ->
                        exception.accessDeniedHandler(new CustomAccessDeniedHandler())) ;


        http.csrf(csrf -> csrf.disable()) ;
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user123"))
                .roles("USER")
                .build() ;

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build() ;

        return new InMemoryUserDetailsManager(user , admin) ;
    }
}

