package com.chiancloud.docdive;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.chiancloud.docdive.config.ESConfig;

@ComponentScan
@Configuration
public class Init2 implements ServletContextInitializer {
	
	@Autowired
	ESConfig config;

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		System.out.println("****do init...****");
		System.out.println(JSON.toJSON(config));
	}

}
