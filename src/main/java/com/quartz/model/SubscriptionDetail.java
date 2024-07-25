package com.quartz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Subscription")
public class SubscriptionDetail {
	@Id
	private int id;
	@Column(name = "RazorPaySubscriptionId")
	private String razorPaySubscriptionId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRazorPaySubscriptionId() {
		return razorPaySubscriptionId;
	}

	public void setRazorPaySubscriptionId(String razorPaySubscriptionId) {
		this.razorPaySubscriptionId = razorPaySubscriptionId;
	}

}
