package com.quartz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserOrganizationMapping")
public class IsPaidUpdate {
	@Id
	private Long id;

}
