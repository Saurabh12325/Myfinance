package com.myfinance.Myfinance.Config;

import com.myfinance.Myfinance.Service.UserDetailService;
import com.myfinance.Myfinance.Util.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailService userDetailService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/status","/profile/register","/profile/activate","/profile/login").permitAll()
                                .anyRequest().authenticated())
                .userDetailsService(userDetailsService) // sirf login ke time use hoga
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();

    }
   @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }
   @Bean
    public CorsConfiguration corsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
       corsConfiguration.setAllowedOriginPatterns(List.of("*"));
       corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
       corsConfiguration.setAllowedHeaders(List.of("Authorization","Content-Type","Accept"));
       corsConfiguration.setAllowCredentials(true);
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**",corsConfiguration);
       return corsConfiguration;
   }

   @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
   }

}
