package com.yuratrumpe.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextHelper {

    public static ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
}
