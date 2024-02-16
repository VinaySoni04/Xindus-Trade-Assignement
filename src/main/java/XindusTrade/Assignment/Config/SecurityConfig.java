package XindusTrade.Assignment.Config;

import XindusTrade.Assignment.Services.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((csrf)->csrf.disable())
                .authorizeHttpRequests(auth->auth.requestMatchers("/api/wishlists/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/wishlists/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/wishlists/**"))
                .httpBasic(withDefaults())
                .formLogin(withDefaults());
        return httpSecurity.build();
    }
}
