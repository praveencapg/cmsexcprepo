package com.cabbooking.service;

import java.util.List;

import com.cabbooking.dto.DriverDTO;
import com.cabbooking.entity.Driver;
import com.cabbooking.exception.DriverBookingException;

public interface IDriverService {

	public Driver addDriver(Driver driver) throws DriverBookingException;

	public Driver updateDriver(Driver driver,int driverId)throws DriverBookingException;

	public List<Driver> viewDrivers()throws DriverBookingException;

	public Driver viewDriverById(Integer driverId)throws DriverBookingException;

	public List<Driver> viewDriverByAvailability()throws DriverBookingException;
}
