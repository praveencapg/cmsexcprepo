package com.cabbooking.service;

import java.time.LocalDate;
import java.util.List;

import com.cabbooking.dto.RideDTO;
import com.cabbooking.entity.Ride;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.exception.CustomerBookingException;
import com.cabbooking.exception.DriverBookingException;
import com.cabbooking.exception.RideBookingException;

public interface IRideService {
	

	public Ride addRide(Ride ride);

	public Ride updateRide(Ride ride,int rideId)throws RideBookingException;

	public Ride cancleRide(Integer rideId)throws RideBookingException;

	public List<Ride> viewAllRides()throws RideBookingException;

	public Ride viewRideByCustomerId(Integer customerId)throws CustomerBookingException;

	public List<Ride> viewRideByDriverId(Integer driverId)throws DriverBookingException;

	public List<Ride> viewRidesByCabId(Integer cabId)throws CabBookingException;
	
	public List<Ride> viewRidesByStatus(String status)throws RideBookingException;
	
	public List<Ride> viewRidesByDate(LocalDate date)throws RideBookingException;
}
