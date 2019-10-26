/**
 * 
 */
package com.basic.framework.auth.config;

import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.basic.framework.auth.authorization.service.UserDetailsServiceImpl;

/**
 * @author gmc
 * @see 认证中心OAuth2配置
 */

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    RedisTokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Primary
    @Bean("jdbcTokenStore")
    public JdbcTokenStore getJdbcTokenStore() {
        return new JdbcTokenStore(datasource);
    }

    @Bean("jdbcClientDetailsService")
    public JdbcClientDetailsService getJdbcClientDetailsService() {
        return new JdbcClientDetailsService(datasource);
    }
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(new JdbcClientDetailsService(datasource));
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.allowFormAuthenticationForClients()
				// 开启/oauth/token_key验证端口无权限访问
				.checkTokenAccess("isAuthenticated()")
				// 开启/oauth/check_token验证端口认证权限访问
				.tokenKeyAccess("permitAll()");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(getJdbcTokenStore())
				.authenticationManager(authenticationManager)
				.tokenEnhancer(accessTokenConverter())
				.reuseRefreshTokens(false).userDetailsService(userDetailsService);
				// 配置TokenServices参数
		        DefaultTokenServices tokenServices = new DefaultTokenServices();
		        tokenServices.setTokenStore(endpoints.getTokenStore());
		        tokenServices.setSupportRefreshToken(true);
		        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
		        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
		        tokenServices.setAccessTokenValiditySeconds( (int) TimeUnit.HOURS.toSeconds(1)); // 1小时
		        tokenServices.setRefreshTokenValiditySeconds((int)TimeUnit.HOURS.toSeconds(12)); // 12小时
		        endpoints.tokenServices(tokenServices);
	}
	
	//对称性加密
	@Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("1q2w3e4rasdf");
        return converter;
    }
}
