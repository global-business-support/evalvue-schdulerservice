package com.quartz.service;

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.quartz.model.PaymentEntityDTO;
import com.quartz.repo.IsPaidUpdateRepo;
import com.quartz.repo.PaymentRepo;
import com.quartz.repo.SubscriptionRepo;
import com.razorpay.Invoice;

@Service
public class UpdateDeatilOfPaymentWithInvoiceService {

	public void updateDeatilWithInovice(Invoice invoice, Long userId, Long organizationId, PaymentRepo paymentRepo,
			SubscriptionRepo subscriptionRepo, IsPaidUpdateRepo isPaidUpdateRepo) throws JSONException, ParseException {

		PaymentEntityDTO paymentEntityDTO = PaymentEntityDTO.getInstance();
		PaymentDetailService paymentDetailService = new PaymentDetailService();
		JSONObject invoiceJson = invoice.toJson();

		// Check the Payment status
		if (invoiceJson.getString("status").equals("paid")) {

			// Set data in PayementDTO from Invoice
			paymentEntityDTO.setAmount((int) (invoiceJson.getDouble("amount") / 100));
			paymentEntityDTO.setRazorpayPaymentId(invoiceJson.getString("payment_id"));
			paymentEntityDTO.setRazorPaySubscriptionId(invoiceJson.getString("subscription_id"));
			paymentEntityDTO.setRazorpayOrderId(invoiceJson.getString("order_id"));
			paymentEntityDTO.setOrganizationId(organizationId);
			paymentEntityDTO.setUserId(userId);
			try {
				// set data in payemntDTO
				paymentDetailService.getPaymentDeatilStore(paymentEntityDTO.getRazorpayPaymentId(), paymentEntityDTO,
						paymentRepo);
				// Update ispaid
				isPaidUpdateRepo.updateIsPaid(organizationId, userId, true);

				System.out.println(paymentEntityDTO);
			} catch (Exception e) {

				e.printStackTrace();
			}
			SubscriptionDetail subscriptionDetail = new SubscriptionDetail();
			String SubscriptionId = paymentEntityDTO.getRazorPaySubscriptionId();
			subscriptionDetail.updateTheSubscriptionDeatil(userId, organizationId, SubscriptionId, subscriptionRepo);

		} else {
			isPaidUpdateRepo.updateIsPaid(organizationId, userId, false);
		}
	}
}
