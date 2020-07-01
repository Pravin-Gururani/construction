package com.pg.construction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pg.construction.model.Commissions;


@Repository
public interface CommissionsRepository extends JpaRepository<Commissions, Integer> 
{

}