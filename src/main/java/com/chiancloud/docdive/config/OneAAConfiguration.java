package com.chiancloud.docdive.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.chinacloud.mir.common.audit.AuditLogger;
import com.chinacloud.mir.common.service.AuthFilter;

@ComponentScan(basePackages = { "com.chinacloud.mir.common" })
@Configuration
@ImportResource("classpath*:one-auth-bean.xml")
public class OneAAConfiguration implements ServletContextInitializer {
	
	@Autowired
	AuthFilter authFilter;

	@Bean
	public AuditLogger auditLogger() {
		return new AuditLogger();
	}

	@Bean
	public FilterRegistrationBean delegateFilter(ServletContext servletContext)
			throws ServletException {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(authFilter);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/v1/*");
		return filterRegistrationBean;
	}

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		//delegateFilter(servletContext);
	}

}
