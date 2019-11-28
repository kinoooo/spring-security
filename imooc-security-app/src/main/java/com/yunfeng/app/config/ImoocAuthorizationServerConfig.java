package com.yunfeng.app.config;

import com.yunfeng.security.core.properties.SecurityProperties;
import com.yunfeng.security.core.properties.oauth.OAuth2ClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 认证服务器
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-26
 */

@Configuration
@EnableAuthorizationServer
public class ImoocAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * springboot2.0 无法直接注入
     */
    private final AuthenticationManager authenticationManager;


    public ImoocAuthorizationServerConfig(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //clients.inMemory()
        //        //        .withClient("imooc")
        //        //        .secret("imoocSecret")
        //        //        .accessTokenValiditySeconds(7200)
        //        //        .authorizedGrantTypes("refresh_token", "password")
        //        //        .scopes("all","read", "write")
        //        //        .redirectUris("http://example.com", "hlocalhost:80")
        //        //        .and()
        //        //        .withClient("myid2")
        //        //        .secret("myid2")
        //        //        .redirectUris("http://example.com", "localhost:8080")
        //        //        .authorizedGrantTypes("refresh_token", "password")
        //        //        .scopes("all", "read", "write");

        InMemoryClientDetailsServiceBuilder inMemory = clients.inMemory();
        OAuth2ClientProperties[] clientsInCustom = securityProperties.getOauth2().getClients();
        for (OAuth2ClientProperties p : clientsInCustom) {
            inMemory.withClient(p.getClientId())
                    .secret(p.getClientSecret())
                    .redirectUris(p.getRedirectUris())
                    .authorizedGrantTypes(p.getAuthorizedGrantTypes())
                    .accessTokenValiditySeconds(p.getAccessTokenValiditySeconds())
                    .scopes(p.getScopes());
        }

    }



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager)
                .userDetailsService(userDetailsService);
        if(jwtAccessTokenConverter != null && jwtAccessTokenConverter != null) {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);

            tokenEnhancerChain.setTokenEnhancers(enhancers);
            endpoints
                    .tokenEnhancer(tokenEnhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 这里使用什么密码需要 根据上面配置client信息里面的密码类型决定
        // 目前上面配置的是无加密的密码
        security.passwordEncoder(NoOpPasswordEncoder.getInstance());
    }


}
