package com.quartz.component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.quartz.repo.IsPaidUpdateRepo;
import com.quartz.repo.PaymentRepo;
import com.quartz.repo.SubscriptionRepo;
import com.quartz.service.RazorPaySubcriptionIdInterface;

@Component
public class JobDeatilAndTrigger {
	@Autowired
	RazorPaySubcriptionIdInterface razorPaySubcriptionIdInterface;
	@Autowired
	@Lazy
	JobDeatilAndTrigger jobDeatilAndTrigger;
	@Autowired
	PaymentRepo paymentRepo;
	@Autowired
	SubscriptionRepo subscriptionRepo;
	@Autowired
	IsPaidUpdateRepo isPaidUpdateRepo;

	public JobDetail getJobDetail() {
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("RazorPaySubcriptionIdInterface", razorPaySubcriptionIdInterface);
		jobDataMap.put("PaymentRepo", paymentRepo);
		jobDataMap.put("SubscriptionRepo", subscriptionRepo);
		jobDataMap.put("IsPaidUpdateRepo", isPaidUpdateRepo);
		JobDetail jobDetail = JobBuilder.newJob(ExcuteTheJob.class).withIdentity("ExcuteTheJob", "group1")
				.usingJobData(jobDataMap).storeDurably(true).build();
		return jobDetail;
	}

	public Trigger getTrigger() {
		 String cronExpression = "0 0 0 * * ?";

	        // Setting start time to the next occurrence of 12 AM
	        ZonedDateTime startTime = ZonedDateTime.now()
	            .withHour(0)
	            .withMinute(0)
	            .withSecond(0)
	            .withNano(0)
	            .plusDays(1);  // Ensures the start time is the next midnight
	        Date startDate = Date.from(startTime.toInstant());

	        Trigger trigger = TriggerBuilder.newTrigger()
	            .forJob(jobDeatilAndTrigger.getJobDetail()) // Assuming jobDetailAndTrigger is already defined
	            .withIdentity("printNameTrigger", "group1")
	            .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)
	                .inTimeZone(TimeZone.getTimeZone("Asia/Kolkata"))
	                .withMisfireHandlingInstructionFireAndProceed())
	            .startAt(startDate)
	            .build();


//		String cronExpression = "0 0/1 * * * ?";
//
//		// Set the start time to the next minute with seconds and nano seconds as 0
//		ZonedDateTime startTime = ZonedDateTime.now().withSecond(0).withNano(0).plusMinutes(1);
//		Date startDate = Date.from(startTime.toInstant());
//
//		// Build the trigger with the given cron expression and start time
//		Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDeatilAndTrigger.getJobDetail())
//				.withIdentity("printNameTrigger", "group1")
//				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)
//						.inTimeZone(TimeZone.getTimeZone("Asia/Kolkata"))
//						.withMisfireHandlingInstructionFireAndProceed())
//				.startAt(startDate).build();
		return trigger;
	}

}
