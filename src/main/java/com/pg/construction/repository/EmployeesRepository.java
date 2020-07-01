package com.pg.construction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pg.construction.model.Address;
import com.pg.construction.model.Employees;


@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer>
{
	
}