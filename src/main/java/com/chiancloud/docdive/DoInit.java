package com.chiancloud.docdive;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.chiancloud.docdive.config.ESConfig;
import com.chiancloud.docdive.dao.Dao;


public class DoInit implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//防止重复执行。
        if(event.getApplicationContext().getParent() == null){
        	try {
        		// 取到上下文中配置文件中的信息
        		ESConfig config = event.getApplicationContext().getBean(ESConfig.class);
        		Dao.init(config);
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
	}
}
