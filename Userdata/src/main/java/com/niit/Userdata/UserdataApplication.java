package com.niit.Userdata;

import com.niit.Userdata.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
public class UserdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserdataApplication.class, args);
	}
//	@Bean
//	public FilterRegistrationBean jwtFilter()
//	{
//		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//		filterRegistrationBean.setFilter(new JwtFilter());
//		filterRegistrationBean.addUrlPatterns("/api/v2/*");
//		return filterRegistrationBean;
//
//	}

}
