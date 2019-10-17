package hu.elte.kandras.spring.webapp.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Value("${app.environment}")
    private String environment;

    @Value("${app.api.secure}")
    private String secureApi;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] generatedFiles = {
                "/**.js",
                "/fontawesome-*",
                "/glyphicons-*",
                "/bootstrap*",
                "/favicon.ico",
                "/**.jpg",
                "/**.png",
                "/**/**.svg",
                "/**/**.css",
                "/**/**.json"
        };
        String[] unAuthorized = new String[0];
        if ("false".equals(secureApi)) {
            unAuthorized = new String[]{
                    "/api/persons/**",
                    "/h2-console/**"
            };
        }
        http.cors().and().csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED)))
                .and()
                .authorizeRequests()
                .antMatchers("/", "/api/auth/**").permitAll()
                .antMatchers(unAuthorized).permitAll()
                .antMatchers(generatedFiles).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/api/auth/login").permitAll()
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout().logoutUrl("/api/auth/logout").clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .addLogoutHandler((request, response, authentication) -> logger.info("User successfully logged out, name: {}", authentication.getName()))
                .permitAll();

        // For h2-console
        http.headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        logger.debug("Configure in memory authentication");
        auth
                .inMemoryAuthentication()
                .withUser("student1").password("{noop}" + "student1").authorities("ROLE_STUDENT");
        auth
                .inMemoryAuthentication()
                .withUser("student2").password("{noop}" + "student2").authorities("ROLE_STUDENT");
        auth
                .inMemoryAuthentication()
                .withUser("instructor1").password("{noop}" + "instructor1").authorities("ROLE_INSTRUCTOR");
        auth
                .inMemoryAuthentication()
                .withUser("instructor2").password("{noop}" + "instructor2").authorities("ROLE_INSTRUCTOR");
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        if ("local".equals(environment)) {
            configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:4201"));
            configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
            configuration.setAllowCredentials(true);
            configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "Origin", "Accept"));
        }
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
