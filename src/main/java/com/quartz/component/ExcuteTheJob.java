package com.quartz.component;

import java.sql.Timestamp;
import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.quartz.repo.IsPaidUpdateRepo;
import com.quartz.repo.PaymentRepo;
import com.quartz.repo.SubscriptionRepo;
import com.quartz.service.CheckDateTimeOfSubscription;
import com.quartz.service.RazorPaySubcriptionIdInterface;
import com.razorpay.RazorpayException;

@Component
public class ExcuteTheJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// getting job data Map
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();

		// Getting object from JobdataMap
		RazorPaySubcriptionIdInterface razorPaySubcriptionIdInterface = (RazorPaySubcriptionIdInterface) jobDataMap
				.get("RazorPaySubcriptionIdInterface");
		List<Object[]> list = razorPaySubcriptionIdInterface.getSubscriptionId();
		PaymentRepo paymentRepo = (PaymentRepo) jobDataMap.get("PaymentRepo");
		IsPaidUpdateRepo isPaidUpdateRepo = (IsPaidUpdateRepo) jobDataMap.get("IsPaidUpdateRepo");
		SubscriptionRepo subscriptionRepo = (SubscriptionRepo) jobDataMap.get("SubscriptionRepo");

		// creating object CheckDateTime Class
		CheckDateTimeOfSubscription checkDateTimeOfSubscription = new CheckDateTimeOfSubscription();
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			// Get detail of Subscription Table

			Object[] obj = list.get(i);
			String razorPaySubcriptionId = (String) obj[0];
			Timestamp subscriptionEndDate = (Timestamp) obj[1];
			Timestamp subscriptionNextDueDate = (Timestamp) obj[2];
			System.out.println(subscriptionNextDueDate);
			Long userId = (Long) obj[3];
			Long organizationId = (Long) obj[4];

			try {
				// Calling the Method CheckDateTimeOfSubscription Service

				checkDateTimeOfSubscription.checkTheExpireOfSubscription(razorPaySubcriptionId, subscriptionEndDate,
						userId, organizationId, subscriptionNextDueDate, paymentRepo, subscriptionRepo,
						isPaidUpdateRepo);

			} catch (RazorpayException e) {
				e.printStackTrace();
			}
		}
	}
}
