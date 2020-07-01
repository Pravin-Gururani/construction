package com.pg.construction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pg.construction.model.Ratings;


@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Integer> 
{

}