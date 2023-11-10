package com.cabbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cabbooking.entity.Ride;

@Repository
public interface RideRepo extends JpaRepository<Ride,Integer> {

}
