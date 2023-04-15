package backendLastProject.GamesApp;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig  {
	
    @Autowired
    private UserDetailsService userDetailsService;	
    
    
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests()
        	.requestMatchers("/register").anonymous()
        //	.requestMatchers("/css/**").permitAll() 
        	.requestMatchers("/gamelist").permitAll()
        	.requestMatchers(toH2Console()).hasAuthority("ADMIN")
        	.requestMatchers("/api/**").hasAuthority("ADMIN")
        	.anyRequest().authenticated()
        	.and()
        	  .csrf().ignoringRequestMatchers(toH2Console())
        	  .and()
        	  .headers().frameOptions().disable()
        	  .and()
      .formLogin()
      		.loginPage("/login")
          .defaultSuccessUrl("/gamelist", true)
          .permitAll()
          .and()
      .logout()
          .logoutUrl("/logout")
          .logoutSuccessUrl("/login?logout")
          .invalidateHttpSession(true)
          .deleteCookies("JSESSIONID")
          .and()
      .httpBasic();
      return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
