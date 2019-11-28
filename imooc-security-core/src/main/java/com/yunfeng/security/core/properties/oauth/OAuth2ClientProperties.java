package com.yunfeng.security.core.properties.oauth;
 
/**
 * oauth2客户端配置
 * @author CaiRui
 * @Date 2019-05-01 16:17
 */
public class OAuth2ClientProperties {
    private String clientId;
    private String clientSecret;
    private String[] authorizedGrantTypes = {};//授权类型
    private String[] redirectUris = {}; // 信任的回调域
    private String[] scopes = {};
    private int accessTokenValiditySeconds; // token有效期 默认单位秒
 
    public String getClientId() {
        return clientId;
    }
 
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
 
    public String getClientSecret() {
        return clientSecret;
    }
 
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
 
    public String[] getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }
 
    public void setAuthorizedGrantTypes(String[] authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }
 
    public String[] getRedirectUris() {
        return redirectUris;
    }
 
    public void setRedirectUris(String[] redirectUris) {
        this.redirectUris = redirectUris;
    }
 
    public String[] getScopes() {
        return scopes;
    }
 
    public void setScopes(String[] scopes) {
        this.scopes = scopes;
    }
 
    public int getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }
 
    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }
}