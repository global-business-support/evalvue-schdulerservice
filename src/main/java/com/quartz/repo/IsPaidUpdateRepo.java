package com.quartz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quartz.model.IsPaidUpdate;

@Repository
public interface IsPaidUpdateRepo extends JpaRepository<IsPaidUpdate, Long> {
	@Modifying
	@Transactional
	@Query(value = "UPDATE UserOrganizationMapping SET IsPaid = :isPaid WHERE OrganizationId = :organizationId And UserId = :userId", nativeQuery = true)
	public void updateIsPaid(@Param("organizationId") Long organizationId, @Param("userId") Long userId,
			@Param("isPaid") boolean isPaid);

}
