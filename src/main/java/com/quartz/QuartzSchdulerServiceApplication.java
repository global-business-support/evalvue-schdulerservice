package com.quartz;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.quartz.component.EntryOfSchduler;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.quartz.*")
public class QuartzSchdulerServiceApplication {

	public static void main(String[] args) throws SchedulerException {
	ApplicationContext context=	SpringApplication.run(QuartzSchdulerServiceApplication.class, args);
	EntryOfSchduler entryOfSchduler =context.getBean(EntryOfSchduler.class);
	entryOfSchduler.start();
	}

}
