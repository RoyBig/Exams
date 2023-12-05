package com.example.exams.SpringSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

    @Autowired
    UserDetailsService customUserDetailsService;

//    @Bean
//    public AuthenticationManager authenticationManager() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(customUserDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        List<AuthenticationProvider> providers = List.of(authProvider);
//        return new ProviderManager(providers);
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("1").password("{noop}1").roles("ADMIN")
                .and()
                .withUser("TestEgzaminator").password("{noop}testEgzaminator").roles("EXAMINER")
                .and()
                .withUser("TestStudent").password("{noop}testStudent").roles("STUDENT");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/css/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/images/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/register")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/testowo", true)
                        .loginProcessingUrl("/login")
                        .failureUrl("/login")
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .invalidSessionUrl("/#")
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                )
                .logout(logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login")
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID", "remember-me")
                                .addLogoutHandler((request, response, authentication) -> {
                                })
                                .permitAll()
                )
                .rememberMe(remember -> remember
                        .rememberMeParameter("remember-me")
                );
        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

}