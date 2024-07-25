package com.quartz.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quartz.model.SubscriptionDetail;

import jakarta.transaction.Transactional;
@Repository
public interface SubscriptionRepo extends JpaRepository<SubscriptionDetail, Integer> {

	@Query(value = "SELECT RazorPaySubscriptionId,EndDate,NextDueDate,UserId,OrganizationId FROM Subscription", nativeQuery = true)
	public List<Object[]> getSubscriptionId();
    @Modifying
    @Transactional
    @Query(value = "UPDATE Subscription SET NextDueDate = :nextDueDate, SubscriptionStatusId = :subscriptionStatus WHERE RazorPaySubscriptionId = :subscriptionId AND UserId = :userId AND OrganizationId = :organizationId", nativeQuery = true)
    public  void updateSubscriptionDetails(
        @Param("nextDueDate") Date nextDueDate,
        @Param("subscriptionStatus") int subscriptionStatus,
        @Param("subscriptionId") String subscriptionId,
        @Param("userId") Long userId,
        @Param("organizationId") Long organizationId
    ) ;

	
	

}
