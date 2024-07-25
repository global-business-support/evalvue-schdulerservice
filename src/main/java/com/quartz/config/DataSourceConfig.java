package com.quartz.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
@Configuration
@EnableAutoConfiguration
public class DataSourceConfig {

//	@Bean
//	public SchedulerFactoryBean schedulerFactoryBean() {
//		SchedulerFactoryBean factory = new SchedulerFactoryBean();
//		return factory;
//	}
	    @Bean
	    @QuartzDataSource
	    @ConfigurationProperties("spring.datasource.hikari")
	    public DataSource dataSource() {
	        return DataSourceBuilder.create().build();
	    }
////
//	    @Bean
//	    @ConfigurationProperties(prefix = "spring.quartz.properties")
//	    public Properties quartzProperties() {
//	        return new Properties();
//	    }

	    @Bean
	    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, Properties quartzProperties) {
	        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//	        factory.setDataSource(dataSource);
//	        factory.setQuartzProperties(quartzProperties);
	        return factory;
	    }
	
}

