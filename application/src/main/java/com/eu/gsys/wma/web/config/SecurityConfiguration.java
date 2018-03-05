package com.eu.gsys.wma.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/").permitAll().
				and().authorizeRequests().antMatchers("/console/**").permitAll();

		// Disable CSRF protection (Cross-Site Request Forgery)
		httpSecurity.csrf().disable();
		/*
			Disable X-Frame-Options in Spring Security
			When enabled adds the X-Frame-Options header to the response,
			this allows newer browsers to do some security checks and prevent clickjacking attacks.
		 */
		httpSecurity.headers().frameOptions().disable();
	}
}
