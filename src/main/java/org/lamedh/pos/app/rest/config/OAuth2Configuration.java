//package org.lamedh.pos.app.rest.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//
//@Configuration
//@EnableResourceServer
//@EnableAuthorizationServer
//class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {
//
//    String applicationName = "pos";
//
//    @Autowired
//    AuthenticationManagerBuilder authenticationManager;
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(auth -> authenticationManager.getOrBuild().authenticate(auth));
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory().withClient("web-" + applicationName)
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .authorities("ROLE_USER").scopes("write").secret("123456");
//    }
//}
