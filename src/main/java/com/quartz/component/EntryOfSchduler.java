package com.quartz.component;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class EntryOfSchduler {
	@Autowired
	public JobDeatilAndTrigger jobDeatilAndTrigger;
	@Autowired
	SchedulerFactoryBean schedulerFactory;


	public void start() throws SchedulerException {

		JobDetail jobDetail = jobDeatilAndTrigger.getJobDetail();
		Trigger trigger = jobDeatilAndTrigger.getTrigger();

		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobDetail, trigger);
	}
}
