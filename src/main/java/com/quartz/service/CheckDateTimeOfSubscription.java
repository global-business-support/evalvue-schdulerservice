package com.quartz.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.quartz.repo.IsPaidUpdateRepo;
import com.quartz.repo.PaymentRepo;
import com.quartz.repo.SubscriptionRepo;
import com.razorpay.RazorpayException;

@Service
public class CheckDateTimeOfSubscription {

	public void checkTheExpireOfSubscription(String razorPaySubcriptionId, Timestamp subcriptionEndDate, Long userId,
			Long organizationId, Timestamp subscriptionNextDueDate, PaymentRepo paymentRepo,
			SubscriptionRepo subscriptionRepo, IsPaidUpdateRepo isPaidUpdateRepo) throws RazorpayException {
		
		// Create CurrentLocal Time For comparison And Object
		LocalDateTime currentDateTime = LocalDateTime.now();
		SubscriptionIdService subscriptionIdService = new SubscriptionIdService();

		if (subcriptionEndDate != null && subscriptionNextDueDate != null) {

			// Convert Timestamp subcriptionEndDate , subscriptionNextDueDate to
			// LocalDateTime
			LocalDateTime localTimeSubcriptionEndDate = subcriptionEndDate.toLocalDateTime();
			LocalDateTime localSubscriptionNextDueDate = subscriptionNextDueDate.toLocalDateTime();

			System.out.println(localTimeSubcriptionEndDate);
			if (localTimeSubcriptionEndDate.isAfter(currentDateTime)) {

				System.out.println(localSubscriptionNextDueDate);
				if (currentDateTime.isAfter(localSubscriptionNextDueDate)) {

					subscriptionIdService.getInvoicesBySubscriptionId(razorPaySubcriptionId, userId, organizationId,
							paymentRepo, subscriptionRepo, isPaidUpdateRepo);
				}

			}  else {
				
				byte isPaid = 0;
				isPaidUpdateRepo.updateIsPaid(userId, organizationId, isPaid);
			}
		} else {
			
			byte isPaid = 0;
			isPaidUpdateRepo.updateIsPaid(userId, organizationId, isPaid);
		}

	}

}
