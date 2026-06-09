// package com.example.taskmanagementsystem.security;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.config.Customizer;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.http.HttpMethod;
// @Configuration
// @EnableWebSecurity
// public class Securityconfig
// {
//     private final JwtAuthenticationFilter jwtAuthenticationFilter;

//     public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//         this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//     }
//     @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {// this is main secuity filter  httpsecurity is a builder
//        System.out.println("SecurityConfig Loaded");
//     http
//         .csrf(csrf -> csrf.disable())
//         .authorizeHttpRequests(auth -> auth
//             .requestMatchers("/register").permitAll()
//             .requestMatchers("/public/**").permitAll()
//             .anyRequest().authenticated()
//         )
//         // .httpBasic(Customizer.withDefaults());//enable basic sercuity user/password popup
//           .sessionManagement(session -> session
//                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//define how spring handle session
//             )
//             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
 
//     return http.build();
// }
//     @Bean
//     public PasswordEncoder passwordEncoder()
//     {
//         return new BCryptPasswordEncoder();
//     }
//     // @Bean
//     // CommandLineRunner run(PasswordEncoder encoder)
//     // {
//     //     return args->{System.out.println(encoder.encode("aashi123"));};
//     // }
// }

package com.example.taskmanagementsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Securityconfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public Securityconfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        System.out.println("SecurityConfig Loaded");

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register").permitAll()
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


