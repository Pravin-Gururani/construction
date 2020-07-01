package com.pg.construction.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "commissions")
public class Commissions 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer commissionId;
	
	@OneToOne(targetEntity = Bookings.class)
	@JoinColumn(name="booking_id", referencedColumnName = "bookingId")
	private Bookings bookingId;
	
	@Column(name="commission_amount")
	private Integer commissionAmount;
	
	@Column(name="commission_paid")
	private Integer commissionPaid;
	
	@Column(name="commission_status")
	private String commissionStatus;

	public Commissions() {
	}

	public Commissions(Integer commissionId, Bookings bookingId, Integer commissionAmount, Integer commissionPaid,
			String commissionStatus) {
		super();
		this.commissionId = commissionId;
		this.bookingId = bookingId;
		this.commissionAmount = commissionAmount;
		this.commissionPaid = commissionPaid;
		this.commissionStatus = commissionStatus;
	}

	public Integer getCommissionId() {
		return commissionId;
	}

	public void setCommissionId(Integer commissionId) {
		this.commissionId = commissionId;
	}

	public Bookings getBookingId() {
		return bookingId;
	}

	public void setBookingId(Bookings bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(Integer commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public Integer getCommissionPaid() {
		return commissionPaid;
	}

	public void setCommissionPaid(Integer commissionPaid) {
		this.commissionPaid = commissionPaid;
	}

	public String getCommissionStatus() {
		return commissionStatus;
	}

	public void setCommissionStatus(String commissionStatus) {
		this.commissionStatus = commissionStatus;
	}
	
	
	
	
}
