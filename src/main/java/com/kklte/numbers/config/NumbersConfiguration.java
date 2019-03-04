package com.kklte.numbers.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class NumbersConfiguration {

    /**
     * Loads the number names as a properties beans.
     * @return
     */
    @Bean(name = "numberNames")
    public static PropertiesFactoryBean numberNames() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("number_names.properties"));
        return bean;
    }
}
