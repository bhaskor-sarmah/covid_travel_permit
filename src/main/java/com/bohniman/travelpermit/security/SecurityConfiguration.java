package com.bohniman.travelpermit.security;

import javax.sql.DataSource;

import com.bohniman.travelpermit.captcha.CaptchaAuthenticationProvider;
import com.bohniman.travelpermit.captcha.CaptchaDetailsSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SecurityConfiguration
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // @Autowired
    // UserDetailsService userDetailsService;

    @Autowired
    CaptchaAuthenticationProvider authenticationProvider;

    @Autowired
    CaptchaDetailsSource detailsSource;

    @Autowired
    DataSource dataSource;

    // private static Logger log =
    // LoggerFactory.getLogger(SecurityConfiguration.class);

    // ========================================================================
    // # Custom Authentication
    // ========================================================================
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] unloggedUrls = { "/", "/login", "/no-role", "/genCaptcha.png" };

        http.authorizeRequests().antMatchers(unloggedUrls).permitAll().antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/district/**").hasAuthority("DISTRICT").anyRequest().authenticated().and().csrf()
                .disable().formLogin().loginPage("/login").authenticationDetailsSource(detailsSource)
                .successHandler(successHandler()).failureHandler(loginFailureHandler()).and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
                .exceptionHandling().accessDeniedPage("/access-denied").and().httpBasic().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/common/**", "/plugins/**", "/images/**");
    }

    @Bean
    public UrlAuthenticationSuccessHandler successHandler() {
        return new UrlAuthenticationSuccessHandler();
    }

    private AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            System.out.println(exception.getMessage());
            response.sendRedirect("/login?error=" + exception.getMessage());
        };
    }
}