package com.quartz.service;

import org.springframework.stereotype.Service;

import com.quartz.model.SubscriptionResponseDTO;
import com.quartz.repo.SubscriptionRepo;

@Service
public class UpdateDeatilOfSubscription {

	public void updateSubsceiption(Long userId, Long organizationId,
			SubscriptionResponseDTO subscriptionResponseDTO, SubscriptionRepo subscriptionRepo) {
		subscriptionRepo.updateSubscriptionDetails(subscriptionResponseDTO.getNextDueDate(), 
				subscriptionResponseDTO.getSubscriptionStatusId(),
				subscriptionResponseDTO.getRazorPaySubscriptionId(),
				userId, 
				organizationId);
		
	}

}
