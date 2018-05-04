package com.yuratrumpe.util;

import com.yuratrumpe.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextHelper {

    //public static ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    public static ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

}
