package com.quartz.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.repo.IsPaidUpdateRepo;
import com.quartz.repo.PaymentRepo;
import com.quartz.repo.SubscriptionRepo;
import com.razorpay.Invoice;
import com.razorpay.RazorpayClient;

@Service
public class SubscriptionIdService implements RazorPaySubcriptionIdInterface {

	@Autowired
	SubscriptionRepo subscriptionRepo;

	static String keyId = "rzp_test_mHIc2FsOxWbBD7";
	static String keySecret = "f8cWeZ5ImalzggaExivDZPT7";

	public static RazorpayClient razorpayClient() throws Exception {
		return new RazorpayClient(keyId, keySecret);
	}

	@Override
	public List<Object[]> getSubscriptionId() {

		return subscriptionRepo.getSubscriptionId();

	}

	@Override
	public void getInvoicesBySubscriptionId(String subscriptionId, Long userId, Long organizationId,
			PaymentRepo paymentRepo, SubscriptionRepo subscriptionRepo, IsPaidUpdateRepo isPaidUpdateRepo) {
		
		UpdateDeatilOfPaymentWithInvoiceService updateDeatilOfPaymentWithInvoiceService = new UpdateDeatilOfPaymentWithInvoiceService();
		//create json object put Subscription Id in it.
		JSONObject jSONObject = new JSONObject();
		jSONObject.put("subscription_id", subscriptionId);

		try {
			// payment sub service
			RazorpayClient razorpayClient = SubscriptionIdService.razorpayClient();
			List<Invoice> response = razorpayClient.invoices.fetchAll(jSONObject);
			Invoice invoice = response.get(0);

			updateDeatilOfPaymentWithInvoiceService.updateDeatilWithInovice(invoice, userId, organizationId,
					paymentRepo, subscriptionRepo, isPaidUpdateRepo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
