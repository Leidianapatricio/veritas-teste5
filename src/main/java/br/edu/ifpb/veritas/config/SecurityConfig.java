package br.edu.ifpb.veritas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // ROTAS PÚBLICAS
                .requestMatchers("/", "/home", "/login", "/error", "/css/**", "/js/**", "/images/**").permitAll()

                // ROTAS PROTEGIDAS
                .requestMatchers("/dashboard/**").authenticated()

                // QUALQUER OUTRA ROTA PRECISA ESTAR LOGADA
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")     // página customizada
                .usernameParameter("email") // nome do input do username
                .passwordParameter("senha") // nome do input da senha
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/home")
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/home")
            );

        return http.build();
    }
}
