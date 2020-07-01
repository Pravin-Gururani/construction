package com.pg.construction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pg.construction.model.Address;
import com.pg.construction.model.Locations;


@Repository
public interface LocationsRepository extends JpaRepository<Locations, Integer> 
{

}