package com.cabbooking.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabbooking.dto.RideDTO;
import com.cabbooking.entity.Ride;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.exception.CustomerBookingException;
import com.cabbooking.exception.DriverBookingException;
import com.cabbooking.exception.RideBookingException;
import com.cabbooking.repository.CustomerRepo;
import com.cabbooking.repository.RideRepo;
import com.cabbooking.service.IRideService;

@Service
public class IRideServiceImpl implements IRideService {

	@Autowired
	RideRepo rideRepo;
	
	@Override
	public Ride addRide(Ride ride) {
		return rideRepo.save(ride);
	}

	@Override
	public Ride updateRide(Ride ride,int rideId) throws RideBookingException {
		Optional<Ride> checkRide = rideRepo.findById(rideId);
		if(checkRide.isPresent()) {
			Ride updateRide = checkRide.get();
			updateRide.setPickupLocation(ride.getPickupLocation());
			updateRide.setDropoffLocation(ride.getDropoffLocation());
			updateRide.setStartTime(ride.getStartTime());
			updateRide.setEndTime(ride.getEndTime());
			updateRide.setRideStatus(ride.getRideStatus());
			updateRide.setCab(ride.getCab());
			updateRide.setCustomer(ride.getCustomer());
			
			return rideRepo.save(updateRide);
			
		}
		throw new RideBookingException("Ride not found");
	}

	@Override
	public Ride cancleRide(Integer rideId)throws RideBookingException {
		Optional<Ride> checkRide = rideRepo.findById(rideId);
		if(checkRide.isPresent()) {
			Ride cancleRide = checkRide.get();
			rideRepo.delete(cancleRide);
			return cancleRide;
		}
		throw new RideBookingException("Ride not found");
		
	}

	@Override
	public List<Ride> viewAllRides() throws RideBookingException {
		List<Ride> ridelist = rideRepo.findAll();
		
		if(!ridelist.isEmpty()) {
			return ridelist;
		}
		throw new RideBookingException("Rides not found");
		
	}

	@Override
	public Ride viewRideByCustomerId(Integer customerId) throws CustomerBookingException {
		Ride ride = rideRepo.findAll().stream().filter(e->e.getCustomer().getUserId()==customerId).findAny().get();
		if(!ride.equals(null)) {
			return ride;
		}
		throw new CustomerBookingException("Ride not found By customerId");
//		Optional<Ride> viewRide = 
//				return rideRepo.findAll().stream().filter(e->e.getCustomer().getUserId()==customerId).findAny().get();
		
		
		
	}

	@Override
	public List<Ride> viewRideByDriverId(Integer driverId)throws DriverBookingException {
		List<Ride> viewById = rideRepo.findAll();
		if(!viewById.isEmpty()) {
			return viewById;
		}
		throw new DriverBookingException("ride not found by driverId");
	}

	@Override
	public List<Ride> viewRidesByCabId(Integer cabId)throws CabBookingException {
		
		List<Ride> ridesByCabId = rideRepo.findAll().stream().filter(e->e.getCab().getCabId()==cabId).collect(Collectors.toList());
		
		if(!ridesByCabId.isEmpty()) {
			return ridesByCabId;
		}
		throw new CabBookingException("rides not found by cabId");
		//return rideRepo.findAll().stream().filter(e->e.getCab().getCabId()==cabId).collect(Collectors.toList());
	}

	@Override
	public List<Ride> viewRidesByStatus(String status) throws RideBookingException {
		List<Ride> ridesByStatus =rideRepo.findAll().stream().filter(e->e.getRideStatus().toString().equals(status)).collect((Collectors.toList()));
		if(!ridesByStatus.isEmpty()) {
			return ridesByStatus;
		}
		throw new RideBookingException("rides not found");
//		return rideRepo.findAll().stream().filter(e->e.getRideStatus().toString().equals(status)).collect((Collectors.toList()));
	}

	@Override
	public List<Ride> viewRidesByDate(LocalDate date)throws RideBookingException {
		List<Ride> ridesByDate = rideRepo.findAll().stream().filter(e->e.getStartTime().toLocalDate().isEqual(date)).collect(Collectors.toList());
		if(!ridesByDate.isEmpty()) {
			return ridesByDate;
		}
		throw new RideBookingException("rides not found on "+date);
//		return rideRepo.findAll().stream().filter(e->e.getStartTime().toLocalDate().isEqual(date)).collect(Collectors.toList());
	}

	
}
