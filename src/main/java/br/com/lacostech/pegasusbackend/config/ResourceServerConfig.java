package br.com.lacostech.pegasusbackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String TEST_PROFILE = "test";

    private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**" };
    private static final String[] USER_API = { "/users/**" };
    private static final String[] CATALOG_API = { "/categories/**", "/products/**" };
    private static final String[] BLOG_API = { "/posts/**", "/themes/**" };
    private static final String BREED_API = "/breeds/**";

    private static final String ADMIN = "ADMIN";
    private static final String MANAGER = "MANAGER";
    private static final String MODERATOR = "MODERATOR";

    @Value("${spring.profiles.active}")
    private String profile;

    private final TokenStore tokenStore;

    public ResourceServerConfig(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if (TEST_PROFILE.equals(profile)) {
            http.headers()
                    .frameOptions()
                    .disable();
        }
        http.authorizeRequests()
                .antMatchers(PUBLIC)
                .permitAll()
                .antMatchers(HttpMethod.POST, USER_API)
                .permitAll()
                .antMatchers(USER_API)
                .authenticated()
                .antMatchers(HttpMethod.GET, CATALOG_API)
                .permitAll()
                .antMatchers(CATALOG_API)
                .hasAnyRole(ADMIN, MANAGER)
                .antMatchers(HttpMethod.GET, BLOG_API)
                .permitAll()
                .antMatchers(BLOG_API)
                .hasAnyRole(ADMIN, MANAGER, MODERATOR)
                .antMatchers(HttpMethod.GET, BREED_API)
                .permitAll()
                .antMatchers(BREED_API)
                .hasAnyRole(ADMIN, MANAGER)
                .anyRequest()
                .authenticated();
        http.cors()
                .configurationSource(getCorsConfigurationSource());
    }

    @Bean
    public CorsConfigurationSource getCorsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOriginPatterns(List.of("*"));
        corsConfig.setAllowedMethods(List.of("POST", "GET", "PUT", "DELETE", "PATCH"));
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> getCorsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(getCorsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
