package com.imocha.lms.common.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	Map<String, AuthenticationManager> authenticationManagers = new HashMap<>();

	JwtIssuerAuthenticationManagerResolver authenticationManagerResolver = new JwtIssuerAuthenticationManagerResolver(
			authenticationManagers::get);

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/", "/swagger-ui*/**", "/v3/api-docs*/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		List<String> issuers = new ArrayList<>();
		issuers.add("https://d1isjhghszo6bn.cloudfront.net/auth/realms/feng-test");

		issuers.stream().forEach(issuer -> addManager(authenticationManagers, issuer));

		http.cors().and().authorizeRequests(authz -> authz.anyRequest().authenticated()).oauth2ResourceServer(
				customizer -> customizer.authenticationManagerResolver(this.authenticationManagerResolver));
	}

	public void addManager(Map<String, AuthenticationManager> authenticationManagers, String issuer) {
		JwtAuthenticationProvider authenticationProvider = new JwtAuthenticationProvider(
				JwtDecoders.fromOidcIssuerLocation(issuer));
		authenticationProvider.setJwtAuthenticationConverter(getJwtAuthenticationConverter());
		authenticationManagers.put(issuer, authenticationProvider::authenticate);
	}

	private Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
		JwtAuthenticationConverter conv = new JwtAuthenticationConverter();

		conv.setJwtGrantedAuthoritiesConverter(jwt -> {

			Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
			if (realmAccess == null) {
				return new ArrayList<>();
			}

			List<String> roles = (List<String>) realmAccess.get("roles");
			return roles.stream().map(r -> "ROLE_" + r).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		});
		return conv;
	}
}
