package com.cabbooking.service;

import java.util.List;

import com.cabbooking.dto.CabDTO;
import com.cabbooking.entity.Cab;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.exception.DriverBookingException;

public interface ICabService {
	
	public Cab addCab(Cab cab) ;

	public Cab updateCab(Cab cab,int cabId)throws CabBookingException;

	public List<Cab> viewCabs()throws CabBookingException;

	public List<Cab> viewCabByType(String cabType) throws CabBookingException;

	public List<Cab> viewCabByCurrentLocation(String currentLocation) throws CabBookingException;
	
	public Cab viewCabByDriverId(int driverId)throws DriverBookingException;

	public Cab viewCabById(int cabId)throws CabBookingException;

	public List<Cab> viewCabByTypeAndLocation(String cabType,String currentLocation)throws CabBookingException;

	public List<Cab> viewCabByAvailability()throws CabBookingException;

}
