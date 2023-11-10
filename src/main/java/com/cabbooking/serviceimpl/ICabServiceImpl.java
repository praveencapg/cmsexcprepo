package com.cabbooking.serviceimpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cabbooking.entity.Cab;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.exception.DriverBookingException;
import com.cabbooking.repository.CabRepo;
import com.cabbooking.service.ICabService;

@Service
public class ICabServiceImpl implements ICabService{
	
	@Autowired
	CabRepo cabRepo;
	
	@Override
	public Cab addCab(Cab cab) {
//		Optional<Cab> cabfind = cabRepo.findById(cab.getCabId());
		
			
			cabRepo.save(cab);
			return cab;
		
				
		
	}

	@Override
	public Cab updateCab(Cab cab,int cabId) throws CabBookingException {
		Optional<Cab> check = cabRepo.findById(cab.getCabId());
		Cab updateCab = check.get();
		if(check.isPresent()) {
			if(Objects.nonNull(cab.getCabType()))
		     {
				updateCab.setCabType(cab.getCabType());
			}
			if(Objects.nonNull(cab.getRatePerKm())) {
				updateCab.setRatePerKm(cab.getRatePerKm());
			}
			if(Objects.nonNull(cab.getRegistrationNo())) {
				updateCab.setRegistrationNo(cab.getRegistrationNo());
			}
			if(Objects.nonNull(cab.getCabAvailability())) {
				updateCab.setCabAvailability(cab.getCabAvailability());
			}
			if(Objects.nonNull(cab.getCurrentLocation())) {
				updateCab.setCurrentLocation(cab.getCurrentLocation());
			}
			cabRepo.save(updateCab);
			return updateCab;
		}
		
			throw new CabBookingException("Cab details not found");
		
	}

	@Override
	public List<Cab> viewCabs() throws CabBookingException{
		List<Cab> allCab = cabRepo.findAll();
		if(!allCab.isEmpty()) {
			return allCab;
		}
		throw new  CabBookingException("No Cabs Found");
	}

	@Override
	public List<Cab> viewCabByType(String cabType) throws CabBookingException{
		List<Cab> viewCabsOfTyp = new ArrayList<>();
		List<Cab> allCab = cabRepo.findAll();
		for(Cab cab:allCab) {
			String carName = cab.getCabType().toString();
			if(carName.equals(cabType)){
			viewCabsOfTyp.add(cab);
			}
		}
		if(!viewCabsOfTyp.isEmpty()) {
			return viewCabsOfTyp;
		}
			throw new CabBookingException(cabType+"Cab Not Found");
		
	}

	@Override
	public List<Cab> viewCabByCurrentLocation(String currentLocation)throws CabBookingException {
		List<Cab> viewCabsByLoc = new ArrayList<>();
		List<Cab> allCab = cabRepo.findAll();
		for(Cab cab:allCab) {
			if(cab.getCurrentLocation().equals(currentLocation)) {
				viewCabsByLoc.add(cab);
			}
		}
		if(!viewCabsByLoc.isEmpty()) {
			return viewCabsByLoc;
		}
		throw new CabBookingException("Cab not found at this location");

	}

	@Override
	public Cab viewCabByDriverId(int driverId) throws DriverBookingException {
		Optional<Cab> check = cabRepo.findById(driverId);
		
		if(check.isPresent()) {
			return check.get();
		}
		else {
			throw new DriverBookingException("Cab not found by driverID");
		}
		
	}

	@Override
	public Cab viewCabById(int cabId)throws CabBookingException {
		Optional<Cab> check = cabRepo.findById(cabId);
		if(check.isPresent()) {
			return check.get();
		}
		else {
			throw new CabBookingException("Cab not found by cabID");
		}
		
	}

	@Override
	public List<Cab> viewCabByTypeAndLocation(String cabType, String currentLocation) throws CabBookingException{
		List<Cab> viewCabs = new ArrayList<>();
		List<Cab> allCab = cabRepo.findAll();
		for(Cab cab : allCab) {
			String cabName = cab.getCabType().toString();
			if(cab.getCurrentLocation().equals(currentLocation)&& cabName.equals(cabType)) {
				viewCabs.add(cab);
			}
		}
		if(!viewCabs.isEmpty()) {
			return viewCabs;
		}
		else {
			throw new CabBookingException(cabType+" Type Cab not found at "+currentLocation);
		}
		

	}

	@Override
	public List<Cab> viewCabByAvailability() throws CabBookingException {
		List<Cab> viewCabByAva = new ArrayList<>();
		List<Cab> allCab = cabRepo.findAll();
		for(Cab cab : allCab) {
			if(cab.getCabAvailability()==true) {
				viewCabByAva.add(cab);
			}
		}
		if(!viewCabByAva.isEmpty()) {
			return viewCabByAva;
		}
		else {
			throw new CabBookingException("Cab not found by cabID");

		}
		
	}

}
