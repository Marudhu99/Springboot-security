package com.amingoscode.SpringSecurity.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.amingoscode.SpringSecurity.constants.ApplicationUserPermisson;
import com.amingoscode.SpringSecurity.constants.ApplicationUserRole;
import com.amingoscode.SpringSecurity.entity.Student;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	private PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/","index").permitAll()
		.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
//		.antMatchers(HttpMethod.GET,"/management/**").hasAnyRole(ApplicationUserRole.ADMIN.name(),ApplicationUserRole.ADMIN_TRAINEE.name())
//		.antMatchers(HttpMethod.DELETE,"/management/**").hasAuthority(ApplicationUserPermisson.COURSE_WRITE.getPermission())
//		.antMatchers(HttpMethod.PUT,"/management/**").hasAuthority(ApplicationUserPermisson.COURSE_WRITE.getPermission())
//		.antMatchers(HttpMethod.POST,"/management/**").hasAuthority(ApplicationUserPermisson.COURSE_WRITE.getPermission())
		.anyRequest()
		.authenticated()
		.and()
//		.httpBasic();
		.formLogin() //Cookie SESSIONID , Expires after 30minutes of inactivity
		    .loginPage("/login").permitAll()
		    .defaultSuccessUrl("/course",true)
		    .usernameParameter("username")
		    .passwordParameter("password")
		.and()
		.rememberMe() // for 2 weeks-remember me cookie(default)
		    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) 
		    .key("somethingkeysecured")
		    .rememberMeParameter("remember-me")
		.and()
		.logout()
		   .logoutUrl("/logout")
		   .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET")) //this line using when csrf disable
		   .clearAuthentication(true)
		   .invalidateHttpSession(true)
		   .deleteCookies("JESSIONID","remember-me")
		   .logoutSuccessUrl("/login"); 
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {

		UserDetails marudhuUser = User.builder()
				.username("Marudhu")
				.password(passwordEncoder.encode("Marudhu1@"))
//				.roles(ApplicationUserRole.STUDENT.name()) //ROLE_STUDENT
				.authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
				.build();

		UserDetails jayarajAdmin = User.builder()
				.username("Jayaraj")
				.password(passwordEncoder.encode("BCT"))
//		        .roles(ApplicationUserRole.ADMIN.name()) //ROLE_ADMIN
				.authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
				.build();

		UserDetails ayyappanUser = User.builder()
				.username("Ayyappan")
				.password(passwordEncoder.encode("computerScience"))
//				.roles(ApplicationUserRole.ADMIN_TRAINEE.name()) //ROLE_ADMINTRAINEE
				.authorities(ApplicationUserRole.ADMIN_TRAINEE.getGrantedAuthorities())
				.build();

		return new InMemoryUserDetailsManager(marudhuUser,jayarajAdmin,ayyappanUser);
	}

}
