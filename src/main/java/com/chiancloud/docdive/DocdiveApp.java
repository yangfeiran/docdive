package com.chiancloud.docdive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.chiancloud.docdive.config.ESConfig;
import com.chiancloud.docdive.config.FileSaveConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;

@SpringBootApplication
@EnableSwagger
@EnableAutoConfiguration
@EnableConfigurationProperties({ FileSaveConfig.class, ESConfig.class})
public class DocdiveApp extends SpringBootServletInitializer{
	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DocdiveApp.class);
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DocdiveApp.class);
		app.addListeners(new DoInit());
		app.run(args);
	}

}
