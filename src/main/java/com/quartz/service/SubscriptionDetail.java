package com.quartz.service;

import org.springframework.stereotype.Service;

import com.quartz.model.SubscriptionResponseDTO;
import com.quartz.repo.SubscriptionRepo;
import com.razorpay.RazorpayClient;
import com.razorpay.Subscription;

@Service
public class SubscriptionDetail {
	private Object status;
	static String keyId = "rzp_live_0KlxeEsfpZArko";
	static String keySecret = "OXiHoD02B9nJQynqRT5g80Za";

	public static RazorpayClient razorpayClient() throws Exception {
		return new RazorpayClient(keyId, keySecret);
	}

	public void updateTheSubscriptionDeatil(Long userId, Long organizationId, String subscriptionId,
			SubscriptionRepo subscriptionRepo) {
		
		// Making Object of Model And Service Class
		SubscriptionResponseDTO subscriptionResponseDTO = new SubscriptionResponseDTO();
		TimeConversionService timeConversionService = new TimeConversionService();
		UpdateDeatilOfSubscription updateDeatilOfSubscription = new UpdateDeatilOfSubscription();

		RazorpayClient razorpayClient = null;
		try {
			razorpayClient = SubscriptionDetail.razorpayClient();
			Subscription subscription = razorpayClient.subscriptions.fetch(subscriptionId);
			// Set data SubscriptionDto Class(DATA TRANSFER OBJECT)

			subscriptionResponseDTO.setRazorPaySubscriptionId(subscriptionId);
			status = subscription.get("status");
			subscriptionResponseDTO
					.setSubscriptionStatusId(EnumMappingService.getSubscriptionStatusByString(status.toString()));

			subscriptionResponseDTO
					.setStartDate(timeConversionService.convertUnixTimeToDate(subscription.get("start_at")));

			subscriptionResponseDTO
					.setNextDueDate(timeConversionService.convertUnixTimeToDate(subscription.get("charge_at")));

			subscriptionResponseDTO.setEndDate(timeConversionService.convertUnixTimeToDate(subscription.get("end_at")));

			subscriptionResponseDTO.setSubscriptionLink(subscription.get("short_url"));

             /// update data in table
			updateDeatilOfSubscription.updateSubsceiption(userId, organizationId, subscriptionResponseDTO,
					subscriptionRepo);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
