package com.pg.construction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pg.construction.model.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>
{
	
}