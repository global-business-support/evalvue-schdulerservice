package com.quartz.service;

import java.util.List;

import com.quartz.repo.IsPaidUpdateRepo;
import com.quartz.repo.PaymentRepo;
import com.quartz.repo.SubscriptionRepo;
import com.razorpay.RazorpayException;

public interface RazorPaySubcriptionIdInterface {

	public List<Object[]> getSubscriptionId();

	public void getInvoicesBySubscriptionId(String razorPaySubcriptionId, Long userId, Long organizationId,
			PaymentRepo paymentRepo, SubscriptionRepo subscriptionRepo,IsPaidUpdateRepo isPaidUpdateRepo) throws RazorpayException;

}
