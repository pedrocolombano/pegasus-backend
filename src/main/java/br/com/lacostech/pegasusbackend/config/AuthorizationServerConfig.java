package br.com.lacostech.pegasusbackend.config;

import br.com.lacostech.pegasusbackend.components.JwtTokenEnhancer;
import br.com.lacostech.pegasusbackend.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.List;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtAccessTokenConverter acessTokenConverter;
    private final JwtTokenStore tokenStore;
    private final JwtTokenEnhancer tokenEnhancer;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Value(value = "${security.oauth2.client.client-id}")
    private String clientId;

    @Value(value = "${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value(value = "${jwt.duration}")
    private Integer jwtDuration;

    public AuthorizationServerConfig(BCryptPasswordEncoder passwordEncoder,
                                     JwtAccessTokenConverter acessTokenConverter,
                                     JwtTokenStore tokenStore,
                                     JwtTokenEnhancer tokenEnhancer,
                                     AuthenticationManager authenticationManager,
                                     UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.acessTokenConverter = acessTokenConverter;
        this.tokenStore = tokenStore;
        this.tokenEnhancer = tokenEnhancer;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(jwtDuration)
                .refreshTokenValiditySeconds(jwtDuration);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain chain = new TokenEnhancerChain();
        chain.setTokenEnhancers(List.of(tokenEnhancer, acessTokenConverter));
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .accessTokenConverter(acessTokenConverter)
                .tokenEnhancer(chain)
                .userDetailsService(userService);
    }

}
