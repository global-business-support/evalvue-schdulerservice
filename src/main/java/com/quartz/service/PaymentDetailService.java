package com.quartz.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.quartz.model.PaymentEntityDTO;
import com.quartz.repo.PaymentRepo;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;

@Service
public class PaymentDetailService {
	static String keyId = "rzp_test_mHIc2FsOxWbBD7";
	static String keySecret = "f8cWeZ5ImalzggaExivDZPT7";
	int paymentStatusId;

	public static RazorpayClient razorpayClient() throws Exception {
		return new RazorpayClient(keyId, keySecret);
	}

	public void getPaymentDeatilStore(String paymentId, PaymentEntityDTO paymentEntityDTO, PaymentRepo paymentRepo) throws Exception {
		RazorpayClient razorpayClient = PaymentDetailService.razorpayClient();
		Payment razorPayPayment = razorpayClient.payments.fetch(paymentId);

		// Set Data in PaymentEntity

		Boolean captured = razorPayPayment.get("captured");
		String status = razorPayPayment.get("status");
		paymentStatusId = EnumMappingService.getPaymentStatus(captured, status);
		paymentEntityDTO.setPaymentStatusId(paymentStatusId);
		paymentEntityDTO.setAmount(razorPayPayment.get("amount"));
		paymentEntityDTO.setPaymentMode(razorPayPayment.get("method"));
		paymentEntityDTO.setUserEmail(razorPayPayment.get("email"));
		paymentEntityDTO.setContact(razorPayPayment.get("contact"));
		paymentEntityDTO.setCreatedOn(razorPayPayment.get("created_at"));
		JSONObject transecationId = razorPayPayment.get("acquirer_data");
		
		// get Transaction Id
		Object rrn = transecationId.get("rrn");
		if (!rrn.equals(null)) {
			paymentEntityDTO.setTransactionId(rrn.toString());
		}



		// Payment Data Update in Payment Table
		paymentRepo.insertPaymentResponse(paymentEntityDTO.getRazorpayPaymentId(),
				paymentEntityDTO.getRazorpayOrderId(), paymentEntityDTO.getRazorPaySubscriptionId(),
				paymentEntityDTO.getUserId(), paymentEntityDTO.getOrganizationId(),
				paymentEntityDTO.getPaymentStatusId(), paymentEntityDTO.getAmount(), paymentEntityDTO.getPaymentMode(),
				paymentEntityDTO.getUserEmail(), paymentEntityDTO.getContact(), paymentEntityDTO.getCreatedOn(),
				paymentEntityDTO.getTransactionId());

	}

}
