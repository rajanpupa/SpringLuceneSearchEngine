package com.gazecode.zzzexperiment.dependency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import com.gazecode.config.Configuration;

public class DependencyCheck {
	public static void main(String[] args) {
		try{
			System.out.println("Working Directory = " +
		              System.getProperty("user.dir"));
			
			ApplicationContext context = 
					new ClassPathXmlApplicationContext("spring-beans.xml");
			Configuration bean1 = (Configuration)context.getBean("configuration");
			
			System.out.println("Index directory : "+bean1.getProperty("INDEX_DIR"));
			System.out.println("TEST : "+bean1.getProperty("TEST"));
			
		} catch (Exception el) {
			System.out.println("" + el);
		}
	}
}
