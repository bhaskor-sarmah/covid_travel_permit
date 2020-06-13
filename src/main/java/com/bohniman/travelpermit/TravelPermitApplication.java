package com.bohniman.travelpermit;

import com.bohniman.travelpermit.captcha.CaptchaDetailsSource;
import com.bohniman.travelpermit.captcha.CaptchaGenerator;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TravelPermitApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TravelPermitApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("admin"));
		return bCryptPasswordEncoder;
	}

	@Bean
	public CaptchaGenerator getCaptchaGenerator() {
		return new CaptchaGenerator();
	}

	@Bean
	public CaptchaDetailsSource getCaptchaDetailsSource() {
		return new CaptchaDetailsSource();
	}
}
