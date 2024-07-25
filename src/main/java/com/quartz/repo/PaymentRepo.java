package com.quartz.repo;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.quartz.model.PaymentEntityDTO;

public interface  PaymentRepo extends JpaRepository<PaymentEntityDTO, Long>{
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Payment (RazorpayPaymentId, RazorpayOrderId, RazorPaySubscriptionId, UserId, OrganizationId, PaymentStatusId, Amount, PaymentMode, UserEmail, Contact, CreatedOn, TransactionId) "
			+ "VALUES (:razorpayPaymentId, :razorpayOrderId, :razorPaySubscriptionId, :userId, :organizationId, :paymentStatusId, :amount, :paymentMode, :userEmail, :contact, :createdOn, :transactionId )", nativeQuery = true)
	public  void insertPaymentResponse(
			@Param("razorpayPaymentId") String razorpayPaymentId,
			@Param("razorpayOrderId") String razorpayOrderId,
			@Param("razorPaySubscriptionId") String razorPaySubscriptionId,
			@Param("userId") Long userId,
			@Param("organizationId") Long organizationId, 
			@Param("paymentStatusId") int paymentStatusId,
			@Param("amount") int amount, 
			@Param("paymentMode") String paymentMode, 
			@Param("userEmail") String userEmail,
			@Param("contact") String contact, 
			@Param("createdOn") Date createdOn,
			@Param("transactionId") String transactionId) ;
}
