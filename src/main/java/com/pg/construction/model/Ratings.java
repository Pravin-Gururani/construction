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
@Table(name = "ratings")
public class Ratings 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ratingId;
	
	@Column(name="rating")
	private int rating;
	
	@OneToOne(targetEntity = Users.class)
	@JoinColumn(name="user_id", referencedColumnName = "userId")
	private Users userId;
	
	@OneToOne(targetEntity = Employees.class)
	@JoinColumn(name="employee_id", referencedColumnName = "employeeId")
	private Employees employeeId;

	public Ratings() {
	}

	public Ratings(Integer ratingId, int rating, Users userId, Employees employeeId) {
		this.ratingId = ratingId;
		this.rating = rating;
		this.userId = userId;
		this.employeeId = employeeId;
	}

	public Integer getRatingId() {
		return ratingId;
	}

	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public Employees getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employees employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
